package com.theta.rarecipe;

public class FoodItem {
    private final String name;
    private final int profilePhoto;

    public FoodItem(String name, int profilePhoto) {
        this.name = name;
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }
}
