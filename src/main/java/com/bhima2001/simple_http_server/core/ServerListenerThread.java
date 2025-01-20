package com.bhima2001.simple_http_server.core;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ServerListenerThread implements Runnable {
    private ServerSocket serverSocket;
    private int port;
    private String webRoot;
    private static final Logger LOGGER = LogManager.getLogger(ServerListenerThread.class);

    public ServerListenerThread(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ResponseThread response = new ResponseThread(socket);
                response.start();
            }
            //serverSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

