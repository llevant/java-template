package edu.spbu.client_server;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket c;
    private OutputStream os;
    private InputStream is;
    public static final int PORT = 3930;

    public Client(Socket c) throws IOException {
        this.c = c;
        this.os = c.getOutputStream();
        this.is = c.getInputStream();
    }

    public void start() throws IOException {
        writeOutputStream();//посылает привет серверу
        readInputStream(); //получает ответ сервера
    }

    public void writeOutputStream() throws IOException {
        String s = "GET /server_client.html HTTP/1.0\r\n\r\n";
        os.write(s.getBytes());//отпраляет байт-строку серверу
        os.flush();//закрывает
    }

    public void readInputStream() throws IOException{ //печатает ответ от сервера
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = br.readLine();
        while (s!=null){
            System.out.println(s);
            s = br.readLine();
        }
    }
}

