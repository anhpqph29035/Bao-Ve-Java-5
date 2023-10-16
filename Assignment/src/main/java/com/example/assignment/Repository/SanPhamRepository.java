package com.example.assignment.Repository;

import com.example.assignment.Model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query(value = "select * from san_pham", nativeQuery = true)
    List<SanPham> getAll();
}
