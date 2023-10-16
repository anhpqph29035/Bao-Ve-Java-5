package com.example.assignment.Controller;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.NhanVien;
import com.example.assignment.Repository.ChucVuRepository;
import com.example.assignment.Repository.CuaHangRepository;
import com.example.assignment.Repository.NhanVienRespository;
import com.example.assignment.Services.ChucVuService;
import com.example.assignment.Services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class NhanVienController {

    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    NhanVienRespository nhanVienRespository;
    @Autowired
    CuaHangRepository cuaHangRepository;
    @Autowired
    ChucVuRepository chucVuRepository;

    @GetMapping("/nhanvien")
    public String show(Model m) {
        List<NhanVien> list = nhanVienRespository.getAll();
        m.addAttribute("listNV", list);
        return "staff/nhanvien";
    }

    @GetMapping("/showaddnv")
    public String detail(
            Model m,
            @ModelAttribute("nv") NhanVien sp) {
        m.addAttribute("nv",sp);
        m.addAttribute("listCH",cuaHangRepository.getAll());
        m.addAttribute("listCV",chucVuRepository.getAll());
        return "staff/add";
    }

    @GetMapping("/deletenv/{id}")
    public String delete(Model m, @PathVariable String id) {
        nhanVienService.deleteNV(UUID.fromString(id));
        List<NhanVien> list = nhanVienRespository.getAll();
        m.addAttribute("listNV", list);
        m.addAttribute("tb","Xóa thành công !");
        return "staff/nhanvien";
    }

    @GetMapping("/detailnv/{id}")
    public String detail(Model m, @PathVariable String id) {
        NhanVien n = nhanVienService.detail(UUID.fromString(id));
        m.addAttribute("nvv", n);
        m.addAttribute("listCH",cuaHangRepository.getAll());
        m.addAttribute("listCV",chucVuRepository.getAll());
        return "staff/detail";
    }

    @PostMapping("/addnv")
    public String add(
            Model m,
            @ModelAttribute("nv") NhanVien sp) {
        nhanVienService.addNV(sp);
        List<NhanVien> list = nhanVienRespository.getAll();
        m.addAttribute("listNV", list);
        m.addAttribute("tb","Thêm thành công !");
        return "staff/nhanvien";
    }

    @PostMapping("/updatenv/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("nvv") NhanVien nv) {
        nhanVienService.updateNV(UUID.fromString(id), nv);
        List<NhanVien> list = nhanVienRespository.getAll();
        m.addAttribute("listNV", list);
        m.addAttribute("tb","Sửa thành công !");
        return "staff/nhanvien";
    }
}
