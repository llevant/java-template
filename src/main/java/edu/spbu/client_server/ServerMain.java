package edu.spbu.client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        //System.out.println("1 - подключиться к браузеру")
        ServerSocket ss = new ServerSocket(3930);
        while (true) {
            Socket s = ss.accept();
            Server m = new Server(s);
            m.start();
        }
    }
}