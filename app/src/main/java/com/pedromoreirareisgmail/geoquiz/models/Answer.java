package com.pedromoreirareisgmail.geoquiz.models;

public class Answer {

    private String Main;
    private String Flag;
    private String Map;
    private String Capital;
    private String Category;

    public Answer() {
    }

    public Answer(String main, String flag, String map, String capital, String category) {
        Main = main;
        Flag = flag;
        Map = map;
        Capital = capital;
        Category = category;
    }

    public String getMain() {
        return Main;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
