package com.example.assignment.Repository;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.HoaDon;
import com.example.assignment.Model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
//    @Query(value = "select * from hoa_don", nativeQuery = true)
//    List<ChucVu> getAll();

    List<HoaDon> findByNv_TenAndTinhTrang(String ten,int tt);
    List<HoaDon> findByNvAndTinhTrang(NhanVien nv, int tt);
    HoaDon findHoaDonById(UUID id);
    List<HoaDon> findHoaDonsByNvAndTinhTrangAndNgayThanhToan(NhanVien nv, int tt, Date d);
    List<HoaDon> findHoaDonsByNvAndAndNgayThanhToanBetween(NhanVien nv,Date d,Date z);
    List<HoaDon> findHoaDonsByNvAndTinhTrangAndKh_TenOrNv_TenOrMa(NhanVien nv,int tt,String ma1,String ma2,String ma3);
}
