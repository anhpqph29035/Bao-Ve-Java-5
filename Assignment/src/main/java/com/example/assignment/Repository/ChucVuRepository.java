package com.example.assignment.Repository;

import com.example.assignment.Model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
    @Query(value = "select * from chuc_vu", nativeQuery = true)
    List<ChucVu> getAll();
}
