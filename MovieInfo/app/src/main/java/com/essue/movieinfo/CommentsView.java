package com.essue.movieinfo;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentsView extends LinearLayout {
    TextView name;
    TextView comment;
    TextView time;
    TextView recommend;
    RatingBar rating;

    public CommentsView(Context context) {
        super(context);
        init(context);
    }

    public CommentsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_layout, this, true);

        name = (TextView) findViewById(R.id.name);
        comment = (TextView) findViewById(R.id.comment);
        time = (TextView) findViewById(R.id.time);
        recommend = (TextView) findViewById(R.id.recommend);
        rating = (RatingBar) findViewById(R.id.comment_ratingbar);

        ImageView profile = findViewById(R.id.profile);

        profile.setBackground(new ShapeDrawable(new OvalShape()));
        profile.setClipToOutline(true);
    }

    public void setName(String name){
        this.name.setText(name);
    }

    public void setComment(String comment){
        this.comment.setText(comment);
    }

    public void setTime(String time){
        this.time.setText(time);
    }

    public void setRecommend(int recommend){
        this.recommend.setText(String.valueOf(recommend));
    }

    public void setRating(double rating){
        this.rating.setRating((float)rating);
    }
}
