package com.example.netcut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {

    private Button stop;
    private Button all;
    private Button allIp;
    private Button allMac;
    private Button start;

    private ClientTCP client;

    private NetworkTask nt;

    private ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Needed in order to use the NetworkTask
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        //Button b = new Button();
        this.all = findViewById(R.id.button1);
        this.start = findViewById(R.id.button);

        this.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (nt != null)
                 nt.sendData(Messages.ALL);
                nt.sendData(Messages.KILL, "192.168.0.5", "AA:BB:CC:DD:EE");
            }
        });
        this.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                nt = NetworkTask.getInstance();
                nt.execute();

            }
        });

//        this.buttons = new ArrayList<Button>();
//
//        for (int i = 0; i < 5; ++i)
//        {
//            int id = getResources().getIdentifier("button"+i, "id", getPackageName());
//            Button b = findViewById(id);
//            if (b != null)
//                buttons.add(b);
//        }
//
//        client = new ClientTCP();
//
//        this.stop = findViewById(R.id.button2);
//        this.allMac = findViewById(R.id.button3);
//        this.allIp = findViewById(R.id.button4);
//
//        this.stop.setText("Stop");
//        this.all.setText("All");
//        this.allIp.setText("All Ip");
//        this.allMac.setText("All Mac");
//
//
//
//        View.OnClickListener ocl = new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//
//                if (v.getId() == stop.getId())
//                {
//                    client.send(Messages.STOP.ordinal());
//                }
//                if (v.getId() == all.getId())
//                {
//                    client.send(Messages.ALL.ordinal());
//                }
//                if (v.getId() == allIp.getId())
//                {
//                    client.send(Messages.ALLIP.ordinal());
//                }
//                if (v.getId() == allMac.getId())
//                {
//                    client.send(Messages.ALLMAC.ordinal());
//                }
//
//            }
//        };
//        for (Button b : buttons)
//            b.setOnClickListener(ocl);




    }
}
