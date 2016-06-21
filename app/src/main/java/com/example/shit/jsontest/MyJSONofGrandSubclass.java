package com.example.shit.jsontest;

/**
 * Created by He on 2016/5/30.
 */
public class MyJSONofGrandSubclass {
    private String title;
    private String id;
    private String text;
    private String ct;
    private int type;

    @Override
    public String toString() {
        return "MyJSONofGrandSubclass{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", ct='" + ct + '\'' +
                ", type=" + type +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MyJSONofGrandSubclass() {
    }

    public MyJSONofGrandSubclass(String title, String id, String text, String ct, int type) {
        this.title = title;
        this.id = id;
        this.text = text;
        this.ct = ct;
        this.type = type;
    }
}
