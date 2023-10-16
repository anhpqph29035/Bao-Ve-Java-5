package com.example.assignment.Services.impl;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.KhachHang;
import com.example.assignment.Repository.ChucVuRepository;
import com.example.assignment.Repository.KhachHangRepository;
import com.example.assignment.Services.ChucVuService;
import com.example.assignment.Services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public void deleteKH(UUID id) {
        khachHangRepository.deleteById(id);
    }

    @Override
    public void addKH(KhachHang ms) {
        khachHangRepository.save(ms);
    }

    @Override
    public void updateKH(UUID id, KhachHang ms) {
        khachHangRepository.save(ms);
    }

    @Override
    public KhachHang detail(UUID id) {
         KhachHang ms = khachHangRepository.getReferenceById(id);
        return ms;
    }
}
