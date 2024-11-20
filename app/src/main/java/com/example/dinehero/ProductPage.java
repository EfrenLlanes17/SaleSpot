package com.example.dinehero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;

public class ProductPage extends AppCompatActivity {

    public TextView PPProductName;
    public TextView PPDiscription;
    public ImageView PPImage;
    public TextView PPPrice;
    private TextView PPPercentOff;
    private Button PPButton;
    private Button shareButton;
    private Button saveButton;
    private Button followingButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);


        Intent intent = getIntent();
        String text1 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT);

        String text3 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT3);
        String text7 = intent.getStringExtra(ProductAdapter.EXTRA_TEXT7);
        Product temp = MainActivity2.findProduct(text1, text3);
        boolean temp2 = ProfileActivity.getCurrentUserUsername().equals("");
        boolean temp3;
        if(User.findUser() != null) {
                temp3 = ProfileActivity.isNotInsideFollowing(text7);
        }
        else{
            temp3 = true;
        }
        String text2 = temp.getProductDiscription();
        String text4 = temp.getProductPrice();
        int text5 = temp.getProductPercentOff();
        String text6 = temp.getProductLink();


        PPProductName = findViewById(R.id.txtPPName);
        PPDiscription = findViewById(R.id.txtPPDiscription);
        PPImage = findViewById(R.id.txtPPImage);
        PPPrice = findViewById(R.id.txtPPPrice);
        PPPercentOff = findViewById(R.id.txtPPPercentOff);
        PPButton = findViewById(R.id.sitePPButton);
        saveButton = findViewById(R.id.savePPButton);
        followingButton = findViewById(R.id.followPPButton);

        PPProductName.setText(text1);
        PPDiscription.setText(text2);
        PPPrice.setText("$" +text4);
        PPPercentOff.setText(text5 + "%");

        if(User.findUser() != null) {
            if (User.inSavedList(temp)) {
                saveButton.setText("    UNSAVE   ");
            } else {
                saveButton.setText("     SAVE    ");
            }

            if (temp3) {
                followingButton.setText("  FOLLOW  ");
            } else {
                followingButton.setText("  FOLLOWING  ");
            }
        }
        else{
            saveButton.setText("     SAVE    ");
            followingButton.setText("  FOLLOW  ");
        }




        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(temp2)) {
                    if (temp3) {
                        ProfileActivity.addToFollowing(text7);
                        followingButton.setText("  FOLLOWING  ");
                    }

                }
                else{
                    Toast.makeText(ProductPage.this, "Sign in to follow sellers", Toast.LENGTH_SHORT).show();
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(temp2)) {

                    if (temp.getHasbeenSaved()) {

                        ProfileActivity.removeFromSaved(temp);
                        saveButton.setText("     SAVE    ");
                        temp.setHasbeenSaved(false);
                        Toast.makeText(ProductPage.this, "Product unsaved, reload tab", Toast.LENGTH_SHORT).show();

                    } else {
                        ProfileActivity.addToSaved(temp);
                        saveButton.setText("    UNSAVE   ");
                       temp.setHasbeenSaved(true);

                    }
                }
                else{
                    Toast.makeText(ProductPage.this, "Sign in to save products", Toast.LENGTH_SHORT).show();
                }
            }
        });

        PPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(text6);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });





        Glide.with(this)
                .asBitmap()
                .load(text3)
                .into(PPImage);


    }






}