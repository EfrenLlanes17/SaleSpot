package com.example.dinehero;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dinehero.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    private BottomNavigationView BNV;
    private static final String FILE_NAME = "example.txt";
    public static ArrayList<Product> savedList = new ArrayList<>();
    public static ArrayList<String> FollowingList = new ArrayList<>();
    ActivityMainBinding binding;

    private TextView signInHeader;

    private static String currentUserPassword = "";
    private static String currentUserUsername = "";

    private TextView usernameTakenError;
    private TextView passwordShortError;
    private TextView uppercaseError;
    private TextView numberError;
    private TextView or;
    private EditText edtTxtUsername;
    private EditText edtTxtPassword;
    private Button signInProfileBtn;
    private Button createAccountProfileBtn;
    private TextView usernameWrongError;
    private TextView helloUserName;
    private TextView usernameCharacterError;
    private TextView usernameSpaceError;
    private TextView passwordSpaceError;
    private TextView usernameNotFoundError;
    private ImageView signLogo;
    private Context con;

    private TextView HelloUser;
    private TextView line;
    private TextView HelloFollowing;
    private TextView line2;
    private TextView Saved;
    private RecyclerView SavedRV;
    private static boolean  result10 = false;
    private RecyclerView FollowingRV;
    private static String username;
    private TextView welcome;
    private static boolean happened = false;

    private ImageButton settingBtn;


    private static RelativeLayout relativeLayout;
    Drawable draw;

    FirebaseDatabase db;
    private static DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        helloUserName = findViewById(R.id.HelloUserName);
        signLogo = findViewById(R.id.SignLogo);
        settingBtn = findViewById(R.id.settingsButton);
        relativeLayout = findViewById(R.id.profileRelativelayout);
        FollowingRV = findViewById(R.id.FollowingRV);
        SavedRV = findViewById(R.id.SavedRV);
        HelloUser = findViewById(R.id.HelloUser);
        line = findViewById(R.id.line);
        HelloFollowing = findViewById(R.id.HelloFollowing);
        line2 = findViewById(R.id.line2);
        Saved = findViewById(R.id.Saved);
        signInHeader = findViewById(R.id.signInHeader);
        usernameTakenError = findViewById(R.id.usernameTakenError);
        passwordShortError = findViewById(R.id.passwordShortError);
        uppercaseError = findViewById(R.id.uppercaseError);
        numberError = findViewById(R.id.numberError);
        or = findViewById(R.id.or);
        edtTxtUsername = findViewById(R.id.edtTxtUsername);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        signInProfileBtn = findViewById(R.id.signInProfileBtn);
        createAccountProfileBtn = findViewById(R.id.createAccountProfileBtn);
        usernameWrongError = findViewById(R.id.usernameWrongError);
        usernameCharacterError = findViewById(R.id.usernameCharacterError);
        usernameSpaceError = findViewById(R.id.usernameSpaceError);
        passwordSpaceError = findViewById(R.id.passwordSpaceError);
        usernameNotFoundError = findViewById(R.id.usernameNotFoundError);
        welcome = findViewById(R.id.welcome);

        usernameTakenError.setVisibility(View.INVISIBLE);
        passwordShortError.setVisibility(View.INVISIBLE);
        uppercaseError.setVisibility(View.INVISIBLE);
        numberError.setVisibility(View.INVISIBLE);
        usernameWrongError.setVisibility(View.INVISIBLE);
        usernameCharacterError.setVisibility(View.INVISIBLE);
        usernameSpaceError.setVisibility(View.INVISIBLE);
        passwordSpaceError.setVisibility(View.INVISIBLE);
        usernameNotFoundError.setVisibility(View.INVISIBLE);
        HelloUser.setVisibility(View.INVISIBLE);
        line.setVisibility(View.INVISIBLE);
        HelloFollowing.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        Saved.setVisibility(View.INVISIBLE);
        SavedRV.setVisibility(View.INVISIBLE);
        FollowingRV.setVisibility(View.INVISIBLE);
        settingBtn.setVisibility(View.INVISIBLE);
        helloUserName.setVisibility(View.INVISIBLE);
        load();

        if(User.isHasSignedInString().indexOf("true") > -1){


            relativeLayout.setBackground(getResources().getDrawable(R.drawable.background3));
            signLogo.setVisibility(View.INVISIBLE);
            welcome.setVisibility(View.INVISIBLE);
            usernameNotFoundError.setVisibility(View.INVISIBLE);
            signInHeader.setVisibility(View.INVISIBLE);
            usernameTakenError.setVisibility(View.INVISIBLE);
            passwordShortError.setVisibility(View.INVISIBLE);
            uppercaseError.setVisibility(View.INVISIBLE);
            numberError.setVisibility(View.INVISIBLE);
            or.setVisibility(View.INVISIBLE);
            edtTxtUsername.setVisibility(View.INVISIBLE);
            edtTxtPassword.setVisibility(View.INVISIBLE);
            signInProfileBtn.setVisibility(View.INVISIBLE);
            createAccountProfileBtn.setVisibility(View.INVISIBLE);
            usernameWrongError.setVisibility(View.INVISIBLE);
            usernameCharacterError.setVisibility(View.INVISIBLE);
            usernameSpaceError.setVisibility(View.INVISIBLE);
            passwordSpaceError.setVisibility(View.INVISIBLE);
            HelloUser.setVisibility(View.VISIBLE);
            HelloUser.setText("Hello" );
            helloUserName.setVisibility(View.VISIBLE);
            helloUserName.setText(ProfileActivity.getCurrentUserUsername());
            line.setVisibility(View.VISIBLE);
            HelloFollowing.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            Saved.setVisibility(View.VISIBLE);
            SavedRV.setVisibility(View.VISIBLE);
            FollowingRV.setVisibility(View.VISIBLE);
            settingBtn.setVisibility(View.VISIBLE);
        }

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openSettingsActivity();
            }
        });

        signInProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(User.findUser2(edtTxtUsername.getText().toString(),edtTxtPassword.getText().toString()) != null) {
                 User.setHasSignedIn(true);
                 setCurrentUserPassword(edtTxtPassword.getText().toString());
                 setCurrentUserUsername(edtTxtUsername.getText().toString());
                 Toast.makeText(ProfileActivity.this, "Signed in successfully, reload tab", Toast.LENGTH_LONG).show();
             }
             else{

                 if(checkCloudUsername(edtTxtUsername.getText().toString())) {
                     Toast.makeText(ProfileActivity.this, "Signed bob in successfully, reload tab", Toast.LENGTH_LONG).show();

                 }
                 else {
                     usernameNotFoundError.setVisibility(View.VISIBLE);
                     usernameTakenError.setVisibility(View.INVISIBLE);
                 }
             }
            }
        });


        createAccountProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameNotFoundError.setVisibility(View.INVISIBLE);
                if(edtTxtPassword.getText().toString().length() >= 10) {
                    if(edtTxtPassword.getText().toString().indexOf("A") > -1 || edtTxtPassword.getText().toString().indexOf("B") > -1 || edtTxtPassword.getText().toString().indexOf("C") > -1|| edtTxtPassword.getText().toString().indexOf("D") > -1|| edtTxtPassword.getText().toString().indexOf("E") > -1|| edtTxtPassword.getText().toString().indexOf("F") > -1|| edtTxtPassword.getText().toString().indexOf("G") > -1|| edtTxtPassword.getText().toString().indexOf("H") > -1|| edtTxtPassword.getText().toString().indexOf("I") > -1|| edtTxtPassword.getText().toString().indexOf("J") > -1|| edtTxtPassword.getText().toString().indexOf("K") > -1|| edtTxtPassword.getText().toString().indexOf("L") > -1|| edtTxtPassword.getText().toString().indexOf("M") > -1|| edtTxtPassword.getText().toString().indexOf("N") > -1|| edtTxtPassword.getText().toString().indexOf("O") > -1|| edtTxtPassword.getText().toString().indexOf("P") > -1|| edtTxtPassword.getText().toString().indexOf("Q") > -1|| edtTxtPassword.getText().toString().indexOf("R") > -1|| edtTxtPassword.getText().toString().indexOf("S") > -1|| edtTxtPassword.getText().toString().indexOf("T") > -1|| edtTxtPassword.getText().toString().indexOf("U") > -1|| edtTxtPassword.getText().toString().indexOf("V") > -1|| edtTxtPassword.getText().toString().indexOf("W") > -1|| edtTxtPassword.getText().toString().indexOf("X") > -1|| edtTxtPassword.getText().toString().indexOf("Y") > -1|| edtTxtPassword.getText().toString().indexOf("Z") > -1) {
                        if(edtTxtPassword.getText().toString().indexOf("1") >-1|| edtTxtPassword.getText().toString().indexOf("2") >-1|| edtTxtPassword.getText().toString().indexOf("3") >-1|| edtTxtPassword.getText().toString().indexOf("4") >-1|| edtTxtPassword.getText().toString().indexOf("5") >-1|| edtTxtPassword.getText().toString().indexOf("6") >-1|| edtTxtPassword.getText().toString().indexOf("7") >-1|| edtTxtPassword.getText().toString().indexOf("8") >-1|| edtTxtPassword.getText().toString().indexOf("9") >-1|| edtTxtPassword.getText().toString().indexOf("0") >-1){
                            if(edtTxtPassword.getText().toString().indexOf(" ") == -1) {
                                if(User.usernameNotTaken(edtTxtUsername.getText().toString())) {
                                    if(edtTxtUsername.getText().toString().indexOf(" ") == -1) {
                                        if(edtTxtUsername.getText().toString().length() < 12) {
                                            ProfileActivity.setUsername(edtTxtUsername.getText().toString());

                                            User newUser = new User(edtTxtUsername.getText().toString(), edtTxtPassword.getText().toString());
                                            User.addToUserlist(new User(edtTxtUsername.getText().toString(), edtTxtPassword.getText().toString()));
                                            db = FirebaseDatabase.getInstance();

                                            User.setHasSignedIn(true);
                                            save(edtTxtUsername.getText().toString(),edtTxtPassword.getText().toString());
                                            setCurrentUserPassword(edtTxtPassword.getText().toString());
                                            setCurrentUserUsername(edtTxtUsername.getText().toString());

                                            reference = db.getReference("Users");
                                            reference.child(edtTxtUsername.getText().toString()).setValue(newUser);

                                            Toast.makeText(ProfileActivity.this, "Account created successfully, reload tab " , Toast.LENGTH_LONG).show();

                                        }
                                        else{
                                            passwordShortError.setVisibility(View.INVISIBLE);
                                            uppercaseError.setVisibility(View.INVISIBLE);
                                            numberError.setVisibility(View.INVISIBLE);
                                            passwordSpaceError.setVisibility(View.INVISIBLE);
                                            usernameTakenError.setVisibility(View.INVISIBLE);
                                            usernameSpaceError.setVisibility(View.INVISIBLE);
                                            usernameCharacterError.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    else{
                                        passwordShortError.setVisibility(View.INVISIBLE);
                                        uppercaseError.setVisibility(View.INVISIBLE);
                                        usernameCharacterError.setVisibility(View.INVISIBLE);
                                        numberError.setVisibility(View.INVISIBLE);
                                        passwordSpaceError.setVisibility(View.INVISIBLE);
                                        usernameTakenError.setVisibility(View.INVISIBLE);
                                        usernameSpaceError.setVisibility(View.VISIBLE);

                                    }
                                }
                                else{
                                    usernameCharacterError.setVisibility(View.INVISIBLE);
                                    passwordShortError.setVisibility(View.INVISIBLE);
                                    uppercaseError.setVisibility(View.INVISIBLE);
                                    numberError.setVisibility(View.INVISIBLE);
                                    passwordSpaceError.setVisibility(View.INVISIBLE);
                                    usernameSpaceError.setVisibility(View.INVISIBLE);
                                    usernameTakenError.setVisibility(View.VISIBLE);
                                }
                            }
                            else{
                                usernameCharacterError.setVisibility(View.INVISIBLE);
                                usernameTakenError.setVisibility(View.INVISIBLE);
                                passwordShortError.setVisibility(View.INVISIBLE);
                                uppercaseError.setVisibility(View.INVISIBLE);
                                numberError.setVisibility(View.INVISIBLE);
                                usernameSpaceError.setVisibility(View.INVISIBLE);
                                passwordSpaceError.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            usernameCharacterError.setVisibility(View.INVISIBLE);
                            usernameTakenError.setVisibility(View.INVISIBLE);
                            passwordSpaceError.setVisibility(View.INVISIBLE);
                            passwordShortError.setVisibility(View.INVISIBLE);
                            uppercaseError.setVisibility(View.INVISIBLE);
                            usernameSpaceError.setVisibility(View.INVISIBLE);
                            numberError.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        usernameCharacterError.setVisibility(View.INVISIBLE);
                        usernameTakenError.setVisibility(View.INVISIBLE);
                        passwordSpaceError.setVisibility(View.INVISIBLE);
                        passwordShortError.setVisibility(View.INVISIBLE);
                        numberError.setVisibility(View.INVISIBLE);
                        uppercaseError.setVisibility(View.VISIBLE);
                        usernameSpaceError.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    usernameCharacterError.setVisibility(View.INVISIBLE);
                    usernameTakenError.setVisibility(View.INVISIBLE);
                    passwordSpaceError.setVisibility(View.INVISIBLE);
                    numberError.setVisibility(View.INVISIBLE);
                    uppercaseError.setVisibility(View.INVISIBLE);
                    passwordShortError.setVisibility(View.VISIBLE);
                    usernameSpaceError.setVisibility(View.INVISIBLE);

                }
            }
        });

        BNV = findViewById(R.id.bottomNavView3);
        //BNV.setSelectedItemId(findViewById(R.id.btnProfile).getId());
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

        if(User.findUser2(ProfileActivity.getCurrentUserUsername(),ProfileActivity.getCurrentUserPassword()) != null) {

            if(User.findUser().getFollowingSellers() != null){
                FollowingList = User.findUser().getFollowingSellers();

            }
            else{
                ArrayList<String> temp = new ArrayList();
                FollowingList =  temp;
            }

            if(User.findUser().getSavedProducts() != null){
                savedList = User.findUser().getSavedProducts();

            }
            else {
                ArrayList<Product> temp2 = new ArrayList();
                savedList =  temp2;
            }
        }
        SavedAdapter adapter = new SavedAdapter(this);
        adapter.setProducts(savedList);
        SavedRV.setAdapter(adapter);
        SavedRV.setLayoutManager(new GridLayoutManager(this,2));



        FollowingAdapter Fadapter = new FollowingAdapter(this);
        Fadapter.setProducts(FollowingList);
        FollowingRV.setAdapter(Fadapter);
        FollowingRV.setLayoutManager(new GridLayoutManager(this,1,LinearLayoutManager.HORIZONTAL,false));

    }
    public static void addToSaved(Product product){

        savedList.add(product);
        if(!(ProfileActivity.getCurrentUserUsername().equals(""))) {
            User.findUser().setSavedProducts(savedList);
            ProfileActivity.updateUserData(User.findUser().username,User.findUser().password,savedList);

        }
    }
    public void save(String username, String password){
        System.out.println(User.isHasSignedInStringPlus(username,password) + " save");
       SharedPreferences.Editor editor = getSharedPreferences(User.hasSignedInString,MODE_PRIVATE).edit();
        editor.putString(User.hasSignedInString,User.isHasSignedInStringPlus(username,password));
        editor.apply();


//Bad VVVVV
       // editor = getSharedPreferences(User.usernameForSaved,MODE_PRIVATE).edit();
       // SharedPreferences.Editor editor3 = getSharedPreferences(User.passwordForSaved,MODE_PRIVATE).edit();
        //editor.putString(User.usernameForSaved,User.findUser().getUsername());
       // editor3.putString(User.passwordForSaved,User.findUser().getPassword());
       // editor.apply();

        //editor2.apply();
        //editor3.apply();

    }
    public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences(User.hasSignedInString, MODE_PRIVATE);
        String result = sharedPreferences.getString(User.hasSignedInString, "").toString();

        if(result.indexOf("true") > -1) {
            User.setHasSignedIn(true);

            int space1 = 0;
            int space2 = 0;

            for (int i = 0; i < result.length() - 1; i++) {
                if (result.substring(i, i + 1).equals(" ")) {
                    space1 = i;
                    i = 10000000;
                }
            }
            for (int x = space1 + 1; x < result.length() - 1; x++) {
                if (result.substring(x, x + 1).equals(" ")) {
                    space2 = x;
                    x = 10000000;
                }
            }
            setCurrentUserUsername(result.substring(space1+1, space2));
            setCurrentUserPassword(result.substring(space2+1));
            //no longer loading
            checkCloudUsername(result.substring(space1+1, space2));
            System.out.println(ProfileActivity.getCurrentUserUsername() + " " + ProfileActivity.getCurrentUserPassword() + " load");
        }

    }
    public static void updateUserData(String username,String password,ArrayList<Product> prodlist){
        HashMap user = new HashMap<>();
        user.put("username",username);
        user.put("password",password);
        user.put("savedProductsString",User.arrayToString(prodlist));

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(username).updateChildren(user);
    }

    public static boolean checkCloudUsername(String username){
        reference = FirebaseDatabase.getInstance().getReference("Users");
        result10 = false;

        //last toast running first
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                // varible assignments for getting children of results have to be inside this

                DataSnapshot dataSnapshot = task.getResult();
                ProfileActivity.setCurrentUserUsername(String.valueOf(dataSnapshot.child("username").getValue()));
                ProfileActivity.setCurrentUserUsername(String.valueOf(dataSnapshot.child("password").getValue()));
                if(User.findUser() == null){
                    User newUser = new User(String.valueOf(dataSnapshot.child("username").getValue()),String.valueOf(dataSnapshot.child("password").getValue()));
                    newUser.setSavedProducts(User.stringToProdArray(String.valueOf(dataSnapshot.child("savedProductsString").getValue())));
                    User.addToUserlist(newUser);
                    User.setHasSignedIn(true);
                    //change new user arrays from strings
                }
                result10 = true;
                Toast.makeText(relativeLayout.getContext(), "Balls "  + result10, Toast.LENGTH_SHORT).show();

            }
        });
        Toast.makeText(relativeLayout.getContext(), "Balls 24 "  + result10, Toast.LENGTH_SHORT).show();

        return result10;
    }

    public static void removeFromSaved(Product product){

        savedList.remove(product);
        if(!(ProfileActivity.getCurrentUserUsername().equals(""))) {
            User.findUser().setSavedProducts(savedList);
            ProfileActivity.updateUserData(User.findUser().username,User.findUser().password,savedList);
        }
    }
    public static void addToFollowing(String string){
        if(User.findUser() != null) {
            User.findUser().getFollowingSellers().add(string);
        }
    }
    public static void removeFromFollowing(String string){
        FollowingList.remove(string);
        if(!(ProfileActivity.getCurrentUserUsername().equals(""))) {
            User.findUser().setFollowingSellers(FollowingList);
        }
    }
    public void openMainActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        this.startActivity(intent);
    }
    public void openInboxActivity(){

        Intent intent = new Intent(this, InboxActivity.class);
        this.startActivity(intent);
    }
    public static void setCurrentUserPassword(String password){
        currentUserPassword = password;
    }
    public static void setCurrentUserUsername(String username){
        currentUserUsername = username;
    }
    public static String getCurrentUserPassword(){
        return currentUserPassword;
    }
    public static String getCurrentUserUsername(){
        return currentUserUsername;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String user) {
        username = user;
    }

    public void openProfileActivity(){

        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
    public void openSettingsActivity(){

        Intent intent = new Intent(this, settings.class);
        this.startActivity(intent);
    }
    public static boolean isNotInsideFollowing(String string){

    for (int x = 0; x < User.findUser().getFollowingSellers().size(); x++) {
        if (User.findUser().getFollowingSellers().get(x).equals(string)) {
            return false;
        }
    }


        return true;
    }
    public static void showSnackbar(String text){


        Snackbar.make(relativeLayout,"Unfollowed " + text, Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addToFollowing(text);
                        Toast.makeText(view.getContext(), text + " followed. Reload Tab", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


     
    }

    public static boolean isNotInsideSearch(String string){

        for(int x = 0; x < SearchActivity.getSearchList().size();x++){
            if(SearchActivity.getSearchList().get(x).getProductImageURL().equals(string)){
                return false;
            }
        }
        return true;
    }
    public static void setSavedList(ArrayList<Product> array){
        savedList = array;
    }
    public static void setFollowingList(ArrayList<String> array){
        FollowingList = array;
    }

}