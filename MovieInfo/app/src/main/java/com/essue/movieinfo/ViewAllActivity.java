package com.essue.movieinfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewAllActivity extends AppCompatActivity {

    static final int COMMENT_MODE = 101;

    TextView movieName;
    ListView listView;
    TextView countUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("movie_name");
        double rating = intent.getDoubleExtra("rating", 0);

        movieName = (TextView) findViewById(R.id.t_view_all_movie_name);
        movieName.setText(name);
        movieName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_15, 0);

        TextView movieRatingText = (TextView) findViewById(R.id.t_view_all_rating);
        RatingBar movieRating = (RatingBar) findViewById(R.id.view_all_movie_ratingBar);

        movieRating.setRating((float)rating);
        movieRatingText.setText(String.format("%.1f", rating*2));

        listView = (ListView) findViewById(R.id.listView);
        countUser = (TextView) findViewById(R.id.t_view_all_count);
        Button btnReview = (Button) findViewById(R.id.btnReview2);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserCommentActivity.class);
                intent.putExtra("movie_name", movieName.getText());
                startActivityForResult(intent, COMMENT_MODE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CommentAdapter adapter = new CommentAdapter(getApplication());
        listView.setAdapter(adapter);
        int value = CommentList.getInstance().getCount();
        countUser.setText(Integer.toString(value));
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
