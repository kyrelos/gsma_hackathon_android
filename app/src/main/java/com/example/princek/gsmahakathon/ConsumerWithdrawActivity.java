package com.example.princek.gsmahakathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConsumerWithdrawActivity extends AppCompatActivity {
    private ViewGroup progressBackground;
    private EditText edit_agent_number;
    private String agent_number;
    private EditText edit_pin_number;
    private String pin_number;
    private EditText edit_amount;
    private String amount;
//    private OkHttpClient client = new OkHttpClient();
//    public static final MediaType myJSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_withdraw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        edit_agent_number = (EditText) findViewById(R.id.editAgentNumber);
        edit_pin_number = (EditText) findViewById(R.id.editPin);
        edit_amount = (EditText) findViewById(R.id.editAmount);

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
        progressBackground = (ViewGroup) findViewById(R.id.progressBackground);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.withdrawSubmit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                JSONObject sender = new JSONObject();
                JSONObject receiver = new JSONObject();
                JSONArray nested_array = new JSONArray();

                agent_number = edit_agent_number.getText().toString();
                if( agent_number.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter Agents Phone number", Toast.LENGTH_LONG).show();
                    return;
                }

                pin_number = edit_pin_number.getText().toString();
                if( pin_number.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter Pin", Toast.LENGTH_LONG).show();
                    return;
                }

                amount = edit_amount.getText().toString();
                if( amount.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter Amount", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    sender.put("key", "MSISDN");
                    sender.put("value", "255718533294" );
                    receiver.put("key", "MSISDN");
                    receiver.put("value", agent_number );
                    jsonObject.put("amount", amount);
                    jsonObject.put("currency", "TZS");
                    jsonObject.put("type", "withdrawal");
//                    jsonObject.put("subtype", "subtype");
                    jsonObject.put("date", "now");
                    jsonObject.put("debitParty", new JSONArray().put(sender));
                    jsonObject.put("creditParty",new JSONArray().put(receiver));


                }catch (JSONException e){
                    e.printStackTrace();
                }

                MyHttpAsync myHttpAsync = new MyHttpAsync();
                myHttpAsync.execute(jsonObject);
            }
        });
    }

    private class MyHttpAsync extends AsyncTask<JSONObject, String, Boolean> {
        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            progressBackground.setVisibility(View.GONE);
            if (b){
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                Intent consumerIntent = new Intent(getApplicationContext(), LandingActivity.class);
                startActivity(consumerIntent);
            }else{
                Toast.makeText(getApplicationContext(), "Transaction failure", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBackground.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Boolean b) {
            super.onCancelled(b);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Boolean doInBackground(JSONObject... jsonObject) {


            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();


            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonObject[0].toString());
            System.out.println("ddddddddddddd"+jsonObject[0].toString());
//            RequestBody body = RequestBody.create(mediaType, "{\n  \"amount\": 1000,\n  \"currency\": \"TZS\",\n  \"type\": \"withdrawal\",\n  \"debitParty\": [\n    {\n      \"key\": \"MSISDN\",\n      \"value\": \"255718533759\"\n    }\n  ],\n  \"creditParty\": [\n    {\n      \"key\": \"MSISDN\",\n      \"value\": \"255718533294\"\n    }\n  ]\n}");
            Request request = new Request.Builder()
                    .url("http://41.222.176.233:8080/v0.14/MM/transactions")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("accept", "application/json")
                    .addHeader("x-correlationid", "1234")
                    .addHeader("date", "now")
                    .addHeader("authorization", "Basic MjU1NzE4NTMzNzU5OjAwMDA=")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "7ac98a31-9215-d52a-e053-ecdfa4763a38")
                    .build();

//            Response response = client.newCall(request).execute();


//            RequestBody body = RequestBody.create(myJSON, jsonObject[0].toString());
//            Request request = new Request.Builder()
//                    .url("http://41.222.176.233:8080/v0.14/MM/transactions")
//                    .post(body)
//                    .build();
            try {
                Response response = client.newCall(request).execute();
                System.out.println(response.code()+" "+response.body().string());
                return (response.isSuccessful() && response.code()==201) ;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }


        }
    }


}
