package com.clover.irpea.toppop.modelalbum;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album_ {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cover_big")
    @Expose
    private String cover_big;
    @SerializedName("contributors")
    @Expose
    private List<Contributor> contributors;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Album_(int id, String title, String cover_big, List<Contributor> contributors, Tracks tracks) {
        this.id = id;
        this.title = title;
        this.cover_big = cover_big;
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
        return cover_big;
    }

    public void setCover(String cover) {
        this.cover_big = cover;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}