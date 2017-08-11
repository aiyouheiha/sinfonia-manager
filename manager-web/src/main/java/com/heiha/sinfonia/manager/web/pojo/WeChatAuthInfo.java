package com.heiha.sinfonia.manager.web.pojo;

import lombok.Data;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/8 13:22<br>
 * <b>Author:</b> heiha<br>
 */
@Data
public class WeChatAuthInfo {
    /** 微信加密签名 */
    private String signature;
    /** 时间戳 */
    private String timestamp;
    /** 随机数 */
    private String nonce;
    /** 随机字符串 */
    private String echostr;
}
