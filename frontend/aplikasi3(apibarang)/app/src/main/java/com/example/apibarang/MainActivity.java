package com.example.apibarang;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apibarang.api.ApiService;
import com.example.apibarang.api.RetrofitClient;
import com.example.apibarang.model.ApiResponse;
import com.example.apibarang.model.Barang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextNama, editTextMerk, editTextStok;
    private Button buttonAdd, buttonGetAll, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.editTextId);
        editTextNama = findViewById(R.id.editTextNama);
        editTextMerk = findViewById(R.id.editTextMerk);
        editTextStok = findViewById(R.id.editTextStok);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGetAll = findViewById(R.id.buttonGetAll);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarang();
            }
        });

        buttonGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllBarang();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBarang();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBarang();
            }
        });
    }

    private void addBarang() {
        String nama = editTextNama.getText().toString();
        String merk = editTextMerk.getText().toString();
        int stok = Integer.parseInt(editTextStok.getText().toString());

        Barang barang = new Barang();
        barang.setNama_barang(nama);
        barang.setMerk(merk);
        barang.setStok(stok);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Barang> call = apiService.addBarang(barang);
        call.enqueue(new Callback<Barang>() {
            @Override
            public void onResponse(Call<Barang> call, Response<Barang> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal menambahkan barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllBarang() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ApiResponse> call = apiService.getAllBarang();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Barang> barangList = response.body().getData();
                    StringBuilder result = new StringBuilder();
                    for (Barang barang : barangList) {
                        result.append("ID: ").append(barang.getId())
                                .append(", Nama: ").append(barang.getNama_barang())
                                .append(", Merk: ").append(barang.getMerk())
                                .append(", Stok: ").append(barang.getStok()).append("\n");
                    }
                    Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal mendapatkan data barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateBarang() {
        int id = Integer.parseInt(editTextId.getText().toString());
        String nama = editTextNama.getText().toString();
        String merk = editTextMerk.getText().toString();
        int stok = Integer.parseInt(editTextStok.getText().toString());

        Barang barang = new Barang();
        barang.setNama_barang(nama);
        barang.setMerk(merk);
        barang.setStok(stok);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Barang> call = apiService.updateBarang(id, barang);
        call.enqueue(new Callback<Barang>() {
            @Override
            public void onResponse(Call<Barang> call, Response<Barang> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Barang berhasil diperbarui", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal memperbarui barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteBarang() {
        int id = Integer.parseInt(editTextId.getText().toString());

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.deleteBarang(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Barang berhasil dihapus", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal menghapus barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}