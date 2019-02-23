package com.example.loginpage;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    Button login, cancel;
    EditText username, password;
    TextView tx1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        login =  findViewById(R.id.Login);
        cancel = findViewById(R.id.Cancel);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        tx1 = findViewById(R.id.Intermon);
        MapCreator map = new MapCreator();
        map.createMap();
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ClientSocket clientSocket = new ClientSocket();
                JSONObject client = new JSONObject();
                try
                {
                    client.put("key", "login");
                    client.put("username", username.getText().toString());
                    client.put("password", password.getText().toString());
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
                try
                {
                    clientSocket.execute(client).get();
                }
                catch (InterruptedException | ExecutionException e)
                {
                    e.printStackTrace();
                }

                String message = serverCommunication.getServerMessage();
                System.out.println(message);
                
                if(message.equals(("Aaron")))
                {
                    Toast.makeText(MainActivity.this, "login success: " + message, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                }
            }
        });
        
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
