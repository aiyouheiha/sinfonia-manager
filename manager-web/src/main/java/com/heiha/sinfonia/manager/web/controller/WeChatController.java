package com.heiha.sinfonia.manager.web.controller;

import com.heiha.sinfonia.manager.web.pojo.WeChatAuthInfo;
import com.heiha.sinfonia.manager.web.util.WeChatUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public String post(HttpServletRequest request) throws IOException {
        System.out.println(IOUtils.toString(request.getInputStream(), "UTF-8"));
        return "OvO";
    }
}
