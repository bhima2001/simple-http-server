package com.bhima2001.simple_http_server.core.Http.ResponseHandler;

import com.bhima2001.simple_http_server.core.Http.HttpException;
import com.bhima2001.simple_http_server.core.Http.HttpMessage;
import com.bhima2001.simple_http_server.core.Http.HttpRequest;
import com.bhima2001.simple_http_server.core.Response.GalleryDispatcher;
import com.bhima2001.simple_http_server.core.Response.HomeDispatcher;
import com.bhima2001.simple_http_server.core.Response.NotFound;
import com.bhima2001.simple_http_server.core.Response.ResponseDispatcher;

public class HttpResponse extends HttpMessage {
    private HttpException status;
    private String httpVersion;
    private final static String CRLF = "\r\n";
    private final static String SP = " ";
    private String source;
    private ResponseDispatcher responseDispatcher;

    public HttpResponse() {
    }

    public String requestBody(String title, String message) {
        return "<html><title>" + title + "</title><body>" + message + "</body></html>";
    }

    public void extract(HttpRequest request) {
        this.status = HttpException.Request_Served_200_success;
        this.httpVersion = request.getHttpVersion();
        this.setHeaders("content-type", "text/html; charset=UTF-8");
        this.setHeaders("content-length", "100");
        System.out.println(request.getEndpoint().toLowerCase());
        if (request.getEndpoint().toLowerCase().equals("/home")) {
            this.source = "Home";
            this.responseDispatcher = new HomeDispatcher();
        } else if (request.getEndpoint().toLowerCase().equals("/gallery")) {
            this.source = "Gallery";
            this.responseDispatcher = new GalleryDispatcher();
        } else {
            this.source = "Not Found";
            this.responseDispatcher = new NotFound();
        }
        this.setBody(this.responseDispatcher.dispatchHandler());
    }

    public String build() {
        return this.httpVersion + SP + this.status.code + SP + this.status.message + SP + CRLF + "content-length: "
                + this.getHeaders("content-length") + CRLF + "content-type: "
                + this.getHeaders("content-type") + CRLF + CRLF + requestBody(this.source, this.getBody()) + CRLF
                + CRLF;
    }

    public static String builder(String httpVersion, HttpException status) {
        String requestBody = "<html><title>" + status.message + "</title><body>" + status.code + status.message
                + "</body></html>";
        return httpVersion + SP + status.code + SP + status.message + SP + CRLF + "content-length: "
                + requestBody.getBytes() + CRLF + "content-type: "
                + "text/html; charset=UTF-8" + CRLF + CRLF + requestBody + CRLF + CRLF;

    }
}
