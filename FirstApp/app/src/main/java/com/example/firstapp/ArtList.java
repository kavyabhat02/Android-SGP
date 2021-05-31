package com.example.firstapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArtList extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener
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
        list.add(new DataClass("Crayons", 425, R.drawable.art1));
        list.add(new DataClass("Watercolor Paint", 300, R.drawable.art2));
        list.add(new DataClass("Sketchbook", 330, R.drawable.art3));
        list.add(new DataClass("Graphite Pencils", 1300, R.drawable.art4));
        list.add(new DataClass("Colored Pencils", 1000, R.drawable.art5));
        list.add(new DataClass("Paintbrushes", 1000, R.drawable.art6));
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
