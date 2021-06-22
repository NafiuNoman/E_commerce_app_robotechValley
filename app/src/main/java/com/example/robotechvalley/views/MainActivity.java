package com.example.robotechvalley.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robotechvalley.R;
import com.example.robotechvalley.adapters.MyFireBaseRecyclerAdapter;
import com.example.robotechvalley.adapters.SliderAdapter;
import com.example.robotechvalley.models.ModelClass;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {

    SliderView sliderView;
    RecyclerView recyclerView;

    private FirebaseAuth mAuth;


    MyFireBaseRecyclerAdapter myFireBaseRecyclerAdapter;

    SliderAdapter sliderAdapter;

    NavigationView navigationView;
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    int[] images;

//    TextView navName,navEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.IdDrawer);
//        navigationView = findViewById(R.id.IdNavigation);
        toolbar = findViewById(R.id.IdToolBar);

        recyclerView = findViewById(R.id.IdRecycler);

        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();

//        Log.d("Name:",firebaseUser.getEmail());
//        Log.d("Name2:",firebaseUser.getDisplayName());

        sliderWork();

        recyclerWork();

        drawerWork();


    }


    private void drawerWork() {


        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.IdNavigation);

        View headerVIew = navigationView.getHeaderView(0);

        TextView navName = headerVIew.findViewById(R.id.IdNavName);
        TextView navEmail = headerVIew.findViewById(R.id.IdNavEmailId);

        String name = getIntent().getExtras().getString("username");
//        Toast.makeText(this, "from main::"+name, Toast.LENGTH_SHORT).show();
        String email = getIntent().getExtras().getString("useremail");

        //        navEmail.setText(email);
        navName.setText(name);
        navEmail.setText(email);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuAdmin:

                        Intent intent = new Intent(MainActivity.this, adminLogIn.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Tutorials:
                        Toast.makeText(MainActivity.this, "Tutorials", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Courses:
                        Toast.makeText(MainActivity.this, "Courses", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Project:
                        Toast.makeText(MainActivity.this, "Project", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.blog:
                        Toast.makeText(MainActivity.this, "Blog", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ambassador:
                        Toast.makeText(MainActivity.this, "ambassador", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.team:
                        Toast.makeText(MainActivity.this, "team", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart:
                        Toast.makeText(MainActivity.this, "cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contact:
                        Toast.makeText(MainActivity.this, "contact", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:

                        mAuth.signOut();

                        Intent intent1 = new Intent(MainActivity.this, logIn.class);
                        startActivity(intent1);

                        Toast.makeText(MainActivity.this, "logOut", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        Toast.makeText(MainActivity.this, "okokokokok", Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });
    }

    private void recyclerWork() {

        FirebaseRecyclerOptions<ModelClass> options =
                new FirebaseRecyclerOptions.Builder<ModelClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), ModelClass.class)
                        .build();

        myFireBaseRecyclerAdapter = new MyFireBaseRecyclerAdapter(options);


        recyclerView.setAdapter(myFireBaseRecyclerAdapter);


    }

    private void sliderWork() {

        images = new int[]

                {
                        R.drawable.one,
                        R.drawable.two,
                        R.drawable.three,
                        R.drawable.four,
                        R.drawable.five
                };

        sliderView = findViewById(R.id.IdImageSlider);

        sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


    }


    @Override
    protected void onStop() {
        super.onStop();

        myFireBaseRecyclerAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myFireBaseRecyclerAdapter.startListening();
    }
}