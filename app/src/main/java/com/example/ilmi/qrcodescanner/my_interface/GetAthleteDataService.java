package com.example.ilmi.qrcodescanner.my_interface;

import com.example.ilmi.qrcodescanner.model.AthleteList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ILMI on 8/15/2018.
 */

public interface GetAthleteDataService {
    @GET("login/view_json_akun/{nim}")
    Call<AthleteList> getAthlete(@Path("nim") String nim);
}
