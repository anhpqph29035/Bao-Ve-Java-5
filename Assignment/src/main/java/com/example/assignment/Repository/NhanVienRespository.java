package com.example.assignment.Repository;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NhanVienRespository extends JpaRepository<NhanVien, UUID> {
    @Query(value = "select * from nhan_vien", nativeQuery = true)
    List<NhanVien> getAll();

    NhanVien findNhanVienByMaAndMatKhau(String ma,String pass);
    NhanVien findNhanVienById(UUID id);
}
