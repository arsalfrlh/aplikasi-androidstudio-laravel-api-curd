package com.example.aplikasi;

import com.example.aplikasi.models.ApiResponse;
import com.example.aplikasi.models.Buku;
import com.example.aplikasi.network.RetrofitClient;
import com.example.aplikasi.network.ApiService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listViewBuku;
    private Button btnTambahBuku;
    private ApiService apiService;
    private List<Buku> bukuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewBuku = findViewById(R.id.listViewBuku);
        btnTambahBuku = findViewById(R.id.btnTambahBuku);
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Ambil data buku dari API
        getAllBuku();

        // Tambah buku
        btnTambahBuku.setOnClickListener(v -> showTambahDialog());
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

    private void showTambahDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Buku");

        View view = getLayoutInflater().inflate(R.layout.dialog_buku, null);
        EditText etJudul = view.findViewById(R.id.etJudul);
        EditText etPenulis = view.findViewById(R.id.etPenulis);
        EditText etStok = view.findViewById(R.id.etStok);

        builder.setView(view);
        builder.setPositiveButton("Tambah", (dialog, which) -> {
            String judul = etJudul.getText().toString();
            String penulis = etPenulis.getText().toString();
            String stok = etStok.getText().toString();

            tambahBuku(judul, penulis, stok);
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }

    private void tambahBuku(String judul, String penulis, String stok) {
        RequestBody judulBody = RequestBody.create(okhttp3.MultipartBody.FORM, judul);
        RequestBody penulisBody = RequestBody.create(okhttp3.MultipartBody.FORM, penulis);
        RequestBody stokBody = RequestBody.create(okhttp3.MultipartBody.FORM, stok);

        apiService.createBuku(null, judulBody, penulisBody, stokBody).enqueue(new Callback<Buku>() {
            @Override
            public void onResponse(Call<Buku> call, Response<Buku> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Buku berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    getAllBuku();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal menambahkan buku", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Buku> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
