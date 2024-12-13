package com.example.aplikasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasi.models.Buku;
import com.example.aplikasi.network.ApiService;
import com.example.aplikasi.network.RetrofitClient;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BukuAdapter extends ArrayAdapter<Buku> {
    private Context context;
    private List<Buku> bukuList;
    private ApiService apiService;



    public BukuAdapter(Context context, List<Buku> bukuList) {
        super(context, R.layout.buku_item, bukuList);
        this.context = context;
        this.bukuList = bukuList;
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.buku_item, parent, false);
        }

        Buku buku = bukuList.get(position);

        TextView judulText = convertView.findViewById(R.id.judulText);
        TextView penulisText = convertView.findViewById(R.id.penulisText);
        ImageView gambarView = convertView.findViewById(R.id.gambarView);

        judulText.setText(buku.getJudul());
        penulisText.setText(buku.getPenulis());
        Glide.with(context).load(buku.getGambarUrl()).into(gambarView);

        convertView.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Pilih Aksi");
            builder.setItems(new CharSequence[]{"Edit", "Hapus"}, (dialog, which) -> {
                if (which == 0) {
                    showEditDialog(buku);
                } else if (which == 1) {
                    hapusBuku(buku.getId());
                }
            });
            builder.show();
            return true;
        });

        return convertView;
    }

    private void showEditDialog(Buku buku) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Buku");

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_buku, null);
        EditText etJudul = view.findViewById(R.id.etJudul);
        EditText etPenulis = view.findViewById(R.id.etPenulis);
        EditText etStok = view.findViewById(R.id.etStok);

        etJudul.setText(buku.getJudul());
        etPenulis.setText(buku.getPenulis());
        etStok.setText(String.valueOf(buku.getStok()));

        builder.setView(view);
        builder.setPositiveButton("Update", (dialog, which) -> {
            RequestBody judulBody = RequestBody.create(MultipartBody.FORM, etJudul.getText().toString());
            RequestBody penulisBody = RequestBody.create(MultipartBody.FORM, etPenulis.getText().toString());
            RequestBody stokBody = RequestBody.create(MultipartBody.FORM, etStok.getText().toString());

            apiService.updateBuku(buku.getId(), null, judulBody, penulisBody, stokBody).enqueue(new Callback<Buku>() {
                @Override
                public void onResponse(Call<Buku> call, Response<Buku> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Buku berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Gagal memperbarui buku", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Buku> call, Throwable t) {
                    Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }

    private void hapusBuku(int id) {
        apiService.deleteBuku(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Buku berhasil dihapus", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Gagal menghapus buku", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
