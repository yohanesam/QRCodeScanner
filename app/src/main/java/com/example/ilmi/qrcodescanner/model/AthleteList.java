package com.example.ilmi.qrcodescanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ILMI on 8/15/2018.
 */

public class AthleteList {

    @SerializedName("athlete_list")
    private ArrayList<Athlete> athleteArrayList;

    public ArrayList<Athlete> getAthleteArrayList() {
        return athleteArrayList;
    }

    public void setAthleteArrayList(ArrayList<Athlete> athleteArrayList) {
        this.athleteArrayList = athleteArrayList;
    }
}
