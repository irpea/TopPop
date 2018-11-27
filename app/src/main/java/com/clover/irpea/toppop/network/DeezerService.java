package com.clover.irpea.toppop.network;

import com.clover.irpea.toppop.modelchart.Album;
import com.clover.irpea.toppop.modelchart.ChartList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeezerService {
    @GET("chart/0/tracks")
    Call<ChartList> getChartList();

    @GET("album/{album_id}")
    Call<Album> getAlbumData(@Path("album_id") String id);
}
