package com.example.assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "chi_tiet_sp")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "id_nsx")
    private NSX sx;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac ms;

    @ManyToOne
    @JoinColumn(name = "id_dong_sp")
    private DongSP dsp;

    @Column(name = "hinh_anh")
    private String anh;

    @Column(name = "nam_bh")
    private int namBH;

    private String moTa;

    private int soLuongTon;

    private BigDecimal giaNhap;

    private BigDecimal giaBan;
}
