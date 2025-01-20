package com.bhima2001.simple_http_server.core;

import java.io.OutputStream;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ResponseThread extends Thread {

    private Socket socket;
    private static final Logger LOGGER = LogManager.getLogger(ResponseThread.class);

    public ResponseThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            String crlf = "\r\n";
            String res = "<head><Title>Simple Http Server Application</Title></head><body>This is the body of the page</body>";

            String response = "HTTP/1.1 200 OK" + crlf + "content-length: " + res.getBytes() + crlf + crlf + res + crlf + crlf;

            try {
                outputStream.write(response.getBytes());
                outputStream.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            LOGGER.info("Processing this request....");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
