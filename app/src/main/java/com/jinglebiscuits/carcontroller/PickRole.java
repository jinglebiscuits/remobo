package com.jinglebiscuits.carcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PickRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_role);
    }

    public void pickRider(View view) {
        Intent intent = new Intent(this, ConnectToCar.class);
        startActivity(intent);
    }
}
