package com.clover.irpea.toppop.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.clover.irpea.toppop.R;
import com.clover.irpea.toppop.adapter.ChartAdapter;
import com.clover.irpea.toppop.modelchart.Chart;
import com.clover.irpea.toppop.modelchart.ChartList;
import com.clover.irpea.toppop.network.DeezerService;
import com.clover.irpea.toppop.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Chart> chartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        getRetrofitData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getRetrofitData();
                Log.wtf("SwipeRefresh called", "Swipe Refresh called");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_asc:
                sortChart(1);
                break;
            case R.id.sort_normal:
                sortChart(2);
                break;
            case R.id.sort_dsc:
                sortChart(3);
                break;
        }        
        return true;
    }

    private void sortChart(int no) {
        if(no == 1){
            Collections.sort(chartArrayList, new Comparator<Chart>() {
                @Override
                public int compare(Chart o1, Chart o2) {
                    return (o1.getDuration() > o2.getDuration()) ? -1: (o1.getDuration() < o2.getDuration()) ? 1:0;
                }
            });
            generateChart(chartArrayList);
        }
        if(no == 2){
            Collections.sort(chartArrayList, new Comparator<Chart>() {
                @Override
                public int compare(Chart o1, Chart o2) {
                    return (o1.getPosition() < o2.getPosition()) ? -1: (o1.getPosition() > o2.getPosition()) ? 1:0;
                }
            });
            generateChart(chartArrayList);
        }
        if(no == 3){
            Collections.sort(chartArrayList, new Comparator<Chart>() {
                @Override
                public int compare(Chart o1, Chart o2) {
                    return (o1.getDuration() < o2.getDuration()) ? -1: (o1.getDuration() > o2.getDuration()) ? 1:0;

                }
            });
            generateChart(chartArrayList);
        }
    }

    private void getRetrofitData() {
        //Handle for retrofit
        DeezerService service = RetrofitInstance.getRetrofitInstance().
                        create(DeezerService.class);

        //call method in the interface to get chart data
        Call<ChartList> call = service.getChartList();
        Log.wtf("URL called", call.request().url() + "");

        call.enqueue(new Callback<ChartList>() {
            @Override
            public void onResponse(Call<ChartList> call, Response<ChartList> response) {
                chartArrayList = response.body().getData();
                generateChart(chartArrayList);
            }

            @Override
            public void onFailure(Call<ChartList> call, Throwable t) {
                Toast.makeText(ChartActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateChart(ArrayList<Chart> data) {
        recyclerView = findViewById(R.id.chart_recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ChartActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ChartAdapter(ChartActivity.this, data, recyclerView);
        recyclerView.setAdapter(adapter);
    }
}
