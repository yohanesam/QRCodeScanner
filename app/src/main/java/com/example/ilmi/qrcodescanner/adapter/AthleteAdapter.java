package com.example.ilmi.qrcodescanner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ilmi.qrcodescanner.R;
import com.example.ilmi.qrcodescanner.model.Athlete;

import java.util.ArrayList;

/**
 * Created by ILMI on 8/15/2018.
 */

public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.AthleteViewHolder> {

    private ArrayList<Athlete> athleteArrayList;
    private Context context;

    public AthleteAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Athlete> getAthleteList() {
        return athleteArrayList;
    }

    public void setAthleteList(ArrayList<Athlete> athleteArrayList) {
        this.athleteArrayList = athleteArrayList;
    }

//    public void setContext(Context context) {
//        this.context = context;
//    }


    @Override
    public AthleteAdapter.AthleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_item_row, parent, false);
        AthleteViewHolder athleteViewHolder = new AthleteViewHolder(mView);
        return athleteViewHolder;
    }

    @Override
    public void onBindViewHolder(AthleteAdapter.AthleteViewHolder holder, int position) {

        Athlete athlete = athleteArrayList.get(position);
        String BASE_URL = "https://porseni.pnj.ac.id/assets/upload/foto/";

        holder.tvName.setText(athlete.getNama());
        holder.tvNim.setText(athlete.getNim());
        holder.tvPolytechnic.setText(athlete.getPoliteknik());
        holder.tvDate.setText(athlete.getDate());

        switch (athlete.getGender()) {
            case "L" :
                holder.tvGender.setText("Laki-Laki");
                break;

            case "P" :
                holder.tvGender.setText("Perempuan");
                break;

            default:
                break;
        }

        holder.tvCompetitionNumber1.setText(athlete.getCabangPertama());
        holder.tvCompetitionNumber2.setText(athlete.getCabangKedua());

        Glide.with(context)
                .load(BASE_URL+athlete.getFoto())
                .crossFade()
                .into(holder.imgSelfPhoto);

    }

    @Override
    public int getItemCount() {
        return athleteArrayList.size();
    }

    class AthleteViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNim, tvPolytechnic, tvGender, tvDate, tvCompetitionNumber1, tvCompetitionNumber2;
        ImageView imgSelfPhoto;

        public AthleteViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView)itemView.findViewById(R.id.tv_item_name);
            tvNim = (TextView)itemView.findViewById(R.id.tv_item_nim);
            tvDate = (TextView)itemView.findViewById(R.id.tv_item_date);
            tvPolytechnic = (TextView)itemView.findViewById(R.id.tv_item_polytechnic);
            tvGender = (TextView)itemView.findViewById(R.id.tv_item_gender);
            tvCompetitionNumber1 = (TextView)itemView.findViewById(R.id.tv_competition_number_1);
            tvCompetitionNumber2 = (TextView)itemView.findViewById(R.id.tv_competition_number_2);
            imgSelfPhoto = (ImageView)itemView.findViewById(R.id.img_self_photo);
        }
    }
}
