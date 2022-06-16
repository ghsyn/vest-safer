package com.example.smartvest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WorkerManualActivity extends AppCompatActivity {
    ImageView back_manual_worker;
    ConstraintLayout manual_smartvest;
    ConstraintLayout manual_nangan;
    ConstraintLayout manual_electrictiy;
    ConstraintLayout manual_load;
    ConstraintLayout manual_transport;
    ConstraintLayout manual_test;
    ImageView home_manual_worker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_manual);

        back_manual_worker = findViewById(R.id.back_manual_worker);
        home_manual_worker = findViewById(R.id.home_manual_worker);
        manual_nangan = findViewById(R.id.manual_nangan);
        manual_electrictiy=findViewById(R.id.manual_electrictiy);
        manual_load=findViewById(R.id.manual_load);
        manual_transport=findViewById(R.id.manual_transport);
        manual_test=findViewById(R.id.manual_test);
        home_manual_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        back_manual_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        manual_nangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManualNanganActivity.class);
                startActivity(intent);
            }
        });
        manual_electrictiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManualElectricityActivity.class);
                startActivity(intent);
            }
        });
        manual_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManualloadActivity.class);
                startActivity(intent);
            }
        });
        manual_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManulTransport.class);
                startActivity(intent);
            }
        });
        manual_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManulTest.class);
                startActivity(intent);
            }
        });


    }
}