package com.example.assignment.Services.impl;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.CuaHang;
import com.example.assignment.Repository.ChucVuRepository;
import com.example.assignment.Repository.CuaHangRepository;
import com.example.assignment.Services.ChucVuService;
import com.example.assignment.Services.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CuaHangServiceImpl implements CuaHangService {
    @Autowired
    CuaHangRepository cuaHangRepository;

    @Override
    public List<CuaHang> getAll() {
        return cuaHangRepository.findAll();
    }

    @Override
    public void deleteCH(UUID id) {
        cuaHangRepository.deleteById(id);
    }

    @Override
    public void addCH(CuaHang ms) {
        cuaHangRepository.save(ms);
    }

    @Override
    public void updateCH(UUID id, CuaHang ms) {
        cuaHangRepository.save(ms);
    }

    @Override
    public CuaHang detail(UUID id) {
         CuaHang ms = cuaHangRepository.getReferenceById(id);
        return ms;
    }
}
