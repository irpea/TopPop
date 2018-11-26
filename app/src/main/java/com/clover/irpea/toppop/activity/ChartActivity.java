package com.clover.irpea.toppop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.clover.irpea.toppop.R;
import com.clover.irpea.toppop.adapter.ChartAdapter;
import com.clover.irpea.toppop.model.Chart;
import com.clover.irpea.toppop.model.ChartList;
import com.clover.irpea.toppop.network.DeezerService;
import com.clover.irpea.toppop.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        //Handle for retrofit
        DeezerService service = RetrofitInstance.getRetrofitInstance().
                        create(DeezerService.class);

        //call method in the interface to get chart data
        Call<ChartList> call = service.getChartList();
        Log.wtf("URL called", call.request().url() + "");

        call.enqueue(new Callback<ChartList>() {
            @Override
            public void onResponse(Call<ChartList> call, Response<ChartList> response) {
                    generateChart(response.body().getData());
            }

            @Override
            public void onFailure(Call<ChartList> call, Throwable t) {
                Toast.makeText(ChartActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateChart(ArrayList<Chart> data) {
        recyclerView = findViewById(R.id.chart_recycle_view);
        adapter = new ChartAdapter(data);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ChartActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
