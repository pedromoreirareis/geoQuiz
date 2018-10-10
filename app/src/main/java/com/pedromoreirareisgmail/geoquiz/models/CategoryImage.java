package com.pedromoreirareisgmail.geoquiz.models;

public class CategoryImage {

    private String category;
    private String image;

    public CategoryImage(String category, String image) {
        this.category = category;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}
