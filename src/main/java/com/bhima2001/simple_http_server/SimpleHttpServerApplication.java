package com.bhima2001.simple_http_server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bhima2001.simple_http_server.config.*;
import com.bhima2001.simple_http_server.core.ServerListenerThread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SimpleHttpServerApplication {

    public static final Logger LOGGER = LogManager.getLogger(SimpleHttpServerApplication.class);

    public static void main(String[] args) {
        int port = 8080;
        String webRoot = "Public";
        if (args.length > 0)
            port = Integer.parseInt(args[0]);
        if (args.length > 1)
            webRoot = args[1];
        Configuration configure = new Configuration(port, webRoot);
        try {
            ServerListenerThread serverListernerThread = new ServerListenerThread(configure.getPort(),
                    configure.getWebroot());
            Thread thread = new Thread(serverListernerThread);
            LOGGER.info("Server is listening at port: " + configure.getPort());
            thread.start();
            thread.join();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
