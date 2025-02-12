package com.bhima2001.simple_http_server.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.bhima2001.simple_http_server.core.Http.HttpParser;
import com.bhima2001.simple_http_server.core.Http.HttpParserException;
import com.bhima2001.simple_http_server.core.Http.HttpRequest;
import com.bhima2001.simple_http_server.core.Http.ResponseHandler.HttpResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkerThread implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger();
    private Socket socket;
    private HttpParser httpParser = new HttpParser();

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            HttpRequest request = httpParser.parseHttpMessage(inputStream);
            HttpResponse response = new HttpResponse();
            response.extract(request);
            String responseData = response.build();
            outputStream.write(responseData.getBytes());
        } catch (IOException e) {
            LOGGER.error("There is an error: " + e.getMessage());
        } catch (HttpParserException e1) {
            String responseData = HttpResponse.builder("HTTP/1.1", e1.getStatusCode());
            try {
                outputStream.write(responseData.getBytes());
            } catch (Exception e) {
                LOGGER.error("There is an error with output stream in error response: " + e.getMessage());
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    LOGGER.info("Inputstream closed successfully");
                } catch (Exception e) {
                    LOGGER.error("Error closing inputstream: " + e.getMessage());
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                    LOGGER.info("Outputstream closed successfully");
                } catch (Exception e) {
                    LOGGER.error("Error closing ouputstream: " + e.getMessage());
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                    LOGGER.info("Socket closed successfully");
                } catch (Exception e) {
                    LOGGER.error("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}
