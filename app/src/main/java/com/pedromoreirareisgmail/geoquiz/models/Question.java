package com.pedromoreirareisgmail.geoquiz.models;

public class Question {

    private String Main;
    private String Flag;
    private String Map;
    private String Capital;
    private String Category;
    private String Region;
    private String Description;

    public Question() {
    }

    public Question(String main, String flag, String map, String capital, String category, String region, String description) {
        Main = main;
        Flag = flag;
        Map = map;
        Capital = capital;
        Category = category;
        Region = region;
        Description = description;
    }

    public String getMain() {
        return Main;
    }

    public void setMain(String main) {
        Main = main;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getMap() {
        return Map;
    }

    public void setMap(String map) {
        Map = map;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
