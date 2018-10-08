package com.pedromoreirareisgmail.geoquiz.models;

public class Ranking {

    private int Id;
    private int Score;
    private String Category;

    public Ranking() {
    }

    public Ranking(int id, int score, String category) {
        Id = id;
        Score = score;
        Category = category;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
