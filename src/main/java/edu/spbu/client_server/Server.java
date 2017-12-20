package edu.spbu.client_server;

import java.io.*;
import java.net.Socket;

public class Server {

    private Socket s; //сделан для клиентов
    private InputStream is;
    private OutputStream os;
    private String fileName;

    public Server(Socket s) throws IOException {
        this.s = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
    }

    public void start() throws IOException {
        readInputStream();  //прочитал сообщение клиента
        writeOutputStream(); //ответил клиенту
        s.close();  //закрылся
    }

    private void readInputStream() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = br.readLine();
        String[] s1 = s.split(" "); //делим входную строку на массив слов
        String s2 = s1[1]; //должен получить "/server_client.html"
        if (s2.length()>0) {
            fileName = s2.substring(1, s2.length()); //создает новую строку от s2[1] до s2.length
        }else fileName = "";
        System.out.println("readInputStream");
    }

    private void writeOutputStream() throws IOException {
        File file = new File("sc.txt");
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String text="";
            String i;
            while ((i = bfr.readLine()) != null) {
                text = text + i + "\r\n"; //посимвольно чтение файла
            }
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: Levant\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + text.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
            String result = response + text; //сервер выдает текст , который описан выше,  и текст из файла
            os.write(result.getBytes()); //передаёт полученную строку result и приводит её к byte массиву.
            os.flush(); //чистит буфер
        }else{
            os.write("<html><h2>404</h2></html>".getBytes());
            os.flush();
        }
    }
 /*   private void writeResponse(String s) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + s;
        os.write(result.getBytes());
        os.flush();
    }

    private void readInputHeaders() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while(true) {
            String s = br.readLine();
            if(s == null || s.trim().length() == 0) {
                break;
            }
        }
    }*/
}