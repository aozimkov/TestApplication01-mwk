package com.example.android.testapplication01;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Word> words;
    private MediaPlayer mMediaPlayer = null;
    private AudioManager mAudioMAnager; //Init Audio Manager
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener; //AM Focus Init Listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa",R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        mAudioMAnager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                mMediaPlayerReleaser();

                mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                            // Pause playback because your Audio Focus was
                            // temporarily stolen, but will be back soon.
                            // i.e. for a phone call
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);

                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                            // Stop playback, because you lost the Audio Focus.
                            // i.e. the user started some other playback app
                            // Remember to unregister your controls/buttons here.
                            // And release the kra — Audio Focus!
                            // You’re done.
                            mMediaPlayerReleaser();

                        } else if (focusChange ==
                                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                            // Lower the volume, because something else is also
                            // playing audio over you.
                            // i.e. for notifications or navigation directions
                            // Depending on your audio playback, you may prefer to
                            // pause playback here instead. You do you.
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                            // Resume playback, because you hold the Audio Focus
                            // again!
                            // i.e. the phone call ended or the nav directions
                            // are finished
                            // If you implement ducking and lower the volume, be
                            // sure to return it to normal here, as well.
                            mMediaPlayer.start();
                        }
                    }
                };

                int result = mAudioMAnager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmWordAudio());
                    mMediaPlayer.start();
                }

                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mMediaPlayerReleaser();
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayerReleaser();
    }

    private void mMediaPlayerReleaser(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioMAnager.abandonAudioFocus(mOnAudioFocusChangeListener); // Request Abadon. When you make autocomplete look on las word here!
        }
    }
}
