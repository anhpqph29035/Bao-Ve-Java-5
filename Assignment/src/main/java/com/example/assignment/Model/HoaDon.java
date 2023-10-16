package com.example.assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Table(name = "hoa_don")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_kh")
    private KhachHang kh;

    @ManyToOne
    @JoinColumn(name = "id_nv")
    private NhanVien nv;

    private String ma;

    private Date ngayTao;

    private Date ngayThanhToan;

    private Date ngayShip;

    private Date ngayNhan;

    private int tinhTrang;

    private String tenNguoiNhan;

    private String diaChi;

    private String sdt;

    private BigDecimal tongTien;
}
