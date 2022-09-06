package com.example.myutil.simhash;

import java.io.Serializable;

/**
 * @author shugang
 * @create 2019-02-18 14:17
 **/
public class SimHashWord implements Serializable {

    private static final long serialVersionUID = 8583346890825521993L;

    private String dimKey; // 维度前缀
    private String text; // 维度内容
    private int weight; // 权重

    public SimHashWord(String dimKey, String text, int weight) {
        this.dimKey = dimKey;
        this.text = text;
        this.weight = weight;
    }

    public SimHashWord() {
    }

    public String getDimKey() {
        return dimKey;
    }

    public void setDimKey(String dimKey) {
        this.dimKey = dimKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
