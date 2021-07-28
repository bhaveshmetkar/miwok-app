package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("My name is__", "Oyaaset__", R.raw.phrase_my_name_is));
        words.add(new Word("What is your name?", "Tinna oyaase'na", R.raw.phrase_what_is_your_name));
        words.add(new Word("How are you feeling?", "Michaksas", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good.", "Kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Where are you going?", "Minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("Let's go", "Yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here", "anni'nem", R.raw.phrase_come_here));
        words.add(new Word("Yes, I'm coming", "Haa'aanam", R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming", "aanam", R.raw.phrase_im_coming));
        words.add(new Word("Are you coming", "oonu'saa", R.raw.phrase_are_you_coming));


        WordAdaptor adaptor = new WordAdaptor(this, words, R.color.phrase_background);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adaptor);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

}


