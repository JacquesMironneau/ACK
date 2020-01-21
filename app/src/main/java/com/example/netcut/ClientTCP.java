package com.example.netcut;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientTCP
{

    public static final int PORT = 50002;
    public static final String IP = "192.168.0.30";

    private Socket socket;

    public ClientTCP()
    {
        try {
            this.socket = new Socket(IP, PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AAAAAAAAAAAAAAALED");
        }
    }

    public void send(int message)
    {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            bw.write(message+"\n");
            //bw.write("ALLED GROS\n");
            bw.flush();
            System.out.println("ENVOYER CHEF");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
