package com.example.assignment.Services;


import com.example.assignment.Model.NhanVien;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {
    List<NhanVien> getAll();
    void deleteNV(UUID id);
    void addNV(NhanVien ms);
    void updateNV(UUID id,NhanVien ms);
    NhanVien detail(UUID id);
}
