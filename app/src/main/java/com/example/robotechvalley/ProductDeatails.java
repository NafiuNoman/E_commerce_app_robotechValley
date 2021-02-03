package com.example.robotechvalley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProductDeatails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatails);
    }

    public void AddToCart(View view) {

        Intent intent = new Intent(view.getContext(),CartAdd.class);
        startActivity(intent);


    }
}