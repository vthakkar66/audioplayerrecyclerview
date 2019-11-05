package com.app.audioplayerrecyclerview;

public class PlayerModel {

    public String audioURL;
    public boolean isPlaying = false;
    public boolean isLoading = false;


    public PlayerModel(String audioURL, boolean isPlaying,boolean isLoading) {
        this.audioURL = audioURL;
        this.isPlaying = isPlaying;
        this.isLoading = isLoading;
    }
}
