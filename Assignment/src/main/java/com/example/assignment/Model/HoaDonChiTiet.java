package com.example.assignment.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "hoa_don_chi_tiet")
@Data

public class HoaDonChiTiet{

    @EmbeddedId
    private IdHoaDonChiTiet id;

    private Integer soLuong;

    private BigDecimal donGia;
}
