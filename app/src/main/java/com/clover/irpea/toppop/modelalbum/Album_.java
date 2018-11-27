package com.clover.irpea.toppop.modelalbum;


import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album_ {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cover_xl")
    @Expose
    private String cover_xl;
    @SerializedName("contributors")
    @Expose
    private ArrayList<Contributor> contributors;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Album_(int id, String title, String cover_xl, ArrayList<Contributor> contributors, Tracks tracks) {
        this.id = id;
        this.title = title;
        this.cover_xl = cover_xl;
        this.contributors = contributors;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover_xl;
    }

    public void setCover(String cover) {
        this.cover_xl = cover;
    }

    public ArrayList<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<Contributor> contributors) {
        this.contributors = contributors;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}