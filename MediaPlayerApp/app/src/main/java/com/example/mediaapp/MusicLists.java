package com.example.mediaapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaSession2Service;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MusicLists extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST = 1;
    private ArrayList<String> arrayList,arr;
    ListView listView;
    private ArrayAdapter<String> adapter;
    private String currentLocation;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_lists);

        if(ContextCompat.checkSelfPermission(MusicLists.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MusicLists.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(MusicLists.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }else{
                ActivityCompat.requestPermissions(MusicLists.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
        }else{
            setContent();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void setContent(){
        listView = (ListView) findViewById(R.id.ListView);
        arrayList = new ArrayList<>();
        arr = new ArrayList<>();
        getMusic();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int cnt=0;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Open Music Player to Playing Songs
                String tempListView = arr.get(position).toString();
                Intent intent = new Intent(MusicLists.this,MainActivity.class);
                intent.putExtra("SongName",tempListView);
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void getMusic()
    {
        ContentResolver contentResolver = getContentResolver();
        Uri songuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songuri,null,null,null,null);

        if(songCursor!=null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLocation = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do{
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                currentLocation = songCursor.getString(songLocation);
                arrayList.add("Title: "+currentTitle+"\nArtist: "+currentArtist+"\nLocation: "+currentLocation);
                arr.add(currentLocation);

            }while(songCursor.moveToNext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MusicLists.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                        //
                    } else {
                        Toast.makeText(this, "No Permission granted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    return;
                }
            }
        }
    }
    public void BackToMainActivity(View v){
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
        Toast.makeText(this,"Function Called",Toast.LENGTH_SHORT).show();
    }
}
