package com.example.robotechvalley.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.robotechvalley.R;
import com.example.robotechvalley.models.ModelClass;
import com.example.robotechvalley.views.CartAdd;

public class ProductDeatails extends AppCompatActivity {

    ImageView productImage;
    TextView productPrice,productName,productDescription;
    ModelClass modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatails);

        productDescription = findViewById(R.id.IdProductDescription);
        productName = findViewById(R.id.IdProductName);
        productPrice = findViewById(R.id.IdProductDetailsPrice);
        productImage = findViewById(R.id.IdProductImage);


        modelClass = (ModelClass) getIntent().getSerializableExtra("productModel");

        productDescription.setText(modelClass.getDescription());
        productName.setText(modelClass.getProductName());
        productPrice.setText(modelClass.getPrice());
        Glide.with(productImage.getContext()).load(modelClass.getPicUrl()).into(productImage);



    }

    public void AddToCart(View view) {

        Intent intent = new Intent(view.getContext(), CartAdd.class);
        Log.d("AddToCart: ","working111111");


        intent.putExtra("image",modelClass.getPicUrl());
        intent.putExtra("name",modelClass.getProductName());
        intent.putExtra("price",modelClass.getPrice());


        Log.d("AddToCart: ","working001");
        startActivity(intent);

        Log.d("AddToCart: ","working");


    }
}