package com.example.aplikasi.models;

public class Buku {
    private int id;
    private String gambar;
    private String judul;
    private String penulis;
    private int stok;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGambarUrl() {
        // Ganti BASE_URL sesuai URL server Anda
        String BASE_URL = "http://10.0.2.2:8000/";
        return BASE_URL + "images/" + gambar;
    }
    public void setGambar(String gambar) { this.gambar = gambar; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}

