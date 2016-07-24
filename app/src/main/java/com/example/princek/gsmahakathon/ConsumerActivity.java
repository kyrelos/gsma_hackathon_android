package com.example.princek.gsmahakathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ConsumerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        FloatingActionButton withdrwaFab = (FloatingActionButton) findViewById(R.id.withdrawFab);
        withdrwaFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consumerWithdrawIntent = new Intent(getApplicationContext(), ConsumerWithdrawActivity.class);
                startActivity(consumerWithdrawIntent);
            }
        });

        FloatingActionButton consumerSendFab = (FloatingActionButton) findViewById(R.id.sendMoneyFab);
        consumerSendFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consumerSendIntent = new Intent(getApplicationContext(), ConsumerSendActivity.class);
                startActivity(consumerSendIntent);
            }
        });

        FloatingActionButton consumerStatementFab = (FloatingActionButton) findViewById(R.id.miniStatementFab);
        consumerStatementFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consumerStatement = new Intent(getApplicationContext(), ConsumerStatementActivity.class);
                startActivity(consumerStatement);
            }
        });
    }

}
