package com.example.robotechvalley;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class myRecyAdapter extends RecyclerView.Adapter<myRecyAdapter.MyViewHolder> {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.myrow,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.row.getContext(),ProductDeatails.class);

                holder.row.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        ConstraintLayout row;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.IdProductImage);
            productName = itemView.findViewById(R.id.IdProductName);
            productPrice = itemView.findViewById(R.id.IdProductPrice);
            row = itemView.findViewById(R.id.IdRow);


        }
    }
}
