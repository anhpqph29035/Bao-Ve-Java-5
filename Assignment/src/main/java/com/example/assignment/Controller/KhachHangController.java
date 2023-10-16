package com.example.assignment.Controller;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Model.KhachHang;
import com.example.assignment.Repository.ChucVuRepository;
import com.example.assignment.Repository.KhachHangRepository;
import com.example.assignment.Services.ChucVuService;
import com.example.assignment.Services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class KhachHangController {

    @Autowired
    KhachHangService khachHangService;
    @Autowired
    KhachHangRepository khachHangRepository;

    @GetMapping("/khachhang")
    public String show(Model m) {
        List<KhachHang> list = khachHangRepository.getAll();
        m.addAttribute("listKH", list);
        return "customer/khachhang";
    }

    @GetMapping("/showaddkh")
    public String detail(
            Model m,
            @ModelAttribute("") KhachHang sp) {
        m.addAttribute("kh",sp);
       return "customer/add";
    }

    @GetMapping("/deletekh/{id}")
    public String delete(Model m, @PathVariable String id) {
        khachHangService.deleteKH(UUID.fromString(id));
        List<KhachHang> list = khachHangRepository.getAll();
        m.addAttribute("listKH", list);
        m.addAttribute("tb","Xóa thành công !");
        return "customer/khachhang";
    }

    @GetMapping("/detailkh/{id}")
    public String detail(Model m, @PathVariable String id) {
        KhachHang n = khachHangService.detail(UUID.fromString(id));
        m.addAttribute("khh", n);
        return "customer/detail";
    }

    @PostMapping("/addkh")
    public String add(
            Model m,
            @ModelAttribute("kh") KhachHang sp) {
        khachHangService.addKH(sp);
        List<KhachHang> list = khachHangRepository.getAll();
        m.addAttribute("listKH", list);
        m.addAttribute("tb","Thêm thành công !");
        return "customer/khachhang";
    }

    @PostMapping("/updatekh/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("khh") KhachHang ch) {
        khachHangService.updateKH(UUID.fromString(id), ch);
        List<KhachHang> list = khachHangRepository.getAll();
        m.addAttribute("listKH", list);
        m.addAttribute("tb","Sửa thành công !");
        return "customer/khachhang";
    }
}
