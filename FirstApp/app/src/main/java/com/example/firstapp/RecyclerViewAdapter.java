package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Method;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private static List<DataClass> list;
    private OnItemClickListener clickListener;
    RecyclerViewAdapter(List<DataClass> myList, OnItemClickListener myListener)
    {
        list = myList;
        this.clickListener = myListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position)
    {
        DataClass data = list.get(position);
        char ch = 8377;
        holder.name.setText(data.name);
        holder.cost.setText(ch+" "+Integer.toString(data.cost));
        holder.imageView.setBackgroundResource(data.img_name);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView cost;
        ImageView imageView;
        Button cart;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            cost = itemView.findViewById(R.id.cost);
            imageView = itemView.findViewById(R.id.imageView);
            cart = itemView.findViewById(R.id.toCart);

            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.addToCart(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void addToCart(DataClass data);
    }
}
