package com.bhima2001.simple_http_server.config;

public class Configuration {
    private int port;
    private String webroot;

    public Configuration(int port, String webroot) {
        this.port = port;
        this.webroot = webroot;
    }

    public int getPort() {
        return this.port;
    }

    public String getWebroot() {
        return this.webroot;
    }
}
