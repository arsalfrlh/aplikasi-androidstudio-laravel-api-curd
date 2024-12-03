package com.example.aplikasi;
import com.example.aplikasi.models.ApiResponse;
import com.example.aplikasi.models.Buku;
import com.example.aplikasi.network.RetrofitClient;
import com.example.aplikasi.network.ApiService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listViewBuku;
    private ApiService apiService;
    private List<Buku> bukuList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewBuku = findViewById(R.id.listViewBuku);
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Ambil data buku dari API
        getAllBuku();
    }

    private void getAllBuku() {
        apiService.getAllBuku().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bukuList = response.body().getData();
                    BukuAdapter adapter = new BukuAdapter(MainActivity.this, bukuList);
                    listViewBuku.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
