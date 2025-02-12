package com.bhima2001.simple_http_server.core;

import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerListenerThread implements Runnable {
    private ServerSocket serverSocket;
    private int port;
    private String webRoot;
    private static final Logger LOGGER = LogManager.getLogger(ServerListenerThread.class);

    public ServerListenerThread(int port, String webRoot) throws Exception {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                WorkerThread workerThread = new WorkerThread(socket);
                Thread thread = new Thread(workerThread);
                thread.start();
            }
        } catch (Exception e) {
            try {
                serverSocket.close();
            } catch (Exception e1) {
                LOGGER.error("Unable to close server socket because of: " + e1.getMessage());
            }
            LOGGER.error(e.getMessage());
        }
    }

}
