package com.example.aplikasi.network;

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

import com.example.aplikasi.models.ApiResponse;
import com.example.aplikasi.models.Buku;

public interface ApiService {
    // GET: Menampilkan semua data buku
    @GET("perpus/buku")
    Call<ApiResponse> getAllBuku();

    @GET("perpus/buku/edit/{id}")
    Call<ApiResponse> getBukuById(@Path("id") int id);

    // POST: Menambahkan data buku
    @Multipart
    @POST("perpus/buku/tambah")
    Call<Buku> createBuku(
            @Part MultipartBody.Part gambar,
            @Part("judul") RequestBody judul,
            @Part("penulis") RequestBody penulis,
            @Part("stok") RequestBody stok
    );

    // PUT: Mengupdate data buku
    @Multipart
    @PUT("perpus/buku/update/{id}")
    Call<Buku> updateBuku(
            @Path("id") int id,
            @Part MultipartBody.Part gambar,
            @Part("judul") RequestBody judul,
            @Part("penulis") RequestBody penulis,
            @Part("stok") RequestBody stok
    );

    // DELETE: Menghapus data buku
    @DELETE("perpus/buku/hapus/{id}")
    Call<Void> deleteBuku(@Path("id") int id);
}

