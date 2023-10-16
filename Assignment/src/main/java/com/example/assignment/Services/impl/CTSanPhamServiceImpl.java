package com.example.assignment.Services.impl;

import com.example.assignment.Model.ChiTietSanPham;
import com.example.assignment.Repository.CTSanPhamRepository;
import com.example.assignment.Services.CTSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CTSanPhamServiceImpl implements CTSanPhamService {

    @Autowired
    CTSanPhamRepository ctSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return ctSanPhamRepository.findAll();
    }

    @Override
    public void deleteCTSP(UUID id) {
        ctSanPhamRepository.deleteById(id);
    }

    @Override
    public void addCTSP(ChiTietSanPham sp) {
        ctSanPhamRepository.save(sp);
    }

    @Override
    public void updateCTSP(UUID id, ChiTietSanPham sp) {
        ctSanPhamRepository.save(sp);
    }

    @Override
    public ChiTietSanPham detail(UUID id) {
        ChiTietSanPham sp = ctSanPhamRepository.getReferenceById(id);
        return sp;
    }
}
