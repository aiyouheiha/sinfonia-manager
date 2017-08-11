package com.heiha.sinfonia.manager.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static com.sun.java.browser.dom.DOMService.getService;
import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/11 10:40<br>
 * <b>Author:</b> heiha<br>
 */
@RestController
@RequestMapping("/**/v1/**")
public class CommonController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
    private final static String COMMON_URL = "uC";
    private final static String COMMON_HTTP = "http://";

    @Autowired
    private RestTemplate restTemplate;

    public void error(HttpServletRequest request, HttpServletResponse response) {
        throw new RuntimeException("Hystrix with an error");
    }

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping
    public void redirectRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Receive one request");
        String method = request.getMethod();



        String uri = request.getRequestURI();
        String realURI = getRealURI(uri);

        String requestBody = "";

        ResponseEntity<String> responseEntity = null;
        if (RequestMethod.GET.name().equals(method)) {
            responseEntity = restTemplate.getForEntity(realURI, String.class, request.getParameterMap());
        } else if ("POST".equals(method)) {
            requestBody = getBody(request);
            responseEntity = restTemplate.postForEntity(realURI, requestBody, String.class);
        } else if ("DELETE".equals(method)) {
            restTemplate.delete(realURI, request.getParameterMap());
        } else if ("PUT".equals(method)) {
            requestBody = getBody(request);
            restTemplate.put(realURI, requestBody);
        } else if ("OPTIONS".equals(method)) {
            restTemplate.optionsForAllow(realURI);
        }

        if (responseEntity == null)
            return;
        int code = responseEntity.getStatusCode().value();
        response.setStatus(code);
        if (responseEntity.getHeaders().getFirst("Content-Type") != null) {
            response.addHeader("Content-Type", responseEntity.getHeaders().getFirst("Content-Type") );
        }
        if (responseEntity.getHeaders().getFirst("Transaction-Id") != null) {
            response.addHeader("Transaction-Id", responseEntity.getHeaders().getFirst("Transaction-Id"));
        }
        response.addHeader("Access-Control-Allow-Origin", "http://sinfonia.heiha.com");
        response.addHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.addHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        try (
                ServletOutputStream outputStream = response.getOutputStream();
                ) {
            if (Integer.valueOf(204).equals(code)) {
            } else {
                byte[] dataByteArr = responseEntity.getBody().getBytes("UTF-8");
                outputStream.write(dataByteArr);
                outputStream.flush();
            }
        }
    }

    private String getRealURI(String uri) {
        StringBuffer sb = new StringBuffer();
        sb.append(COMMON_HTTP);
        String realUri = "";
        if (uri.startsWith("/uc")) {
//            sb.append("uc.");
            realUri = uri.replaceFirst("/uc", "");
        }
        return sb
                .append(COMMON_URL)
//                .append(":")
//                .append(20080)
                .append(realUri)
                .toString();
    }

    public String getBody(HttpServletRequest request) throws IOException {
        StringBuffer sb = new StringBuffer();
        try (
                InputStream in = request.getInputStream()
                ) {
            return IOUtils.toString(in, "UTF-8");
        }
    }
}
