package com.clover.irpea.toppop.network;

import com.clover.irpea.toppop.model.ChartList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeezerService {
    @GET("chart/0/tracks")
    Call<ChartList> getChartList();
}
