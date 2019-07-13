package com.essue.movieinfo;

public class Comment {
    private String name;
    private String comment;
    private int recommend;
    private String time;
    private double rating;

    public Comment(String name, String comment, int recommend, String time, double rating) {
        this.name = name;
        this.comment = comment;
        this.recommend = recommend;
        this.time = time;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", recommend=" + recommend +
                ", time=" + time +
                '}';
    }
}
