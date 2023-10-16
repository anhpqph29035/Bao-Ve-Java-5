package com.example.assignment.Repository;

import com.example.assignment.Model.HoaDonChiTiet;
import com.example.assignment.Model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface HDCTRes extends JpaRepository<HoaDonChiTiet, UUID> {
    HoaDonChiTiet findHoaDonChiTietById_IdHoaDon_IdAndId_IdChiTietSp_Id(UUID idHD, UUID idCtsp);
    List<HoaDonChiTiet> findHoaDonChiTietsById_IdHoaDon_Id(UUID idHD);
    @Query(value = "SELECT SUM(o.don_gia) FROM hoa_don_chi_tiet o JOIN hoa_don on o.id_hoa_don=hoa_don.id WHERE hoa_don.id=:id", nativeQuery = true)
    BigDecimal getTongTien(UUID id);
}
