package com.example.assignment.Services;


import com.example.assignment.Model.MauSac;

import java.util.List;
import java.util.UUID;

public interface MauSacService {
    List<MauSac> getAll();
    void deleteMS(UUID id);
    void addMS(MauSac ms);
    void updateMS(UUID id,MauSac ms);
    MauSac detail(UUID id);
}
