package com.heiha.sinfonia.manager.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/11 15:44<br>
 * <b>Author:</b> heiha<br>
 */
@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfig implements InitializingBean {
    @Autowired
    private ServiceProperties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        Service.UC.init(properties.getUc().getHost(), properties.getUc().getPort());
        Service.MANAGER.init(properties.getManager().getHost(), properties.getManager().getPort());
    }
}
