package com.heiha.sinfonia.manager.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/11 15:28<br>
 * <b>Author:</b> heiha<br>
 */
@ConfigurationProperties(prefix = "heiha.sinfonia.service")
public class ServiceProperties {
    private UC uc;
    private Manager manager;

    public UC getUc() {
        return uc;
    }

    public void setUc(UC uc) {
        this.uc = uc;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public static class UC extends ServiceFields {}
    public static class Manager extends ServiceFields {}

    static class ServiceFields {
        private String host;
        private Integer port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

}
