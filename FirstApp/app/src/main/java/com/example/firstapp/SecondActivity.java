package com.example.firstapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity
{
    List<DataClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        list.add(new DataClass("Kavya", "121212", R.drawable.p1));
        list.add(new DataClass("ABC", "343434", R.drawable.p2));
        list.add(new DataClass("PQR", "565656", R.drawable.p3));
        list.add(new DataClass("XYZ", "787878", R.drawable.p4));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}