package com.example.assignment.Repository;

import com.example.assignment.Model.DongSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DongSPRepository extends JpaRepository<DongSP, UUID> {
    @Query(value = "select * from dong_sp", nativeQuery = true)
    List<DongSP> getAll();
}
