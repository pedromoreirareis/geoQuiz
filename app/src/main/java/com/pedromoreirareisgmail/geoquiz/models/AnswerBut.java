package com.pedromoreirareisgmail.geoquiz.models;

public class AnswerBut {

    private String A;
    private String B;
    private String C;
    private String D;

    public AnswerBut() {
    }

    public AnswerBut(String a, String b, String c, String d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }
}
