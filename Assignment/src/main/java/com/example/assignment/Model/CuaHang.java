package com.example.assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "cua_hang")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CuaHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String ma;

    private String ten;

    private String diaChi;

    private String thanhPho;

    private String quocGia;
}
