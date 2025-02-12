package com.bhima2001.simple_http_server.core.Http;

public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String endpoint;
    private String Httpversion;

    HttpRequest() {
    }

    public HttpMethod getHttpMethod() {
        return method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getHttpVersion() {
        return Httpversion;
    }

    void setHttpMethod(String methodName) throws HttpParserException {
        for (HttpMethod method : HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpParserException(
                HttpException.Server_Error_501_Not_Implemented);
    }

    void setEndpoint(String endpoint) throws HttpParserException {
        if (endpoint == null || endpoint.length() == 0) {
            throw new HttpParserException(HttpException.Server_Error_500_Internal_Server_Error);
        }
        this.endpoint = endpoint;
    }

    void setHttpVersion(String Httpversion) {
        this.Httpversion = Httpversion;
    }

    public void processHeader(String header) {
        int index = header.indexOf(':');
        this.setHeaders(header.substring(0, index), header.substring(index, header.length()));
    }
}
