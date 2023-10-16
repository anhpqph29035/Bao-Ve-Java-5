package com.example.assignment.Services;

import com.example.assignment.Model.SanPham;

import java.util.List;
import java.util.UUID;


public interface SanPhamService {
    List<SanPham> getAll();
    void deleteSP(UUID id);
    void addSP(SanPham sp);
    void updateSP(UUID id,SanPham sp);
    SanPham detail(UUID id);
}
