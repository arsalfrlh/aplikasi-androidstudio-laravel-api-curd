package com.example.latihan.model;

public class Buku {
    private int id; //menyimpan id buku yang ada di api ke dalam variabel ini
    private String gambar;
    private String judul;
    private String penulis;
    private int stok;

    public int getId() { //menyimpan id buku di variabel tadi kedalam function ini
        return id; //mengembalikan nilai data id ini saat memanggil function ini
    }
    public void setId(int id) { //function ini untuk menyimpan id yang akan di ubah sesuai interaksi user
        this.id = id; //id yang sudah di ubah sesuai interaksi selanjutnya akan di simpan kedalam variabel id
    }

    public String getGambar(){
        String gambar_url = "http://10.0.2.2:8000/"; //url server
        return gambar_url + "images/" + gambar; //mengembalikan url server dan masuk ke folder images dan memanggil nama file gambar
    }
    public void setGambar(String gambar){
        this.gambar = gambar;
    }

    public String getJudul(){ //menyimpan judul buku di variabel tadi kedalam function ini
        return judul; //mengembalikan nilai data judul ini saat memanggil function ini
    }
    public void setJudul(String judul){ //function ini untuk menyimppan judul yang akan di ubah sesuai interaksi user
        this.judul = judul; //judul yang sudah di ubah sesuai interaksi, selanjutnya akan di simpan kedalam variabel judul
    }


    public String getPenulis(){
        return penulis;
    }
    public void setPennulis(){
        this.penulis = penulis;
    }

    public int getStok(){
        return stok;
    }
    public void setStok(int stok){
        this.stok = stok;
    }
}
