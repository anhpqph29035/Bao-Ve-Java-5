package com.example.assignment.Services.impl;

import com.example.assignment.Model.SanPham;
import com.example.assignment.Repository.SanPhamRepository;
import com.example.assignment.Services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public void deleteSP(UUID id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public void addSP(SanPham sp) {
        sanPhamRepository.save(sp);
    }

    @Override
    public void updateSP(UUID id, SanPham sp) {
        sanPhamRepository.save(sp);
    }

    @Override
    public SanPham detail(UUID id) {
        SanPham sp = sanPhamRepository.getReferenceById(id);
        return sp;
    }
}
