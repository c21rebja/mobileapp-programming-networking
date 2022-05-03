package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private Button dataButton;
    private Button clearButton;

    RecyclerView recyclerView;
    private final String TAG = "===";

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    public List<Mountain> listOfMountains;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new JsonFile(this, this).execute(JSON_FILE); //reads from file
        //new JsonTask(this).execute(JSON_URL);

        dataButton = findViewById(R.id.add_data_button);
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JsonTask(MainActivity.this).execute(JSON_URL);
            }
        });

        clearButton = findViewById(R.id.clear_data_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOfMountains.clear();
                adapter.notifyDataSetChanged();
            }
        });

        listOfMountains = new ArrayList<Mountain>();

        recyclerView = findViewById(R.id.recycle);
        adapter = new MyAdapter(listOfMountains);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);

        Gson gson = new Gson();
        Type type = new TypeToken<List<Mountain>>() {}.getType();
        List<Mountain> tempList = gson.fromJson(json, type);
        if(tempList != null) {
            Log.d(TAG, "Number of elements: " + tempList.size());
            Log.d(TAG, "Element 0: " + tempList.get(0).toString());
            listOfMountains.addAll(tempList);
            adapter.notifyDataSetChanged();
        }
        else {
            Log.d(TAG, "There were no elements to show.");
        }
    }
}
