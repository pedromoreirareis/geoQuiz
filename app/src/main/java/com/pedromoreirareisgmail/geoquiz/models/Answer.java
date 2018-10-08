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
}
