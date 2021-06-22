package com.example.robotechvalley.models;

import java.io.Serializable;

public class ModelClass implements Serializable {

    public String productName,price,description,picUrl;

    public ModelClass() {
    }

    public ModelClass(String productName, String price, String description,String picUrl) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.picUrl = picUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
