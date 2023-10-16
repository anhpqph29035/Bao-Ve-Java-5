package com.example.assignment.Services;

import com.example.assignment.Model.ChucVu;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {
    List<ChucVu> getAll();
    void deleteCV(UUID id);
    void addCV(ChucVu ms);
    void updateCV(UUID id,ChucVu ms);
    ChucVu detail(UUID id);
}
