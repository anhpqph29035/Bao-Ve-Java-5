package com.example.assignment.Services.impl;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.NhanVien;
import com.example.assignment.Repository.NhanVienRespository;
import com.example.assignment.Services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    NhanVienRespository nhanVienRespository;

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRespository.findAll();
    }

    @Override
    public void deleteNV(UUID id) {
        nhanVienRespository.deleteById(id);
    }

    @Override
    public void addNV(NhanVien ms) {
        nhanVienRespository.save(ms);
    }

    @Override
    public void updateNV(UUID id, NhanVien ms) {
        nhanVienRespository.save(ms);
    }

    @Override
    public NhanVien detail(UUID id) {
         NhanVien ms = nhanVienRespository.getReferenceById(id);
        return ms;
    }
}
