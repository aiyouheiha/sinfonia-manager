package com.heiha.sinfonia.manager.web.util;

import com.heiha.sinfonia.manager.web.pojo.WeChatAuthInfo;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/8 13:15<br>
 * <b>Author:</b> heiha<br>
 */
public class WeChatUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeChatUtil.class);
    private final static String TOKEN = "sinfonia";

    /**
     * 通过校验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
     * @param authInfo WeChatAuthInfo
     * @return
     */
    public static String checkSignature(WeChatAuthInfo authInfo) {
        if (WeChatUtil.checkSignature(authInfo.getSignature(), authInfo.getTimestamp(), authInfo.getNonce())) {
            return authInfo.getEchostr();
        }
        return null;
    }

    /**
     * 通过校验signature对请求进行校验，若校验成功则返回true，表示接入成功
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] array = new String[]{TOKEN, timestamp, nonce};
        // 将signature,timestamp,nonce组成数组进行字典排序
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }

        String stnStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(sb.toString().getBytes());
            stnStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 将sha1加密后的字符串与signature对比，标识该请求来源于微信
        return stnStr != null ? stnStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换成十六进制字符串
     * @param digestArray
     * @return
     */
    private static String byteToStr(byte[] digestArray) {
        String digestStr = "";
        for(int i = 0; i < digestArray.length; i++){
            digestStr += byteToHexStr(digestArray[i]);
        }
        return digestStr;
    }

    /**
     * 将字节转换成为十六进制字符串
     */
    private static String byteToHexStr(byte dByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tmpArr = new char[2];
        tmpArr[0] = Digit[(dByte>>>4)&0X0F];
        tmpArr[1] = Digit[dByte&0X0F];
        String s = new String(tmpArr);
        return s;
    }

    public static String response(Document document) {
        Element root = document.getRootElement();
        String fromUserName = root.element("FromUserName").getStringValue();
        String toUserName = root.element("ToUserName").getStringValue();
        LOGGER.info("\nDocument: {}\nFromUserName: {}\nToUserName: {}", root.getStringValue(), fromUserName, toUserName);

        // iterate through child elements of root
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element e = it.next();
            System.out.println(e.getName() + ": " + e.getStringValue());
        }

        String result = formatXmlAnswer(fromUserName, toUserName, "read ok");
        LOGGER.info("result: {}", result);
        return result;
    }

    /**
     * 封装文字类的返回消息
     * @param to
     * @param from
     * @param content
     * @return
     */
    public static String formatXmlAnswer(String to, String from, String content) {
        StringBuffer sb = new StringBuffer();
        Date date = new Date();
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);
        sb.append("]]></ToUserName><FromUserName><![CDATA[");
        sb.append(from);
        sb.append("]]></FromUserName><CreateTime>");
        sb.append(date.getTime());
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
        sb.append(content);
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
        return sb.toString();
    }
}
