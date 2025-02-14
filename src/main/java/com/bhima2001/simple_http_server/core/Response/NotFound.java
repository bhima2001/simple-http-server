package com.bhima2001.simple_http_server.core.Response;

public class NotFound implements ResponseDispatcher {
    public String dispatchHandler() {
        return "The page is not found.";
    }

    public int getLength() {
        return dispatchHandler().length();
    }
}
