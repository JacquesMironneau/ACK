package com.example.netcut;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class NetworkTask extends AsyncTask
{

    public static final int PORT = 50002;
    public static final String IP = "192.168.0.30";

    private Socket socket;
    private BufferedReader nis;
    private BufferedWriter nos;

    //Singleton
    private static NetworkTask nt = null;


    public static NetworkTask getInstance()
    {
        if (nt != null)
            return nt;
        nt = new NetworkTask();
        return nt;
    }

    private NetworkTask()
    {
        super();
    }

    @Override
    protected void onPreExecute() {
        Log.i("AsyncTask", "onPreExecute");
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        try {
            Log.i("AsyncTask", "Creating socket (DOinBackGround");

            this.socket = new Socket(IP, PORT);
            Log.i("AsyncTask", "Socket created");

            this.nos = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.nis = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            Log.i("AsyncTask", "Streams created");


            String received;

            while ((received = this.nis.readLine()) != null)
            {
                Log.i("DataReceived"  ,received);
                publishProgress(received);
                //TODO call the UI thread and edit the text
                editText(received);
            }


        } catch (IOException e)
        {
            e.printStackTrace();
        }
//        finally {
//            try{
//                this.nis.close();
//                this.nos.close();
//                this.socket.close();
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            Log.i("AsyncTask", "Job done !");
//        }
        return null;
    }

    public void sendData(Messages msg)
    {
        if (this.socket == null) return;

        try {
            Log.i("SendData", "Writing to the socket !");
            this.nos.write(msg.ordinal());
            this.nos.flush();
            Log.i("SendData", "You walking ani");
        } catch (IOException e)
        {
            Log.i("SendData", "Failed to send data");
            e.printStackTrace();
        }
    }


    public void sendData(Messages msg, String ip, String mac)
    {
        if (this.socket == null) return;

        try {
            Log.i("SendData", "Writing to the socket !");

            this.nos.write(msg.ordinal() + " " + ip + " " + mac);
            this.nos.flush();
            Log.i("SendData", "You walking ani");

        } catch (IOException e)
        {
            Log.i("SendData", "Failed to send data (+a string content)");
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Object[] values)
    {
        Log.i("Progress", "Something is happening somehow");
    }

    private void editText(String content)
    {

    }
}
