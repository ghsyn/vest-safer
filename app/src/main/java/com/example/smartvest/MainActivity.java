package com.example.smartvest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText IdText;
    EditText PasswordText;
    TextView registerbutton;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText IdText = (EditText) findViewById(R.id.IdText);
        final EditText PasswordText = (EditText) findViewById(R.id.PasswordText);
        final Button button = (Button) findViewById(R.id.button);
        final TextView registerbutton = (TextView) findViewById(R.id.registerbutton);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String userID = IdText.getText().toString();
               final String userPassword= PasswordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success)
                            {
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                if(!userID.equals("admin"))
                                {
                                    Intent intent = new Intent(MainActivity.this, WelcomActivity.class);
                                    intent.putExtra("userID",userID);
                                    intent.putExtra("userPassword",userPassword);
                                    MainActivity.this.startActivity(intent);


                                }
                                else
                                {

                                    Intent intent = new Intent(MainActivity.this, adminActivitiy.class);
                                    intent.putExtra("userID",userID);
                                    intent.putExtra("userPassword",userPassword);
                                    MainActivity.this.startActivity(intent);
                                }
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("다시시도",null)
                                        .create()
                                        .show();

                            }

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}