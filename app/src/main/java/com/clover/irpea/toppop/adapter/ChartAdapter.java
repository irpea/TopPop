package com.clover.irpea.toppop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clover.irpea.toppop.R;
import com.clover.irpea.toppop.activity.ChartActivity;
import com.clover.irpea.toppop.activity.TrackActivity;
import com.clover.irpea.toppop.modelchart.Chart;

import java.util.ArrayList;
import java.util.Locale;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ChartViewHolder> {

    private RecyclerView recyclerView;
    private Activity activity;
    private ArrayList<Chart> dataList;

    public ChartAdapter(Activity activity, ArrayList<Chart> dataList, RecyclerView rv) {
        this.activity = activity;
        this.dataList = dataList;
        this.recyclerView = rv;
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

    class ChartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView numberId;
        private TextView songName;
        private TextView artistName;
        private TextView songDuration;

        ChartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            numberId = itemView.findViewById(R.id.numberId);
            songName = itemView.findViewById(R.id.song_name);
            songName.setSelected(true);
            artistName = itemView.findViewById(R.id.artist_name);
            artistName.setSelected(true);
            songDuration = itemView.findViewById(R.id.song_duration);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = 0;
            itemPosition = recyclerView.getChildLayoutPosition(v);

            Intent intent = new Intent(activity, TrackActivity.class);
            intent.putExtra("albumId", dataList.get(itemPosition).getAlbum().getId());
            intent.putExtra("songName", dataList.get(itemPosition).getTitle());
            activity.startActivity(intent);
        }
    }
}























