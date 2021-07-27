package com.example.mediaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioTimestamp;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.media.TimedText;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = new MediaPlayer();
    TextView textView;
    Button PlayPause;
    Button ib3,playlist;
    SeekBar sb;
    MediaTimestamp startTime;
//    Boolean flag=false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayPause = findViewById(R.id.PlayPause);
        ib3 = findViewById(R.id.button);
        playlist = findViewById(R.id.PlayList);
        textView = findViewById(R.id.songname);
        String TempHolder = getIntent().getStringExtra("SongName");
        textView.setText(TempHolder);
//        startTime = findViewById(R.id.editTextTime);
        sb = findViewById(R.id.seekBar2);
            if(TempHolder==null){
                    mp.stop();
                    mp = MediaPlayer.create(this,R.raw.ignite);
            }else {
                try {
                    mp.stop();
                    mp.setDataSource(TempHolder);
                    mp.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        mp.start();
          startTime = mp.getTimestamp();
        mp.setOnPreparedListener(mps -> {
            Toast.makeText(MainActivity.this,"Let's Play!",Toast.LENGTH_SHORT).show();
            mps.start();
            sb.setMax(mp.getDuration());
            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser){
                        mp.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    mp.start();
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                        mp.stop();
                }
            });
        });
//        mp.prepareAsync();
        PlayPause.setOnClickListener(v -> {
            if(!mp.isPlaying()){
            mp.start();
            PlayPause.setText("Pause");
            }else{
                mp.pause();
                PlayPause.setText("Play");
            }

        });


        ib3.setOnClickListener(v -> {
            if(mp.isPlaying()) {
                mp.stop();
//                try {
//                    mp.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
    public void OpenActivity(View v){
        mp.stop();
        Intent intent = new Intent(this,MusicLists.class);
        startActivity(intent);
    }
}
