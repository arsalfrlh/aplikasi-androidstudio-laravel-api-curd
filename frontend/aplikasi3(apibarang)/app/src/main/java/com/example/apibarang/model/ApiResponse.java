package com.example.apibarang.model;

import java.util.List;

public class ApiResponse {
    private List<Barang> data;

    public List<Barang> getData(){
        return data;
    }

    public void setData(List<Barang> data) {
        this.data = data;
    }
}
