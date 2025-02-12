package com.bhima2001.simple_http_server.core.Http;

import java.util.HashMap;

public abstract class HttpMessage {
    private String body = null;
    private HashMap<String, String> headers = new HashMap<>();

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHeaders(String header, String value) {
        this.headers.put(header.toLowerCase(), value);
    }

    public String getHeaders(String header) {
        return this.headers.get(header);
    }
}
