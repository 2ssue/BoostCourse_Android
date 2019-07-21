package com.essue.movieinfo;

import android.app.Application;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

class CommentAdapter extends BaseAdapter {
    ArrayList<Comment> comments = CommentList.getInstance().getComments();
    Application curApplication;

    public CommentAdapter(Application curApp){
        curApplication = curApp;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommentsView comments_view = null;

        if (view == null) {
            comments_view = new CommentsView(curApplication);
        } else {
            comments_view = (CommentsView) view;
        }

        Comment comment = comments.get(i);
        comments_view.setName(comment.getName());
        comments_view.setComment(comment.getComment());
        comments_view.setRecommend(comment.getRecommend());
        comments_view.setTime(comment.getTime());
        comments_view.setRating(comment.getRating());

        return comments_view;
    }
}