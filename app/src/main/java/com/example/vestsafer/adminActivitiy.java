package com.example.vestsafer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class adminActivitiy extends AppCompatActivity {

    ConstraintLayout location_mgr;
    ConstraintLayout managementButton;
    ConstraintLayout safety_mgr;
    TextView logout_mgr;
    TextView text_calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activitiy);
        location_mgr = findViewById(R.id.location_mgr);
        managementButton= findViewById(R.id.managementButton);
        logout_mgr = findViewById(R.id.logout_mgr);
        text_calendar = findViewById(R.id.text_calendar);
        safety_mgr= findViewById(R.id.safety_mgr);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");

        logout_mgr.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        logout_mgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(adminActivitiy.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(adminActivitiy.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        location_mgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask2().execute();
            }
        });


        // IdText.setText(userID);
        // PassWordText.setText(userPassword);


        safety_mgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask3().execute();

            }
        });

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask().execute();
            }
        });

        //공사일정 수정하기
        text_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(adminActivitiy.this, WorkerCalendarView.class);
                adminActivitiy.this.startActivity(intent1);
            }
        });

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://vestsafer.dothome.co.kr/List.php";
        }

        @Override
        protected String doInBackground(Void...voids){
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }


        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(adminActivitiy.this, ManagementActivity.class);
            intent.putExtra("userList", result);
            adminActivitiy.this.startActivity(intent);
        }


    }


    class BackgroundTask2 extends AsyncTask<Void, Void, String>{
        String target2;
        @Override
        protected void onPreExecute(){
            target2 = "http://vestsafer.dothome.co.kr/WorkerLocationMarker.php";
        }
        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target2);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(adminActivitiy.this, ManagerMapActivity.class);
            intent.putExtra("userLocationList", result);
            adminActivitiy.this.startActivity(intent);
        }

    }

    class BackgroundTask3 extends AsyncTask<Void, Void, String> {
        String target3;

        @Override
        protected void onPreExecute(){
            target3 = "http://vestsafer.dothome.co.kr/bluetoothList.php";
        }

        @Override
        protected String doInBackground(Void...voids){
            try{
                URL url = new URL(target3);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }


        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(adminActivitiy.this, safetymgrActivity.class);
            intent.putExtra("bluetoothList", result);
            adminActivitiy.this.startActivity(intent);
        }


    }






}