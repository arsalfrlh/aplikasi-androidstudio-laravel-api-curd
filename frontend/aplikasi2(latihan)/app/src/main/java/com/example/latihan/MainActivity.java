package com.example.latihan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.latihan.model.ApiResponse;
import com.example.latihan.model.Buku;
import com.example.latihan.nework.ApiService;
import com.example.latihan.nework.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView daftarBuku; //variabel listview di simpan di sini
    private ApiService ApiService; //menyimpan interface end point API kedalam ApiService.java
    private List<Buku> bukuList = new ArrayList<>(); //menggunkan model <Buku> yang disimpan kedalam variabel
    private ArrayAdapter<String> adapter; //seperti SetModel pada tabel di java

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //untuk menampilkan isi konten view/output pada MainActivity.java

        daftarBuku = findViewById(R.id.ly_daftarBuku); //menyimpan layout listview activity_main.xml kedalam variabel ini
        ApiService = RetrofitClient.getConfig().create(ApiService.class); //koneksi API Retrofit disimpan di variabel ini|RetyrofitClient.java dan class atau function getConfig|end Point API ApiService.java

        getAllBuku();//menampilkan data yang ada di function ini|end Point API pada ApiService.java untuk menampikan smua data buku
    }

    private void getAllBuku(){ //function untuk menampilkan data buku dengan method GET
        ApiService.getAllBuku().enqueue(new Callback<ApiResponse>() { //memanggil function getAllBuku pada file ApiService.java dan data buku di simpan kedalam model <Buku> Buku.java
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) { //jika respon api berhasil maka akan menampilkan data berikut
                    bukuList = response.body().getData(); // Ambil data dari respons|getData mengambil data dari ApiResponse.java
                    BukuAdapter adapter = new BukuAdapter(MainActivity.this, bukuList); //file BukuAdapter.java menyimpannya menjadi adapter seperti setTabelModel pada netbeans|BukuAdapter.java akan isi konten MainActivitynya di simpan ke variabel ini
                    daftarBuku.setAdapter(adapter); //seperti setTableModel pada netbeans
                }else {
                    Toast.makeText(MainActivity.this,"Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}