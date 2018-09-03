package com.example.ilmi.qrcodescanner.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ILMI on 8/15/2018.
 */

public class Athlete {
    @SerializedName("nama")
    private String nama;

    @SerializedName("nim")
    private String nim;

    @SerializedName("gender")
    private String gender;

    @SerializedName("tanggal_lahir")
    private String date;

    @SerializedName("politeknik")
    private String politeknik;

    @SerializedName("foto")
    private String foto;
//
//    @SerializedName("ss_ktp")
//    private String ssKtp;

    @SerializedName("cabang_pertama")
    private String cabangPertama;

    @SerializedName("cabang_kedua")
    private String cabangKedua;

    @SerializedName("status")
    private String status;


    /**
     * Method constructor, setter, dan getter kelas Athlete
     * Ganti sesuai dengan kebutuhan
     */

    public Athlete(String nama, String nim, String gender, String date,
                   String politeknik, String cabangPertama, String cabangKedua, String status) {

        this.nama = nama;
        this.nim = nim;
        this.gender = gender;
        this.date = date;
        this.politeknik = politeknik;
        this.cabangPertama = cabangPertama;
        this.cabangKedua = cabangKedua;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoliteknik() {
        return politeknik;
    }

    public void setPoliteknik(String politeknik) {
        this.politeknik = politeknik;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCabangPertama() {
        return cabangPertama;
    }

    public void setCabangPertama(String cabangPertama) {
        this.cabangPertama = cabangPertama;
    }

    public String getCabangKedua() {
        return cabangKedua;
    }

    public void setCabangKedua(String cabangKedua) {
        this.cabangKedua = cabangKedua;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
