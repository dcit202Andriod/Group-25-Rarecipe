package com.theta.rarecipe.classes;

public class CreatorItem {
    private final String name;
    private final String imageUrl;

    public CreatorItem(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
