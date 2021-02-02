package com.example.robotechvalley;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {

    SliderView sliderView;
    RecyclerView recyclerView;

    myRecyAdapter recyAdapter;
    SliderAdapter sliderAdapter;

    NavigationView navigationView;
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    int [] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new int[]

                {
                        R.drawable.one,
                        R.drawable.two,
                        R.drawable.three,
                        R.drawable.four,
                        R.drawable.five
                };


        sliderView = findViewById(R.id.IdImageSlider);
        drawerLayout =findViewById(R.id.IdDrawer);
        toolbar =findViewById(R.id.IdToolBar);

        recyclerView = findViewById(R.id.IdRecycler);

        recyAdapter = new myRecyAdapter();
        sliderAdapter = new SliderAdapter(images);

        recyclerView.setAdapter(recyAdapter);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

//        navigationView =findViewById(R.id.IdNavigation);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();






    }
}