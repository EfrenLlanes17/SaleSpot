package com.example.dinehero;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class User {
    public String username;
    public static String usernameForSaved;
    public static String passwordForSaved;
    private static ArrayList<User>  userArrayList = new ArrayList();
    public String password;

    private String userIcon;
    static SharedPreferences sp;
    private  ArrayList<Product> savedProducts = new ArrayList();
    private  ArrayList<String>  followingSellers = new ArrayList();
    private  ArrayList<String> likedHashtags = new ArrayList();
    private  ArrayList<Integer> likedHashtagStats = new ArrayList();

    private  ArrayList<Product> viewedProducts = new ArrayList();

    public static boolean hasSignedIn = false;
    public static String hasSignedInString;

    public String savedProductsString;


    public User(String username, String password) {
        //database info based on strings in here
        //only saves strings and ints
        savedProductsString = "";
        this.username = username;
        this.password = password;
        hasSignedIn = true;
    }

    public static boolean isHasSignedIn() {


        return hasSignedIn;

    }
    public static String isHasSignedInString() {

        if (isHasSignedIn()){
            return "true" + " " + ProfileActivity.getCurrentUserUsername() + " " + ProfileActivity.getCurrentUserPassword();
        }
        return "false";
    }
    public static String isHasSignedInStringPlus(String username, String password) {

        if (isHasSignedIn()){
            return "true" + " " + username + " " + password;
        }
        return "false";
    }
    public static void addToUserlist(User user){
        userArrayList.add(user);
    }

    public void addToViewedProducts(Product product){
        viewedProducts.add(product);
    }

    public boolean isNotInViewedProducts(Product product){
        if(viewedProducts.size() != 0) {
            for (int x = 0; x < viewedProducts.size(); x++) {
                if (viewedProducts.get(x).getProductName().equals(product.getProductName()) && viewedProducts.get(x).getProductImageURL().equals(product.getProductImageURL())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getUsername() {
        return username;
    }
    private  static Context context;


    public String getPassword() {
        return password;
    }

    public String getUserIcon() {
        return userIcon;
    }
    private Context connn;

    public ArrayList<Product> getSavedProducts() {
        return savedProducts;
    }

    public ArrayList<String> getFollowingSellers() {
        return followingSellers;
    }

    public ArrayList<String> getLikedHashtags() {
        return likedHashtags;
    }

    public ArrayList<Integer> getLikedHashtagStats() {
        return likedHashtagStats;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public static void addToHashtagList(String string){




        ArrayList<String> userHash = User.findUser().likedHashtags;

        ArrayList<Integer> userStats =User.findUser().likedHashtagStats;

        userHash.add("owenf[ojandj");
        userStats.add(1);
        boolean temp9 = false;
        for(int x = 0; x < userHash.size();x++){

            if(userHash.get(x).equals(string)){
                userStats.set(x,userStats.get(x) + 1);
                temp9 = true;
            }
        }
        if (!temp9){
            userHash.add(string);
            userStats.add(1);
        }

        int temp = 0;
        String temp2 = "";
        for (int i = 0; i <userStats.size(); i++) {
            for (int j = i+1; j <userStats.size(); j++) {
                if(userStats.get(i) >userStats.get(j)) {      //swap elements if not in order
                    temp = userStats.get(i);
                    temp2 = userHash.get(i);
                    userStats.set(i,userStats.get(j));
                    userHash.set(i,userHash.get(j));
                    userStats.set(j,temp);
                    userHash.set(j,temp2);
                }
            }

        }

        int n = userStats.size();
        int u = userHash.size();

        // Swapping the first half elements with last half
        // elements
        for (int i = 0; i < n / 2; i++) {

            // Storing the first half elements temporarily
            int temp3 = userStats.get(i);
            String temp4 = userHash.get(i);

            // Assigning the first half to the last half
            userStats.set(i,userStats.get(n - i - 1));
            userHash.set(i,userHash.get(u - i - 1));


            // Assigning the last half to the first half
            userStats.set(n - i - 1,temp3);
            userHash.set(u - i - 1,temp4);
        }


    }

    public  void setSavedProducts(ArrayList<Product> sp) {
      savedProducts = sp;
    }

    public   void setFollowingSellers(ArrayList<String> fs) {
        followingSellers = fs;
    }

    public void setLikedHashtags(ArrayList<String> likedHashtags) {
        this.likedHashtags = likedHashtags;
    }
    public  ArrayList<String> getLikedHashtagsList(){
        return User.findUser().likedHashtags;
    }
    public  ArrayList<Integer> getLikedHashtagsStatsList(){
        return likedHashtagStats;
    }

    public void setLikedHashtagStats(ArrayList<Integer> likedHashtagStats) {
        this.likedHashtagStats = likedHashtagStats;
    }

    public static void setHasSignedIn(boolean hasSignedIn) {

        User.hasSignedIn = hasSignedIn;

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", savedProducts=" + savedProducts +
                ", followingSellers=" + followingSellers +
                ", likedHashtags=" + likedHashtags +
                ", likedHashtagStats=" + likedHashtagStats +
                '}';
    }
    public static boolean usernameNotTaken(String username){
        for (int x = 0; x <userArrayList.size();x++ ){
            if(username.equals(userArrayList.get(x).getUsername())){
                return false;
            }
        }
        return true;
    }
    public static User findUser(){
    //return userArrayList.get(0);

        for(int x = 0; x < userArrayList.size();x++) {
            System.out.println("XPass: " + userArrayList.get(x).getPassword() + " CurrPass: "+ ProfileActivity.getCurrentUserPassword() +" finduser");

            if(userArrayList.get(x).getUsername().equals(ProfileActivity.getCurrentUserUsername()) && userArrayList.get(x).getPassword().equals(ProfileActivity.getCurrentUserPassword())){
                return userArrayList.get(x);
            }
        }
       return null;
    }
    public static boolean inSavedList(Product pro){
        for(int x = 0; x < User.findUser().getSavedProducts().size();x++) {
            if(User.findUser().getSavedProducts().get(x) == pro){
                return true;
            }
        }
        return false;
    }
    public static String arrayToString(ArrayList<Product> prodList) {
        String result = "";
        for (Product val : prodList) {
            result += val.toString();
        }

        return result;
    }

    public static ArrayList<Product> stringToProdArray(String str) {
        ArrayList<Product> prodList = new ArrayList<Product>();
        boolean done = false;

        for (int i = 0; done == false; i++) {
            if (str.indexOf("Product{productName='") > -1) {
                int start1 = str.indexOf("Product{productName='") + 21;
                int end1 = str.indexOf(",") - 1;
                int start2 = str.indexOf("productDiscription='") + 20;
                int end2 = str.indexOf(",!") - 1;
                int start3 = str.indexOf("productPrice=") + 13;
                int end3 = str.indexOf(",*");
                int start4 = str.indexOf("productPercentOff=") + 18;
                int end4 = str.indexOf(",&");
                int start5 = str.indexOf("productSeller='") + 15;
                int end5 = str.indexOf(",^") - 1;
                int start6 = str.indexOf("productImageURL='") + 17;
                int end6 = str.indexOf(",$") - 1;
                int start7 = str.indexOf("link='") + 6;
                int end7 = str.indexOf(",#") - 1;
                int start8 = str.indexOf("hashtagsWithSpacese='") + 21;
                int end8 = str.indexOf(",@") - 1;
                prodList.add(new Product(str.substring(start1, end1), str.substring(start2, end2), str.substring(start3, end3),
                        Integer.parseInt(str.substring(start4, end4)), str.substring(start5, end5), str.substring(start6, end6),
                        str.substring(start7, end7), str.substring(start8, end8)));
                str = str.substring(end8 + 2);
            } else {
                return prodList;
            }

        }

        return prodList;
    }
    public static User findUser2(String username, String password){
       for(int x = 0; x < userArrayList.size();x++) {
            if(userArrayList.get(x).getUsername().equals(username) && userArrayList.get(x).getPassword().equals(password)){
               return userArrayList.get(x);
            }
        }
       return null;
    }
}