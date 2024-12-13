package com.example.latihan.nework;

import com.example.latihan.model.ApiResponse;
import com.example.latihan.model.Buku;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    @GET("perpus/buku")
    Call<ApiResponse> getAllBuku(); //function end poin API untuk memanggil semua data buku

    @GET("perpus/buku/edit/{id}")
    Call<ApiResponse> getBukuById(@Path("id") int id); //function end poin API untuk menampilkan data buku yang dipilih

    @Multipart
    @POST("perpus/buku/tambah")
    Call<Buku> createBuku( //function end poin API untuk menambahkan data buku
            @Part MultipartBody.Part gambar, //array key
            @Part("judul") RequestBody judul, //array key
            @Part("penulis") RequestBody penulis,
            @Part("Stok") RequestBody stok
            );

    @Multipart
    @PUT("perpus/buku/update/{id}")
    Call<Buku>updateBuku( //function end poin API untuk mengupdate data buku
            @Path("id") int id,
            @Part MultipartBody.Part gambar,
            @Part("judul") RequestBody judul,
            @Part("penulis") RequestBody penulis,
            @Part("stok") RequestBody stok
    );

    @DELETE("perpus/buku/hapus/{id}") //function end poin API untuk menghapus data buku
    Call<Void> deleteBuku(@Path("id") int id);
}
