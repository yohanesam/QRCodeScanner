package com.example.ilmi.qrcodescanner.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.ilmi.qrcodescanner.R;
import com.example.ilmi.qrcodescanner.adapter.AthleteAdapter;
import com.example.ilmi.qrcodescanner.model.Athlete;
import com.example.ilmi.qrcodescanner.model.AthleteList;
import com.example.ilmi.qrcodescanner.my_interface.GetAthleteDataService;
import com.example.ilmi.qrcodescanner.network.RetrofitInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AthleteDesc extends AppCompatActivity {

    public static String EXTRA_NAME = "extra_name";
    TextView athleteName;
    private RecyclerView mRecycle;
    GetAthleteDataService dataService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_desc);

        String value = getIntent().getStringExtra(EXTRA_NAME);

        JSONObject obj = null;
        try {
            obj = new JSONObject(value);
            String jsonIntentValue = obj.getString("nim");
            Log.v("TAG", jsonIntentValue);
            setAllAthleteItem(jsonIntentValue);
        }

        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAllAthleteItem(String jsonIntentValue) {

        //handler for retrofit interface
        dataService = RetrofitInstance.getRetrofit().create(GetAthleteDataService.class);

        final Call<AthleteList> listCall = dataService.getAthlete(jsonIntentValue);


        Log.wtf("URL Called", listCall.request().url() + "");

        listCall.enqueue(new Callback<AthleteList>() {

            @Override
            public void onResponse(Call<AthleteList> call, Response<AthleteList> response) {
                if(response.isSuccessful()) {
                    if(response.body().getAthleteArrayList().isEmpty()) {
                        showIsNEmptyValueMessage();
                    }

                    else {
                        generateAthleteList(response.body().getAthleteArrayList());
                    }
                }

                else {
                    showOnFailureMessage();
                }

            }

            @Override
            public void onFailure(Call<AthleteList> call, Throwable t) {
                showOnFailureMessage();
            }
        });
    }

    private void generateAthleteList(ArrayList<Athlete> athleteArrayList) {
        mRecycle = (RecyclerView) findViewById(R.id.rv_athlete);
        AthleteAdapter athleteAdapter = new AthleteAdapter(this);
        athleteAdapter.setAthleteList(athleteArrayList);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);

        mRecycle.setLayoutManager(mLayout);
        mRecycle.setAdapter(athleteAdapter);
    }

    private void showOnFailureMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AthleteDesc.this);
        builder.setTitle("Kesalahan");
        builder.setMessage("Terjadi Kesalahan. Periksa Kembali Jaringan Internet Anda dan Coba Lagi");

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog
                .show();
    }


    private void showIsNEmptyValueMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AthleteDesc.this);
        builder.setTitle("Kesalahan");
        builder.setMessage("Maaf, Mahasiswa yang Anda Cari Tidak Dapat Ditemukan atau Tidak Terdaftar");

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog
                .show();
    }

}