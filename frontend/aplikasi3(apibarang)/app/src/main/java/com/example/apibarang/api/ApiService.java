package com.example.apibarang.api;

import com.example.apibarang.model.ApiResponse;
import com.example.apibarang.model.Barang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("barang")
    Call<ApiResponse> getAllBarang();

    @POST("barang/tambah/proses")
    Call<Barang> addBarang(@Body Barang barang);

    @GET("barang/edit/{id}")
    Call<Barang> getBarangById(@Path("id") int id);

    @PUT("barang/update/{id}")
    Call<Barang> updateBarang(@Path("id") int id, @Body Barang barang);

    @DELETE("barang/hapus/{id}")
    Call<Void> deleteBarang(@Path("id") int id);
}
