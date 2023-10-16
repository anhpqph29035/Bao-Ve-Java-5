package com.example.assignment.Services.impl;

import com.example.assignment.Model.NSX;
import com.example.assignment.Model.SanPham;
import com.example.assignment.Repository.NSXRepository;
import com.example.assignment.Repository.SanPhamRepository;
import com.example.assignment.Services.NSXService;
import com.example.assignment.Services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NSXServiceImpl implements NSXService {

    @Autowired
    NSXRepository nsxRepository;

    @Override
    public List<NSX> getAll() {
        return nsxRepository.findAll();
    }

    @Override
    public void deleteNSX(UUID id) {
        nsxRepository.deleteById(id);
    }

    @Override
    public void addNSX(NSX nsx) {
        nsxRepository.save(nsx);
    }

    @Override
    public void updateNSX(UUID id, NSX sp) {
        nsxRepository.save(sp);
    }

    @Override
    public NSX detail(UUID id) {
        NSX sp = nsxRepository.getReferenceById(id);
        return sp;
    }
}
