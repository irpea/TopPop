package com.clover.irpea.toppop.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clover.irpea.toppop.R;
import com.clover.irpea.toppop.adapter.ChartAdapter;
import com.clover.irpea.toppop.modelchart.Album;
import com.clover.irpea.toppop.modelchart.Chart;
import com.clover.irpea.toppop.modelchart.ChartList;
import com.clover.irpea.toppop.network.DeezerService;
import com.clover.irpea.toppop.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackActivity extends AppCompatActivity {

    private TextView songName;
    private TextView artistName;
    private TextView albumName;
    private ImageView coverImage;
    private TextView tracklist;

    private Picasso picasso;
    private int albumId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        initWidgets();

        if(savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
            if(extra == null){
                albumId = 0;
            } else {
                albumId = extra.getInt("albumId");
            }
        } else {
            albumId = (int) savedInstanceState.getSerializable("albumId");
        }

//        //Handle for retrofit
//        DeezerService service = RetrofitInstance.getRetrofitInstance().
//                create(DeezerService.class);
//
//        //call method in the interface to get chart data
//        Call<Album> call = service.getAlbumData();
//        Log.wtf("URL called", call.request().url() + "");
//
//        call.enqueue(new Callback<ChartList>() {
//            @Override
//            public void onResponse(Call<ChartList> call, Response<ChartList> response) {
//                chartArrayList = response.body().getData();
//                generateChart(chartArrayList);
//            }
//
//            @Override
//            public void onFailure(Call<ChartList> call, Throwable t) {
//                Toast.makeText(ChartActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void initWidgets() {
        songName = findViewById(R.id.track_name);
        artistName = findViewById(R.id.artist_track_name);
        albumName = findViewById(R.id.album_track_name);
        coverImage = findViewById(R.id.cover_img);
        tracklist = findViewById(R.id.tracklist);
    }
}
