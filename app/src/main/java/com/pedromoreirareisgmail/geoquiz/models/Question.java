package com.pedromoreirareisgmail.geoquiz.models;

public class Question {

    private String Main;
    private String Flag;
    private String Map;
    private String Capital;
    private String Region;

    public Question(String main, String flag, String map, String capital, String category, String region, String description) {
        Main = main;
        Flag = flag;
        Map = map;
        Capital = capital;
        String Category = category;
        Region = region;
        String Description = description;
    }

    public String getMain() {
        return Main;
    }

    public String getFlag() {
        return Flag;
    }

    public String getMap() {
        return Map;
    }

    public String getCapital() {
        return Capital;
    }

    public String getRegion() {
        return Region;
    }

}
