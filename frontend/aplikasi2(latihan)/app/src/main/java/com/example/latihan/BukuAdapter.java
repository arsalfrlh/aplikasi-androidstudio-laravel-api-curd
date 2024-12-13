package com.example.latihan;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.latihan.model.Buku;

import java.util.List;

public class BukuAdapter extends ArrayAdapter<Buku> {
    private Context konten;
    private List<Buku> bukuList;

    public BukuAdapter(Context konten, List<Buku> bukuList){
        super(konten, R.layout.ly_buku_item, bukuList);
        this.konten = konten;
        this.bukuList = bukuList;
    }

    @Override
    public View getView(int posisi, View konversiView, ViewGroup grupView){
        if(konversiView == null){
            konversiView = LayoutInflater.from(konten).inflate(R.layout.ly_buku_item, grupView, false);
        }

        Buku buku = bukuList.get(posisi);

        TextView judulText = konversiView.findViewById(R.id.judulText);
        TextView penulisText = konversiView.findViewById(R.id.penulisText);
        ImageView gambarView = konversiView.findViewById(R.id.gambarView);

        judulText.setText(buku.getJudul());
        penulisText.setText(buku.getPenulis());
        Glide.with(konten).load(buku.getGambar()).into(gambarView);

        return konversiView;
    }
}
