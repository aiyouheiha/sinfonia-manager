package com.heiha.sinfonia.manager.service;

import com.heiha.sinfonia.manager.web.pojo.WechatMsg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/17 19:49<br>
 * <b>Author:</b> heiha<br>
 */
@FeignClient("wechat")
public interface WeChatService {
    @RequestMapping(path = "/v1/msg", method = RequestMethod.POST)
    void save(@RequestBody WechatMsg wechatMsg);
}
