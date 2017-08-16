package com.heiha.sinfonia.manager.web.controller;

import com.heiha.sinfonia.manager.web.pojo.WeChatAuthInfo;
import com.heiha.sinfonia.manager.web.util.WeChatUtil;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/7 15:01<br>
 * <b>Author:</b> heiha<br>
 */
@RestController
@RequestMapping("/wx")
public class WeChatController {
    @RequestMapping(method = RequestMethod.GET)
    public String auth(WeChatAuthInfo authInfo) {
        return WeChatUtil.checkSignature(authInfo);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws IOException, DocumentException {
        SAXReader saxReader = new SAXReader();
        try (
                InputStream inputStream = request.getInputStream();
                OutputStream outputStream = response.getOutputStream();

        ) {
            String result = WeChatUtil.response(saxReader.read(inputStream, "UTF-8"));
            outputStream.write(result.getBytes("UTF-8"));
            outputStream.flush();
        }
    }
}
