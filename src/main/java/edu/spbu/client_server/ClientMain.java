package edu.spbu.client_server;

import java.net.*;
import java.io.*;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket c = new Socket(InetAddress.getByName("localhost"), 3930);
        Client m = new Client(c);
        m.start();
    }
}