package com.example.dinehero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dinehero.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InboxActivity extends AppCompatActivity {

    private BottomNavigationView BNV;

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        BNV = findViewById(R.id.bottomNavView2);
        //BNV.setSelectedItemId(findViewById(R.id.btnInbox).getId());
        BNV.setItemActiveIndicatorEnabled(false);

        BNV.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == findViewById(R.id.btnHome).getId()) {

                    openMainActivity();
                } else if (item.getItemId() == findViewById(R.id.btnProfile).getId()) {
                    openProfileActivity();
                }

                return false;
            }
        });
    }
    public void openMainActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        this.startActivity(intent);
    }
    public void openInboxActivity(){

        Intent intent = new Intent(this, InboxActivity.class);
        this.startActivity(intent);
    }
    public void openProfileActivity(){

        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
}