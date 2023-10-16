package com.example.assignment.Services;


import com.example.assignment.Model.DongSP;
import com.example.assignment.Model.MauSac;

import java.util.List;
import java.util.UUID;

public interface DongSPService {
    List<DongSP> getAll();
    void deleteDSP(UUID id);
    void addDSP(DongSP ms);
    void updateDSP(UUID id,DongSP ms);
    DongSP detail(UUID id);
}
