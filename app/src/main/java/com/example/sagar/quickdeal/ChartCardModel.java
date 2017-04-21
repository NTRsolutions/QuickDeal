package com.example.sagar.quickdeal;

/**
 * Created by sagar on 20/4/17.
 */

public class ChartCardModel  {

    String sname;
    String chat;
    int number;
    String days;
    int image;

    public ChartCardModel(String sname, String chat, int number, String days, int image) {
        this.sname = sname;
        this.chat = chat;
        this.number = number;
        this.days = days;
        this.image = image;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
