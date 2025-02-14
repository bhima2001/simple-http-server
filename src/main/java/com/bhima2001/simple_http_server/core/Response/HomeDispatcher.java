package com.bhima2001.simple_http_server.core.Response;

public class HomeDispatcher implements ResponseDispatcher {
    public String dispatchHandler() {
        return "This is home page,";
    }

    public int getLength() {
        return dispatchHandler().length();
    }
}
