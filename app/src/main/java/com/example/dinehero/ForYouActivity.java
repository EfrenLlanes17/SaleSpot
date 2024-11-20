package com.example.dinehero;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinehero.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ForYouActivity extends AppCompatActivity {

    private BottomNavigationView BNV;
    private BottomNavigationView TNV;
    private RecyclerView FRV;
    private static ArrayList<Product> forYouList = new ArrayList<>();
    private static  Boolean first = true;

    private Button goToSignIN;

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_for);

        FRV = findViewById(R.id.FRV);
        BNV = findViewById(R.id.bottomNavView4);
        TNV = findViewById(R.id.topNavView2);
        //TNV.setSelectedItemId(findViewById(R.id.btnForYou).getId());
        TNV.setItemActiveIndicatorEnabled(false);
        BNV.setItemActiveIndicatorEnabled(false);
        goToSignIN = findViewById(R.id.foryouSignIn);

        goToSignIN.setVisibility(View.VISIBLE);
        if(!(ProfileActivity.getCurrentUserUsername().equals(""))){
            goToSignIN.setVisibility(View.INVISIBLE);
        }
       forYouList.clear();

        if (!(ProfileActivity.getCurrentUserUsername().equals(""))){
            for (int y = 0; y < User.findUser().getLikedHashtagsList().size(); y++) {
                for (int x = 0; x < MainActivity2.getProductsList().size(); x++) {
                    if ((MainActivity2.getProductsList().get(x).getHashtagWithSpaces()).indexOf(User.findUser().getLikedHashtagsList().get(y)) > -1) {
                        if (!(MainActivity2.findProductInForYouBoolean(MainActivity2.getProductsList().get(x).getProductName(), MainActivity2.getProductsList().get(x).getProductImageURL()))) {
                            if (User.findUser().isNotInViewedProducts(MainActivity2.getProductsList().get(x))){
                                forYouList.add(MainActivity2.getProductsList().get(x));
                         }
                        }
                    }
                }
            }
    }

            ProductAdapter adapter = new ProductAdapter(this);
        adapter.setProducts(forYouList);
        FRV.setAdapter(adapter);
        FRV.setLayoutManager(new LinearLayoutManager(this));

        goToSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        TNV.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == findViewById(R.id.btnDiscover).getId()) {

                    openMainActivity();
                } else if (item.getItemId() == findViewById(R.id.btnForYou).getId()) {
                    openForYouActivity();
                } else if (item.getItemId() == findViewById(R.id.btnFollowing).getId()) {
                    openFollowingActivity();
                }else if (item.getItemId() == findViewById(R.id.btnSearch).getId()) {
                    openSearchActivity();
                }

                return false;
            }
        });

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

        Intent intent = new Intent(this, ForYouActivity.class);
        this.startActivity(intent);
    }
    public void openProfileActivity(){

        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
    public void openForYouActivity(){

        Intent intent = new Intent(this, ForYouActivity.class);
        this.startActivity(intent);
    }
    public void openFollowingActivity(){
        Intent intent = new Intent(this, FollowingActivity.class);
        this.startActivity(intent);
    }
    public void openSearchActivity(){

        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }
    public static void setFirst(boolean boo){

        first = boo;
    }
    public static boolean getFirst(){
        return first;
    }
    public static ArrayList<Product> getForYouList(){
        return forYouList;
    }

    public static void setForYouList(ArrayList<Product> array){

        forYouList = array;
    }
}