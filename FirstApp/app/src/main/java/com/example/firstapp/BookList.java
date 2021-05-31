package com.example.firstapp;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookList extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener
{
    static List<DataClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        list.add(new DataClass("The Lord of the Rings - Book 1", 500, R.drawable.book_p1));
        list.add(new DataClass("The Lord of the Rings - Book 2", 500, R.drawable.book_p2));
        list.add(new DataClass("The Lord of the Rings - Book 3", 500, R.drawable.book_p3));
        list.add(new DataClass("His Dark Materials - Book 1", 690, R.drawable.book_p4));
        list.add(new DataClass("His Dark Materials - Book 2", 500, R.drawable.book_p5));
        list.add(new DataClass("His Dark Materials - Book 3", 860, R.drawable.book_p6));
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