package com.example.latihan.nework;

import retrofit2.Retrofit; //import retrofit seperti DriverManager di netbeans
import retrofit2.converter.gson.GsonConverterFactory; //untuk mengkoversi data api

public class RetrofitClient {
    private static Retrofit koneksi; //menyimpan koneksi API Retrofit ke dalam variabel koneksi|seperti di java netbeans
    private static final String API_URL = "http://10.0.2.2:8000/api/"; //url API di simpan kedalam variable ini|seperti url localhost di DriverManager Netbeans

    public static Retrofit getConfig(){
        if(koneksi == null){
            koneksi = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return koneksi;
    }
}
