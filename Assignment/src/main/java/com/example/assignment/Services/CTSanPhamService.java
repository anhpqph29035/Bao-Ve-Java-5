package com.example.assignment.Services;

import com.example.assignment.Model.ChiTietSanPham;

import java.util.List;
import java.util.UUID;

public interface CTSanPhamService {
    List<ChiTietSanPham> getAll();
    void deleteCTSP(UUID id);
    void addCTSP(ChiTietSanPham ms);
    void updateCTSP(UUID id,ChiTietSanPham ms);
    ChiTietSanPham detail(UUID id);
}
