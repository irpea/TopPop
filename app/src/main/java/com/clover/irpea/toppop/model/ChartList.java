package com.clover.irpea.toppop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChartList {

    @SerializedName("data")
    @Expose
    private ArrayList<Chart> data;

    public ArrayList<Chart> getData() {
        return data;
    }

    public void setData(ArrayList<Chart> data) {
        this.data = data;
    }
}