package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private ListView lst;
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_phrases);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_where_are_you_going));
        words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_where_are_you_going));
        words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_where_are_you_going));
        words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_where_are_you_going));
        words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_where_are_you_going));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_where_are_you_going));
        words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_where_are_you_going));
        words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_where_are_you_going));
        words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_where_are_you_going));

        WordAdapter adap = new WordAdapter(this,words,R.color.category_phrases);
        lst = (ListView)findViewById(R.id.phrasesView);
        lst.setAdapter(adap);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word req = (Word)lst.getItemAtPosition(i);
                mp = MediaPlayer.create(getApplicationContext(),req.getSound());
                mp.start();
            }
        });


    }
}
