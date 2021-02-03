package com.example.robotechvalley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CartAdd extends AppCompatActivity {

      int count=1;
      int perPiecePrize = 100;
      int Total = perPiecePrize;
     TextView countView,totalPrice;
     Button addButton,minusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_add);

        countView = findViewById(R.id.IdCartCount);
        totalPrice = findViewById(R.id.IdCartTotal);
        addButton = findViewById(R.id.IdCartAddButton);
        minusButton = findViewById(R.id.IdCartMinus);






    }

    public void AddCount(View view)

    {


        count++;
        countView.setText(""+count);
        Total = count*perPiecePrize;
        totalPrice.setText(""+Total);


    }

    public void MinusCount(View view) {


       if(count<=0)
       {

       }
       else {

           count--;
           countView.setText(""+count);
           Total = Total-perPiecePrize;
           totalPrice.setText(""+Total);

       }





    }
}