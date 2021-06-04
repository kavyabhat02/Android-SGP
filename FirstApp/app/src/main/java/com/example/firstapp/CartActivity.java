package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    static List<DataClass> list = new ArrayList<>();
    static List<Integer> quantity = new ArrayList<>();
    static int total = 0;
    static int countOfItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CartRVAdapter adapter = new CartRVAdapter(list, quantity, countOfItems, total, new CartRVAdapter.OnClickListener() {

            @Override
            public void addOrder(DataClass data) {
                CartActivity.this.addOrder(data);
            }

            @Override
            public void reduceItems(DataClass data) {
                CartActivity.this.reduceItems(data);
            }

            @Override
            public void deleteFromCart(DataClass data) {
                CartActivity.this.deleteFromCart(data);
            }
        });
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void addOrder(DataClass data) {
        String name = data.name;
        int cost = data.cost;
        int img = data.img_name;

        int flag = 0, pos=0;
        for(int x=0; x<list.size(); x++) {
            if(list.get(x).img_name == img) {//check if item is already present in cart
                flag = 1;
                pos = x;
                break;
            }
        }

        if(flag == 0) {
            list.add(new DataClass(name, cost, img));
            quantity.add(1);
        }
        else
        {
            list.set(pos,data);
            quantity.set(pos, quantity.get(pos)+1);
        }
        countOfItems += 1;
        total += data.cost;
    }

    public void reduceItems(DataClass data)
    {
        int position = list.indexOf(data);

        //if quantity is 0, remove item from cart.
        countOfItems -= 1;
        quantity.set(position, quantity.get(position)-1);
        if(quantity.get(position) == 0) {
            list.remove(position);
            quantity.remove(position);
        }

        total -= data.cost;
    }

    public void deleteFromCart(DataClass data) {
        int position = list.indexOf(data);
        countOfItems -= quantity.get(position);
        total -= data.cost * quantity.get(position);
        quantity.remove(position);
        list.remove(position);
    }
}
