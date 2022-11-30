package com.example.vestsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class safetymgrActivity extends AppCompatActivity {

    private ListView listView;
    private MagerSafetyAdapter adapter;
    private List<Bluetooth> bluetoothList;
    private List<Bluetooth> saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safetymgr);


        Intent intent = getIntent();
        listView= (ListView) findViewById(R.id.ListView);
        bluetoothList = new ArrayList<Bluetooth>();
        saveList = new ArrayList<Bluetooth>();
        adapter = new MagerSafetyAdapter(getApplicationContext(), bluetoothList,this,saveList);
        listView.setAdapter(adapter);

        try
        {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("bluetoothList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String userID, str_btn, str_dust, str_co,str_hum,str_temp;

            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                str_btn = object.getString("str_btn");
                str_dust = object.getString("str_dust");
                str_co = object.getString("str_co");
                str_hum = object.getString("str_hum");
                str_temp = object.getString("str_temp");
                Bluetooth bluetooth = new Bluetooth(userID, str_btn, str_dust, str_co,str_hum,str_temp);
                count++;

                if(!userID.equals("admin"))
                {
                    bluetoothList.add(bluetooth);
                    saveList.add(bluetooth);
                }
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}