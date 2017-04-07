package com.example.thaonzo.mymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    BigCompute bc=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.m2);
        mediaPlayer.setLooping(false);

        Button buttonStart = (Button) findViewById(R.id.start);
        Button buttonPause = (Button) findViewById(R.id.pause);
        Button buttonStop = (Button) findViewById(R.id.stop);
        final SeekBar seekBarProgress = (SeekBar) findViewById(R.id.seekProgress);
        final SeekBar seekBarVolum = (SeekBar) findViewById(R.id.seekSound);

        seekBarVolum.setProgress(50);

        int maxVolume = 100;
        float log1=(float)(Math.log(maxVolume-seekBarVolum.getProgress())/Math.log(maxVolume));

        mediaPlayer.setVolume(1-log1,1-log1);
        TextView maxduration = (TextView) findViewById(R.id.maxduration);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bc==null){
                    bc = new
                            BigCompute(getApplicationContext(),
                            (SeekBar) findViewById(R.id.seekProgress), mediaPlayer);
                    bc.execute();
                }

                mediaPlayer.start();
            }
        });
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mediaPlayer.pause();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.reset();

            }
        });


        seekBarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(seekBar.getProgress() * mediaPlayer.getDuration() / 100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarVolum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    int maxVolume = 100;
                    float log1=(float)(Math.log(maxVolume-seekBar.getProgress())/Math.log(maxVolume));

                    mediaPlayer.setVolume(1-log1,1-log1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }
}
