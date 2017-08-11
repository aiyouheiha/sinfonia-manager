package com.heiha.sinfonia.manager.web.config;

import com.heiha.sinfonia.manager.web.exception.ServiceInitException;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/11 15:07<br>
 * <b>Author:</b> heiha<br>
 */
public enum Service {
    UC("uc"), MANAGER("manager");

    private String name;
    private String host;
    private Integer port;

    Service(String name) {
        this.name = name;
    }

    public void init(String host, Integer port) {
        if (this.host == null && this.port == null) {
            this.host = host;
            this.port = port;
        } else
            throw new ServiceInitException(this.getName() + " has been already initialized");
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }
}
