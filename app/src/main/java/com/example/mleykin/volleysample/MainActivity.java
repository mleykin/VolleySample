package com.example.mleykin.volleysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bOK;
    TextView tvDate;
    private ArrayList<Pair<String, String>> Data;

    RecyclerView JokesLayout;
    RecyclerView.Adapter lJokesLayoutAdapter;
    RecyclerView.LayoutManager JokesLayoutManager;

    public static String url = "https://official-joke-api.appspot.com/random_joke";

    APIListener apiListener = new APIListener();
    APIErrorListener apiErrorListener = new APIErrorListener();

    class APIListener implements Response.Listener {
        @Override
        public void onResponse(Object response) {
            Data.add(lJokesLayoutAdapter.getItemCount(),
                    parseJson(response.toString()));
            lJokesLayoutAdapter.notifyDataSetChanged();
        }
    }

    class APIErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"Something bad happened!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bOK = (Button)findViewById(R.id.bGetFromServer);
        JokesLayout = (RecyclerView) findViewById(R.id.jokes_recycler_view);

        Data = new ArrayList<>();
        lJokesLayoutAdapter = new JokesLayoutAdapter(Data);
        JokesLayout.setAdapter(lJokesLayoutAdapter);
        JokesLayoutManager = new LinearLayoutManager(this);
        JokesLayout.setLayoutManager(JokesLayoutManager);

        tvDate = (TextView)findViewById(R.id.dateandtime);
    }

    public void onclick(View v) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest
                        (StringRequest.Method.GET,
                        url, apiListener, apiErrorListener);
        request.setTag("TAG");
        queue.add(request);
    }

    public static Pair<String, String> parseJson(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson( response, JsonObject.class);
        String setupString = jsonObject.get("setup").toString();
        String punchlineString = jsonObject.get("punchline").toString();
        return new Pair<>(setupString, punchlineString);
    }
}
