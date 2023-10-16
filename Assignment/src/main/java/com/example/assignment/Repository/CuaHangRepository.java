package com.example.assignment.Repository;

import com.example.assignment.Model.CuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CuaHangRepository extends JpaRepository<CuaHang, UUID> {
    @Query(value = "select * from cua_hang", nativeQuery = true)
    List<CuaHang> getAll();
}
