package com.example.robotechvalley.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.robotechvalley.R;
import com.example.robotechvalley.models.ModelClass;

public class CartAdd extends AppCompatActivity {

      int count=1;
      int perPiecePrice;
      int Total = perPiecePrice;
     TextView countView,totalPrice;
     Button addButton,minusButton;
     ModelClass modelClass;
     ImageView cartImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_add);

        countView = findViewById(R.id.IdCartCount);
        totalPrice = findViewById(R.id.IdCartTotal);
        addButton = findViewById(R.id.IdCartAddButton);
        minusButton = findViewById(R.id.IdCartMinus);
        cartImage  = findViewById(R.id.IdCartImage);

        String name = getIntent().getExtras().getString("name");
        String picUrl = getIntent().getExtras().getString("image");
        String price = getIntent().getExtras().getString("price");

        Log.d("cart price.....",price);




        Glide.with(cartImage.getContext()).load(picUrl).into(cartImage);

        totalPrice.setText(price);
        perPiecePrice = Integer.parseInt(price);










//                String sPrice= modelClass.getPrice();
//
//        Log.d("CartAdd","into the price"+sPrice);
//               perPiecePrice = Integer.parseInt(sPrice);
//

//        modelClass = (ModelClass) getIntent().getSerializableExtra("productModel");














    }

    public void AddCount(View view)

    {


        count++;
        countView.setText(""+count);
        Total = count* perPiecePrice;
        totalPrice.setText(""+Total);


    }

    public void MinusCount(View view) {


       if(count<=1)
       {

       }
       else {

           count--;
           countView.setText(""+count);
           Total = Total- perPiecePrice;
           totalPrice.setText(""+Total);

       }





    }
}