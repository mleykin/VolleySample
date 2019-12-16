package com.example.mleykin.volleysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    Button bOK;
    TextView tvDate;
    String url = "http://date.jsontest.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bOK = (Button)findViewById(R.id.bGetFromServer);
        tvDate = (TextView)findViewById(R.id.dateandtime);
    }

    public void onclick(View v) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest
                        (StringRequest.Method.GET,
                        url,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                tvDate.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something bad happened!", Toast.LENGTH_LONG).show();
                            }
                        });
        request.setTag("TAG");
        queue.add(request);
    }



}
