package com.example.fsa.starwars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    //private WebView mWebView;
    private Context mContext;
    private TextView titleText;
    private TextView descriptionText;
    private ImageView posterImage;
    private Button submitButton;
    private boolean seen;
    private boolean wantTo;
    private boolean doNotLike;
    private int position;


    // override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = this;
        titleText = findViewById(R.id.movie_title);
        descriptionText = findViewById(R.id.movie_description);
        posterImage = findViewById(R.id.movie_poster);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent checkboxIntent = new Intent();

                checkboxIntent.putExtra("position", position);
                checkboxIntent.putExtra("Already Seen", seen);
                checkboxIntent.putExtra("Want to See", wantTo);
                checkboxIntent.putExtra("Do Not Like", doNotLike);

                setResult(RESULT_OK, checkboxIntent);
                finish();
            }
        });

        // title
        // instruction url
        // get recipe data from main activity

        String title = this.getIntent().getExtras().getString("title");
        String description = this.getIntent().getExtras().getString("description");
        String url = this.getIntent().getExtras().getString("url");

        // set the title on the action bar
        setTitle(title);

        Picasso.with(mContext).load(this.getIntent().getExtras().getString("poster"))
                .into(posterImage);
        final int position = this.getIntent().getExtras().getInt("position");
        // create the webview and load the url
        //mWebView = findViewById(R.id.detail_web_view);
        //mWebView.loadUrl(url);

    }

    public void seen(View view) {
        seen = ((RadioButton) view).isChecked();
    }

    public void wantTo(View view){
        wantTo = ((RadioButton) view).isChecked();
    }

    public void DoNotLike(View view) {
        doNotLike = ((RadioButton) view).isChecked();
    }
}


