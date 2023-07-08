package com.theta.rarecipe;


public class FoodItem {
    private  String name;
    private  String imageUrl;
    private  String creatorName;

    public FoodItem(String name, String imageUrl, String creatorName) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.creatorName = creatorName;
    }



    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreatorName() {
        return creatorName;
    }
}
