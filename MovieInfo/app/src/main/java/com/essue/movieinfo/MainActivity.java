package com.essue.movieinfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView thumbsDownNum;
    TextView thumbsUpNum;
    TextView movieName;
    ListView listview;
    RatingBar movieRating;

    boolean thumbsUpState = false;
    boolean thumbsDownState = false;

    static final int COMMENT_MODE = 101;
    static final int VIEW_ALL_MODE = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview2);

        thumbsUpNum = (TextView) findViewById(R.id.tThumbsUp);

        thumbsUpNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                thumbsUpClick();
            }
        });

        thumbsDownNum = (TextView) findViewById(R.id.tThumbsDown);

        thumbsDownNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                thumbsDownClick();
            }
        });

        Button review = (Button) findViewById(R.id.btnReview);
        movieName = (TextView) findViewById(R.id.tMovieName);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserCommentActivity.class);
                intent.putExtra("movie_name", movieName.getText());
                startActivityForResult(intent, COMMENT_MODE);
            }
        });

        Button viewAll = (Button) findViewById(R.id.btnViewAll);
        movieRating = (RatingBar) findViewById(R.id.movie_ratingBar);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewAllActivity.class);
                intent.putExtra("movie_name", movieName.getText());
                intent.putExtra("rating", (double)movieRating.getRating());
                startActivityForResult(intent, VIEW_ALL_MODE);
            }
        });
    }

    public void thumbsUpClick(){
        if(thumbsUpState) {
            decreaseCount(thumbsUpNum, true);
        }else{
            if(thumbsDownState){
                decreaseCount(thumbsDownNum, false);
                thumbsDownState = !thumbsDownState;
            }
            increaseCount(thumbsUpNum, true);
        }

        thumbsUpState = !thumbsUpState;
    }

    public void thumbsDownClick(){
        if(thumbsDownState){
            decreaseCount(thumbsDownNum, false);
        }else{
            if(thumbsUpState){
                decreaseCount(thumbsUpNum, true);
                thumbsUpState = !thumbsUpState;
            }
            increaseCount(thumbsDownNum, false);
        }

        thumbsDownState = !thumbsDownState;
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommentAdapter adapter = new CommentAdapter(getApplication());

        listview.setAdapter(adapter);
    }

    public void increaseCount(TextView textview, boolean upflag){
        int num = Integer.parseInt(textview.getText().toString());
        textview.setText(String.valueOf(num + 1));

        if(upflag)
            textview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_selected, 0,0,0);
        else
            textview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_down_selected, 0,0,0);
    }

    public void decreaseCount(TextView textview, boolean upflag){
        int num = Integer.parseInt(textview.getText().toString());
        textview.setText(String.valueOf(num - 1));

        if(upflag)
            textview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up, 0,0,0);
        else
            textview.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_down, 0,0,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == COMMENT_MODE){
            if(data != null){
                String comments = data.getStringExtra("comment");
                double ratingNum = data.getDoubleExtra("rating", 0);

                CommentList.getInstance().addComment(new Comment("이수정", comments, 0, "0분전", ratingNum));

            }
        }
    }
}
