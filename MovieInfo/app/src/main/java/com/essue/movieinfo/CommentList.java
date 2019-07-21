package com.essue.movieinfo;

import java.util.ArrayList;

public class CommentList {
    private static CommentList comments;
    private ArrayList<Comment> commentList = new ArrayList<>();

    private CommentList(){}

    public static CommentList getInstance(){
        if(comments == null){
            comments = new CommentList();
        }

        return comments;
    }

    public ArrayList<Comment> getComments(){
        return commentList;
    }

    public int getCount(){
        return commentList.size();
    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }
}
