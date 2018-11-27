package com.clover.irpea.toppop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clover.irpea.toppop.R;
import com.clover.irpea.toppop.model.Chart;

import java.util.ArrayList;
import java.util.Locale;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ChartViewHolder> {

    private ArrayList<Chart> dataList;

    public ChartAdapter(ArrayList<Chart> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_chart, viewGroup, false);
        return new ChartViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder chartViewHolder, int i) {
        String positionNumber = String.format(Locale.getDefault(), "%02d.", dataList.get(i).getPosition());
        int songDuration = dataList.get(i).getDuration();
        int minuteDuration = songDuration/60;
        int secondDuration = songDuration%60;
        String duration = String.format(Locale.getDefault(), "%02d:%02d", minuteDuration, secondDuration);

        chartViewHolder.numberId.setText(positionNumber);
        chartViewHolder.songName.setText(dataList.get(i).getTitle());
        chartViewHolder.artistName.setText(dataList.get(i).getArtist().getName());
        chartViewHolder.songDuration.setText(duration);
    }

    @Override
    public int getItemCount() {
        Log.wtf("Datalist size", dataList.size() + "");
            return dataList.size();
    }

    class ChartViewHolder extends RecyclerView.ViewHolder{

        private TextView numberId;
        private TextView songName;
        private TextView artistName;
        private TextView songDuration;

        ChartViewHolder(@NonNull View itemView) {
            super(itemView);
            numberId = itemView.findViewById(R.id.numberId);
            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            songDuration = itemView.findViewById(R.id.song_duration);
        }
    }
}
