package com.example.robotechvalley.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.robotechvalley.R;
import com.example.robotechvalley.models.ModelClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class adimn extends AppCompatActivity {


    EditText productName,productDescription,price;
    ImageView save,productImage;
    ProgressBar progressBar;
    DatabaseReference reference;

    String key,picUrl;
    Uri filePath;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adimn);

        productName = findViewById(R.id.IdAdminProductName);
        productDescription = findViewById(R.id.IdAdimDescription);
        price = findViewById(R.id.IdAdminPrice);
        save = findViewById(R.id.IdImageSave);
        productImage = findViewById(R.id.IdAdminProductImage);

        progressBar = findViewById(R.id.IdAdminProgressBar);


        reference = FirebaseDatabase.getInstance().getReference("Products");
        key = reference.push().getKey();








    }

    public void addProductImage(View view) {

        Dexter.withContext(adimn.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");

                        startActivityForResult(Intent.createChooser(intent, "please select the app"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);

                productImage.setImageBitmap(bitmap);
                SendToStorage();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void SendToStorage() {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference uploder =  storage.getReference().child("user"+key);


        uploder.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        picUrl = uri.toString();
                    }
                });

                ////////////

//         picUrl  = String.valueOf(uploder.getDownloadUrl());
//
//          Log.d("url",""+picUrl);

                //////
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(picUrl, String.valueOf(uploder.getDownloadUrl()));
//
//                Log.d("link...","!!!"+picUrl);



                Toast.makeText(adimn.this, "Purl Send", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void saveAdminProduct(View view) {


        progressBar.setVisibility(View.VISIBLE);


        String sName = productName.getText().toString().trim();
        String sPrice = price.getText().toString().trim();
        String sDescription = productDescription.getText().toString().trim();

        ModelClass modelClass = new ModelClass(sName,sPrice,sDescription,picUrl);


        reference.child(key).setValue(modelClass);



        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();


        progressBar.setVisibility(View.GONE);






    }
}