package com.example.firstapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GameList extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener
{
    static List<DataClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, this::addToCart);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void setList()
    {
        list.add(new DataClass("Monopoly", 800, R.drawable.game1));
        list.add(new DataClass("Scrabble", 900, R.drawable.game2));
        list.add(new DataClass("The Game of Life", 1000, R.drawable.game3));
        list.add(new DataClass("Connect Four", 600, R.drawable.game4));
        list.add(new DataClass("Downfall", 200, R.drawable.game5));
        list.add(new DataClass("Othello", 600, R.drawable.game6));
    }

    @Override
    public void addToCart(DataClass data)
    {
        int position = list.indexOf(data);
        Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
        list.set(position, data);
        new CartActivity().addOrder(data);
    }
}