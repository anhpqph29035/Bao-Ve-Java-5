package com.example.assignment.Services.impl;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Repository.ChucVuRepository;
import com.example.assignment.Services.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    ChucVuRepository chucVuRepository;

    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public void deleteCV(UUID id) {
        chucVuRepository.deleteById(id);
    }

    @Override
    public void addCV(ChucVu ms) {
        chucVuRepository.save(ms);
    }

    @Override
    public void updateCV(UUID id, ChucVu ms) {
        chucVuRepository.save(ms);
    }

    @Override
    public ChucVu detail(UUID id) {
         ChucVu ms = chucVuRepository.getReferenceById(id);
        return ms;
    }
}
