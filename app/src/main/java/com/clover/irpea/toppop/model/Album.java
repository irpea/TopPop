package com.clover.irpea.toppop.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("cover_small")
    @Expose
    private String coverSmall;
    @SerializedName("tracklist")
    @Expose
    private String tracklist;

    public Album(int id, String title, String cover, String coverSmall, String tracklist) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.coverSmall = coverSmall;
        this.tracklist = tracklist;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public String getTracklist() {
        return tracklist;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}