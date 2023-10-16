package com.example.assignment.Services;

import com.example.assignment.Model.CuaHang;

import java.util.List;
import java.util.UUID;

public interface CuaHangService {
    List<CuaHang> getAll();
    void deleteCH(UUID id);
    void addCH(CuaHang ms);
    void updateCH(UUID id,CuaHang ms);
    CuaHang detail(UUID id);
}
