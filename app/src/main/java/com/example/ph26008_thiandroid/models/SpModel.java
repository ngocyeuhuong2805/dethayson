package com.example.ph26008_thiandroid.models;

public class SpModel {
    private String name;
    private String description;
    private String image;
    private String color;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SpModel() {
    }

    public SpModel(String name, String description, String image, String color ) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.color = color;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }




}
