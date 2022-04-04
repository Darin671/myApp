package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;



public class MainActivity extends AppCompatActivity {
    private Socket socket;
    TextView text;
    Button bt;
    EditText edit;
    Button bt2;
    Socket s;

    private static final int SERVERPORT = 6666;
    private static final String SERVER_IP = "192.168.1.102";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         text=(TextView) findViewById(R.id.mytext);
         bt=(Button)findViewById(R.id.mybutton);
         bt2=(Button)findViewById(R.id.mybutton2);
         edit=(EditText)findViewById(R.id.et);

         bt.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       try {

                                           DataOutputStream output = new DataOutputStream(s.getOutputStream());
                                           output.writeUTF(edit.getText().toString());
                                           DataInputStream input = new DataInputStream(s.getInputStream());
                                           String str = input.readUTF();
                                           text.setText(str);
                                           Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();


                                       } catch (Exception e) {
                                           text.setText("Bye");
                                           e.printStackTrace();


                                       }
                                   }
                                   });

                 bt2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         try {
                             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                             StrictMode.setThreadPolicy(policy);
                             s = new Socket("192.168.1.102", 6666);
                             text.setText("Write your Message");

                         } catch (Exception e) {
                             text.setText("Bye2");
                             e.printStackTrace();


                         }


                     }
                 });

             }
         }