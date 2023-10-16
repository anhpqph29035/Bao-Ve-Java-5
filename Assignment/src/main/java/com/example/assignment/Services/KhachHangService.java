package com.example.assignment.Services;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.KhachHang;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {
    List<KhachHang> getAll();
    void deleteKH(UUID id);
    void addKH(KhachHang ms);
    void updateKH(UUID id,KhachHang ms);
   KhachHang detail(UUID id);
}
