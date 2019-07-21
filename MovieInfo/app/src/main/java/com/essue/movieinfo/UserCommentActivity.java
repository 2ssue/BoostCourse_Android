package com.essue.movieinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class UserCommentActivity extends AppCompatActivity {

    TextView ratingNotice;
    EditText userInputText;
    RatingBar userInputRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_comment);

        Intent intent = getIntent();
        String name = intent.getStringExtra("movie_name");

        TextView movieName = (TextView) findViewById(R.id.tmovieName);
        movieName.setText(name);
        movieName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_15, 0);

        ratingNotice = (TextView) findViewById(R.id.rating_notice);
        userInputText = (EditText) findViewById(R.id.user_textEdit);

        userInputRating = (RatingBar) findViewById(R.id.user_ratingBar);
        userInputRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingNotice.setText("");
            }
        });

    }

    public void onClick(View view){
        finish();
    }

    public void onSummitClick(View view){
        returnToMain();
    }

    public void returnToMain(){
        String comment = userInputText.getText().toString();
        double ratingNum = userInputRating.getRating();

        Intent intent = new Intent();
        intent.putExtra("comment", comment);
        intent.putExtra("rating", ratingNum);

        setResult(RESULT_OK, intent);

        finish();
    }
}
