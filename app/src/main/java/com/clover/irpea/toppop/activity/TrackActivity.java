package com.clover.irpea.toppop.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.clover.irpea.toppop.R;
import com.clover.irpea.toppop.modelalbum.Album_;
import com.clover.irpea.toppop.modelalbum.Data;
import com.clover.irpea.toppop.modelchart.Album;
import com.clover.irpea.toppop.network.DeezerService;
import com.clover.irpea.toppop.network.RetrofitInstance;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackActivity extends AppCompatActivity {

    private TextView songName;
    private TextView artistName;
    private TextView albumName;
    private ImageView coverImage;
    private TextView tracklist;

    private Album_ album;

    private Picasso picasso;
    private int albumId = 0;
    private String trackName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        initWidgets();

        if(savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
            if(extra == null){
                albumId = 0;
                trackName = null;
            } else {
                albumId = extra.getInt("albumId");
                trackName = extra.getString("songName");
            }
        }

        //Handle for retrofit
        DeezerService service = RetrofitInstance.getRetrofitInstance().
                create(DeezerService.class);

        //call method in the interface to get chart data
        Call<Album_> call = service.getAlbumData(albumId + "");
        Log.wtf("URL called", call.request().url() + "");

        call.enqueue(new Callback<Album_>() {
            @Override
            public void onResponse(Call<Album_> call, Response<Album_> response) {
                album = response.body();
                generateAlbum(album);
            }

            @Override
            public void onFailure(Call<Album_> call, Throwable t) {

            }
        });
    }

    private void generateAlbum(Album_ album) {
        songName.setText(trackName);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<album.getContributors().size(); i++){
            if(i < album.getContributors().size()-1) {
                sb.append(album.getContributors().get(i).getName() + ", ");
            } else if(i <= (album.getContributors().size()-1)){
                sb.append(album.getContributors().get(i).getName());
            }
        }
        artistName.setText(sb.toString());
        albumName.setText("Album: " +  album.getTitle());

        picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(new OkHttpClient()))
                .build();

        picasso.load(album.getCover())
                .into(coverImage);

        ArrayList<Data> tracks = album.getTracks().getData();
        StringBuilder albumSongs = new StringBuilder();
        int i=1;

        for(Data data : tracks){
            if(i < tracks.size()) {
                albumSongs.append(data.getTitle() + ", ");
                i++;
            } else if(i == tracks.size()){
                albumSongs.append(data.getTitle());

            }
        }
        tracklist.setText("List of songs from the album: " + albumSongs.toString());

    }

    private void initWidgets() {
        songName = findViewById(R.id.track_name);
        artistName = findViewById(R.id.artist_track_name);
        artistName.setSelected(true);
        albumName = findViewById(R.id.album_track_name);
        coverImage = findViewById(R.id.cover_img);
        tracklist = findViewById(R.id.tracklist);
        tracklist.setMovementMethod(new ScrollingMovementMethod());
    }
}
