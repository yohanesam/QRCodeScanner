package com.example.ilmi.qrcodescanner.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ILMI on 8/15/2018.
 */

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://porseni.pnj.ac.id/index.php/"; // bisa pakai yang https://192.168.43.137

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
