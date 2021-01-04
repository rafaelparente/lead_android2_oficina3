package com.dellead.exemplo_multimidia;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MyMediaPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private boolean videoViewPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player);
        videoView = (VideoView) findViewById(R.id.videoview);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videoView != null) {
            videoView.suspend();
            videoView = null;
        }
    }

    public void executarVideo(View view) {
        if (!videoView.isPlaying()) {
            if (videoViewPaused) {
                videoView.start();
                videoViewPaused = false;
            } else {
                getVideoFile(view);
            }
        }
    }

    public void pausarVideo(View view) {
        if (videoView.isPlaying() && videoView.canPause()) {
            videoView.pause();
            videoViewPaused = true;
        }
    }

    public void pararVideo(View view) {
        videoView.stopPlayback();
        videoViewPaused = false;
    }

    public void getVideoFile(View v) {
        Uri uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        videoView.setOnPreparedListener(mp -> videoView.start());
        videoView.setVideoURI(uri);
    }

}
