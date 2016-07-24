package com.example.princek.gsmahakathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton consumerFab = (FloatingActionButton) findViewById(R.id.consumerfab);
        consumerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consumerIntent = new Intent(getApplicationContext(), ConsumerActivity.class);
                startActivity(consumerIntent);
            }
        });

        FloatingActionButton agentFab = (FloatingActionButton) findViewById(R.id.agentFab);
        agentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agentIntent = new Intent(getApplicationContext(), AgentActivity.class);
                startActivity(agentIntent);
            }
        });


    }

}
