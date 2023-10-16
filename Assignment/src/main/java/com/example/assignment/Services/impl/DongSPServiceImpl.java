package com.example.assignment.Services.impl;

import com.example.assignment.Model.DongSP;
import com.example.assignment.Model.MauSac;
import com.example.assignment.Repository.DongSPRepository;
import com.example.assignment.Repository.MauSacRepository;
import com.example.assignment.Services.DongSPService;
import com.example.assignment.Services.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DongSPServiceImpl implements DongSPService {
    @Autowired
    DongSPRepository dongSPRepository;

    @Override
    public List<DongSP> getAll() {
        return dongSPRepository.findAll();
    }

    @Override
    public void deleteDSP(UUID id) {
        dongSPRepository.deleteById(id);
    }

    @Override
    public void addDSP(DongSP dsp) {
        dongSPRepository.save(dsp);
    }

    @Override
    public void updateDSP(UUID id, DongSP dsp) {
        dongSPRepository.save(dsp);
    }

    @Override
    public DongSP detail(UUID id) {
         DongSP dsp = dongSPRepository.getReferenceById(id);
        return dsp;
    }
}
