package com.example.assignment.Services.impl;

import com.example.assignment.Model.MauSac;
import com.example.assignment.Repository.MauSacRepository;
import com.example.assignment.Services.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public void deleteMS(UUID id) {
        mauSacRepository.deleteById(id);
    }

    @Override
    public void addMS(MauSac ms) {
        mauSacRepository.save(ms);
    }

    @Override
    public void updateMS(UUID id, MauSac ms) {
        mauSacRepository.save(ms);
    }

    @Override
    public MauSac detail(UUID id) {
         MauSac ms = mauSacRepository.getReferenceById(id);
        return ms;
    }
}
