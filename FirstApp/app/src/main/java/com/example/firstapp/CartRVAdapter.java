package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVAdapter.ViewHolder> {
    private List<DataClass> list;
    private List<Integer> quantity;

    private OnClickListener clickListener;
    static int totalCost;
    static int counter;

    CartRVAdapter(List<DataClass> myList, List<Integer> myQuantity, int tcount, int cost, OnClickListener myListener) {
        list = myList;
        quantity = myQuantity;
        counter = tcount;
        totalCost = cost;
        clickListener = myListener;
    }

    @NonNull
    @Override
    public CartRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == R.layout.texts)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.texts, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRVAdapter.ViewHolder holder, int position) {
        char ch = 8377;
        if(position == list.size()) {
            if(totalCost>=0) {
                holder.total.setText(ch+ Integer.toString(totalCost));
                if(counter!=1)
                    holder.text.setText("SubTotal ("+Integer.toString(counter)+" items): ");
                else
                    holder.text.setText("SubTotal ("+Integer.toString(counter)+" item): ");
            }
        }
        else {
            DataClass data = list.get(position);
            int q = quantity.get(position);
            holder.name.setText(data.name);
            holder.cost.setText(ch+" "+Integer.toString(data.cost));
            holder.imageView.setBackgroundResource(data.img_name);
            holder.number.setText(Integer.toString(q));
        }
    }

    @Override
    public int getItemCount()
    {
        return (list.size()+1);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == list.size())
            return R.layout.texts;
        else
            return R.layout.cartcard;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView cost;
        TextView number;
        ImageView imageView;
        ImageButton trash;
        Button plus, minus;
        TextView total;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            cost = itemView.findViewById(R.id.cost);
            number = itemView.findViewById(R.id.quantity);
            imageView = itemView.findViewById(R.id.imageView);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            trash = itemView.findViewById(R.id.trash);

            total = itemView.findViewById(R.id.total);
            text = itemView.findViewById(R.id.text);

            if(itemView.findViewById(R.id.trash) != null) {
                trash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataClass data = list.get(getAdapterPosition());

                        totalCost -= (list.get(getAdapterPosition()).cost * quantity.get(getAdapterPosition()));
                        counter -= quantity.get(getAdapterPosition());

                        clickListener.deleteFromCart(data);

                        notifyDataSetChanged();
                    }
                });

                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataClass data = list.get(getAdapterPosition());
                        totalCost -= list.get(getAdapterPosition()).cost;
                        counter -= 1;
                        clickListener.reduceItems(data);
                        notifyDataSetChanged();
                    }
                });

                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataClass data = list.get(getAdapterPosition());
                        totalCost += list.get(getAdapterPosition()).cost;
                        counter += 1;
                        clickListener.addOrder(data);
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }

    public interface OnClickListener {
        void addOrder(DataClass data);
        void reduceItems(DataClass data);
        void deleteFromCart(DataClass data);
    }
}
