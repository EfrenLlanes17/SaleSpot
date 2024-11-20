package com.example.dinehero;

import java.util.ArrayList;

public class Product {
    private String productName;
    private String productDiscription;
    private String productPrice;
    private int productPercentOff;
    private String productSeller;
    private String productImageURL;
    private String productLink;
    private ArrayList<String> hashtags = new ArrayList();
    private String hashtagWithSpace;
    private boolean hasbeenSaved;
    public Product(String productName, String productDiscription, String productPrice, int productPercentOff, String productSeller, String productImageURL, String link, String hashtagsWithSpaces) {
        this.productName = productName;
        this.productDiscription = productDiscription;
        this.productPrice = productPrice;
        this.productPercentOff = productPercentOff;
        this.productSeller = productSeller;
        this.productImageURL = productImageURL;
        this.productLink = link;
        hashtagWithSpace = hashtagsWithSpaces;
        hasbeenSaved = false;
        int temp = 0;
        for(int x = 0; x< hashtagsWithSpaces.length()-1;x++){
            if(hashtagsWithSpaces.substring(x,x+1).equals(" ")){
                hashtags.add(hashtagsWithSpaces.substring(temp,x));
                hashtagsWithSpaces = hashtagsWithSpaces.substring(x+1);
                x = 0;
            }
        }
    }
    public ArrayList<String> getHashtagList(){
        return hashtags;
    }
    public String getHashtagWithSpaces(){
        return hashtagWithSpace;
    }

    public boolean getHasbeenSaved() {
        return hasbeenSaved;
    }

    public void setHasbeenSaved(boolean hasbeenSaved) {
        this.hasbeenSaved = hasbeenSaved;
    }

    public String getProductLink() {
        return productLink;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDiscription() {
        return productDiscription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public int getProductPercentOff() {
        return productPercentOff;
    }

    public String getProductSeller() {
        return productSeller;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDiscription(String productDiscription) {
        this.productDiscription = productDiscription;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductPercentOff(int productPercentOff) {
        this.productPercentOff = productPercentOff;
    }

    public void setProductSeller(String productSeller) {
        this.productSeller = productSeller;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productDiscription='" + productDiscription + '\'' +
                ",! productPrice=" + productPrice +
                ",* productPercentOff=" + productPercentOff +
                ",& productSeller='" + productSeller + '\'' +
                ",^ productImageURL='" + productImageURL + '\'' +
                ",$ link='" + productLink + '\'' +
                ",# hashtagsWithSpacese='" + hashtagWithSpace + '\'' +
                ",@" +
                '}';
    }
}
