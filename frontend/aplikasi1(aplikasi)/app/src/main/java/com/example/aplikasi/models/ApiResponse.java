package com.example.aplikasi.models;

import java.util.List;

public class ApiResponse {
    private String status;
    private List<Buku> data;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Buku> getData() { return data; }
    public void setData(List<Buku> data) { this.data = data; }
}
