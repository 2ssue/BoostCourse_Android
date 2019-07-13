package com.essue.movieinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView thumbsDownNum;
    TextView thumbsUpNum;

    boolean thumbsUpState = false;
    boolean thumbsDownState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.listview2);

        CommentAdapter adapter = new CommentAdapter();
        adapter.addItem(new Comment("이수정", "재밌네요", 1, "1분전", 5));
        adapter.addItem(new Comment("e2ssue", "노잼", 0, "20분전", 2.3));
        adapter.addItem(new Comment("e2ssue", "노잼", 0, "20분전", 1.5));
        adapter.addItem(new Comment("이수정", "재밌네요", 1, "1분전", 3.4));

        listview.setAdapter(adapter);

        thumbsUpNum = (TextView) findViewById(R.id.tThumbsUp);

        thumbsUpNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
        });

        thumbsDownNum = (TextView) findViewById(R.id.tThumbsDown);

        thumbsDownNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
        });
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

    class CommentAdapter extends BaseAdapter {
        ArrayList<Comment> comments = new ArrayList<>();

        public void addItem(Comment comment){
            comments.add(comment);
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
                comments_view = new CommentsView(getApplication());
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
}
