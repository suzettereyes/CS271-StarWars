package com.example.fsa.starwars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;
    public TextView resultTextView;
    private ArrayList<Movie> movieList;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        // data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json",
                this);

        // create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        resultTextView = findViewById(R.id.has_seen);

        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);


        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send

        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Movie selectedMovie = movieList.get(position);

                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                //explicit
                // from, to
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                // put title and instruction URL
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("poster", selectedMovie.poster);
                detailIntent.putExtra("main_characters", selectedMovie.main_characters
                        .toString());
                detailIntent.putExtra("url", selectedMovie.url);
                detailIntent.putExtra("position", position);

                //startActivity(detailIntent);
                launchActivity(detailIntent);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if (resultCode ==RESULT_OK) {
                final int position = data.getIntExtra("position", -1);
                boolean alreadySeen = data.getBooleanExtra("radioButton1", false);
                boolean wantToSee = data.getBooleanExtra("radioButton2", false);
                boolean doNotLike = data.getBooleanExtra("radioButton3", false);

                if (alreadySeen){
                    movieList.get(position).hasSeen="Has seen";
                }
                else if (wantToSee){
                    movieList.get(position).hasSeen="Wants to See";
                }
                else if (doNotLike){
                    movieList.get(position).hasSeen="Does Not Like";
                    }
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void launchActivity(Intent intent) {
        startActivityForResult(intent, 1);
    }
}
