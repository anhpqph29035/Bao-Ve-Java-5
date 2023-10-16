package com.example.assignment.Repository;

import com.example.assignment.Model.NSX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NSXRepository extends JpaRepository<NSX, UUID> {
    @Query(value = "select * from nxs", nativeQuery = true)
    List<NSX> getAll();
}
