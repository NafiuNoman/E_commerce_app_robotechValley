package com.example.robotechvalley.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.robotechvalley.R;
import com.example.robotechvalley.models.ModelClass;
import com.example.robotechvalley.views.ProductDeatails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFireBaseRecyclerAdapter extends FirebaseRecyclerAdapter<ModelClass, MyFireBaseRecyclerAdapter.MyViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyFireBaseRecyclerAdapter(@NonNull FirebaseRecyclerOptions<ModelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyFireBaseRecyclerAdapter.MyViewHolder holder, int position, @NonNull ModelClass model) {

        holder.price.setText(model.getPrice());
        holder.productName.setText(model.getProductName());
        Glide.with(holder.productImage.getContext()).load(model.getPicUrl()).into(holder.productImage);

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.row.getContext(), ProductDeatails.class);

                intent.putExtra("productModel", (Serializable) model);
                holder.row.getContext().startActivity(intent);
            }
        });

    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.myrow, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        TextView productName, price;
        ConstraintLayout row;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.IdRowProductName);
            price = itemView.findViewById(R.id.IdRowProductPrice);
            productImage = itemView.findViewById(R.id.IdRowProductImage);
            row = itemView.findViewById(R.id.IdRow);

        }
    }

}
