package com.bhima2001.simple_http_server.core.Http;

public class HttpParserException extends Exception {
    private HttpException statusCode;

    HttpParserException(HttpException exception) {
        super(exception.message);
        this.statusCode = exception;
    }

    public HttpException getStatusCode() {
        return statusCode;
    }
}
