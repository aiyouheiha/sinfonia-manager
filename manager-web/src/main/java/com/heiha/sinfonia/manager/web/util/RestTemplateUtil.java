package com.heiha.sinfonia.manager.web.util;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/11 13:49<br>
 * <b>Author:</b> heiha<br>
 */
public class RestTemplateUtil<T> {
    public static void execute(RestTemplate restTemplate,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpHeaders httpHeaders = getHttpHeaders(request);
        String body = getBody(request);
        Map<String, ?> parameterMap = request.getParameterMap();
        String method = request.getMethod();
        String uri = request.getRequestURI();
    }

//    public static ResponseEntity<T> get(RestTemplate restTemplate) {
//        RequestEntity<T> requestEntity = new RequestEntity<T>()
//        restTemplate.
//    }

    public static HttpHeaders getHttpHeaders(HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            httpHeaders.set(name, request.getHeader(name));
        }
        return httpHeaders;
    }

    public static String getBody(HttpServletRequest request) throws IOException {
        StringBuffer sb = new StringBuffer();
        try (
                InputStream in = request.getInputStream()
        ) {
            return IOUtils.toString(in, "UTF-8");
        }
    }

//    private String getRealURI(String uri) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(Service);
//        String realUri = "";
//        if (uri.startsWith("/uc")) {
//            sb.append("uc.");
//            realUri = uri.replaceFirst("/uc", "");
//        }
//        return sb.append(COMMON_URL).append(":").append(20080).append(realUri).toString();
//    }
}
