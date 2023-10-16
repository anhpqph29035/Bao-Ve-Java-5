package com.example.assignment.Services;

import com.example.assignment.Model.NSX;

import java.util.List;
import java.util.UUID;


public interface NSXService {
    List<NSX> getAll();
    void deleteNSX(UUID id);
    void addNSX(NSX sp);
    void updateNSX(UUID id,NSX nsx);
    NSX detail(UUID id);
}
