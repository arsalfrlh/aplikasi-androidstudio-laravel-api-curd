package com.example.aplikasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasi.models.Buku;

import java.util.List;

public class BukuAdapter extends ArrayAdapter<Buku> {
    private Context context;
    private List<Buku> bukuList;

    public BukuAdapter(Context context, List<Buku> bukuList) {
        super(context, R.layout.buku_item, bukuList);
        this.context = context;
        this.bukuList = bukuList;
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

        return convertView;
    }
}

