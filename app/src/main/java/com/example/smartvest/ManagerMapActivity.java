package com.example.smartvest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartvest.databinding.ActivityManagerMapBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagerMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityManagerMapBinding binding;
    ImageView back_location_mgr;
    Intent intent = getIntent();
    //유저정보
    private List<UserLocation> userLocationList;
    private List<UserLocation> saveLocationList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_map);
        Intent intent = getIntent();

        //유저 위치-> 리스트로 저장하기
        userLocationList = new ArrayList<UserLocation>();
        saveLocationList = new ArrayList<UserLocation>();

        try
        {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userLocationList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String userID, str_datetime;
            Double str_latitude, str_longitude;

            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                str_datetime = object.getString("str_datetime");
                str_latitude = object.getDouble("str_latitude");
                str_longitude = object.getDouble("str_longitude");

                UserLocation userLocation = new UserLocation(userID, str_datetime, str_latitude, str_longitude);
                if(!userID.equals("admin"))
                {
                    userLocationList.add(userLocation);
                    saveLocationList.add(userLocation);
                }
                count++;
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }


        back_location_mgr = findViewById(R.id.back_location_mgr);
        //뒤로가기
        back_location_mgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_manager);
        mapFragment.getMapAsync(this);



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap ) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(35.16, 126.85);
        //mMap.addMarker(new MarkerOptions().position(latLng).title("광주광역시"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

        for(int i = 0; i< saveLocationList.size(); i++)
        {
            String userID = saveLocationList.get(i).getUserID();
            String datetime =saveLocationList.get(i).getStr_datetime();
            double lat = saveLocationList.get(i).str_latitude;
            double lon = saveLocationList.get(i).str_longitude;
            final MarkerOptions  marker = new MarkerOptions()
                    .position(new LatLng(lat,lon))
                    .title(userID +" "+datetime);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    mMap.addMarker(marker);
                }

            });
            //LatLng latLng = new LatLng(lat , lon);
            //mMap.addMarker(new MarkerOptions().position(latLng).title(userID + datetime));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            mMap.setMyLocationEnabled(true);
        else{
            checkLocationPermissionWithRationale();
        }

    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(ManagerMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }





}





