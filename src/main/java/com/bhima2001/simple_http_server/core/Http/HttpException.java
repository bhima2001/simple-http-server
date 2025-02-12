package com.bhima2001.simple_http_server.core.Http;

public enum HttpException {
    //Client side errors
    Client_Error_400_Bad_Request(400, "Bad Request"),
    Client_Error_401_Unauthorized(401, "Unauthorized"),
    Client_Error_404_Not_Found(404, "Not Found"),
    Client_Error_413_Content_Too_Large(413, "Content Too Large"),

    //Server Side Errors
    Server_Error_500_Internal_Server_Error(500, "Internal Server Error"),
    Server_Error_501_Not_Implemented(501, "Nop Implemented"),
    Server_Error_504_Gateway_Timeout(504, "Gateway Timeout"),

    //Success
    Request_Served_200_success(200, "success");

     public int code;
     public String message;

    HttpException(int code , String message){
        this.code = code;
        this.message = message;
    }

    public static HttpException getException(int code) {
        for( HttpException exception: HttpException.values()) {
            if(code == exception.code){
                return exception;
            }
        }
        return Client_Error_404_Not_Found;
    }
}
