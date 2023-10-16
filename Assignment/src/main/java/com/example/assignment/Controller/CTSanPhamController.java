package com.example.assignment.Controller;

import com.example.assignment.Model.ChiTietSanPham;
import com.example.assignment.Repository.*;
import com.example.assignment.Services.CTSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class CTSanPhamController {

    @Autowired
    CTSanPhamService ctSanPhamService;
    @Autowired
    CTSanPhamRepository ctSanPhamRepository;
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    NSXRepository nsxRepository;
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    DongSPRepository dongSPRepository;

    @GetMapping("/ctsanpham")
    public String showSanPham(Model m) {
        List<ChiTietSanPham> list = ctSanPhamRepository.getAll();
        m.addAttribute("listCTSP", list);
        return "productdt/ctsanpham";
    }

    @GetMapping("/showaddctsp")
    public String detail(
            Model m,
            @ModelAttribute("ct") ChiTietSanPham sp) {
        m.addAttribute("ct",sp);
        m.addAttribute("listSP", sanPhamRepository.getAll());
        m.addAttribute("listSX", nsxRepository.getAll());
        m.addAttribute("listMS", mauSacRepository.getAll());
        m.addAttribute("listDSP", dongSPRepository.getAll());
        return "productdt/add";
    }

    @GetMapping("/deletectsp/{id}")
    public String delete(Model m, @PathVariable String id) {
        ctSanPhamService.deleteCTSP(UUID.fromString(id));
        List<ChiTietSanPham> list = ctSanPhamRepository.getAll();
        m.addAttribute("listCTSP", list);
        m.addAttribute("tb","Xóa thành công !");
        return "productdt/ctsanpham";
    }

    @GetMapping("/detailctsp/{id}")
    public String detail(Model m, @PathVariable String id) {
        ChiTietSanPham n = ctSanPhamService.detail(UUID.fromString(id));
        m.addAttribute("ctt", n);
        m.addAttribute("listSP", sanPhamRepository.getAll());
        m.addAttribute("listSX", nsxRepository.getAll());
        m.addAttribute("listMS", mauSacRepository.getAll());
        m.addAttribute("listDSP", dongSPRepository.getAll());
        return "productdt/detail";
    }

    @PostMapping("/addctsp")
    public String add(
            Model m,
            @ModelAttribute("ct") ChiTietSanPham sp) {
        int check = 0;
        if(sp.getAnh().trim().isEmpty()){
            m.addAttribute("loiCTA","Không để trống ảnh !");
            check =1 ;
        }
        if(sp.getNamBH()<=0){
            m.addAttribute("loiCTBH","Không để trống năm bảo hành (>0) !");
            check =1 ;
        }
        if(sp.getMoTa().trim().isEmpty()){
            m.addAttribute("loiCTMT","Không để trống mô tả !");
            check =1 ;
        }
        if(sp.getSoLuongTon()<=0){
            m.addAttribute("loiCTSL","Số lượng tồn lớn hơn 0 !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("ct",sp);
            m.addAttribute("listSP", sanPhamRepository.getAll());
            m.addAttribute("listSX", nsxRepository.getAll());
            m.addAttribute("listMS", mauSacRepository.getAll());
            m.addAttribute("listDSP", dongSPRepository.getAll());
            return "productdt/add";
        }
        ctSanPhamService.addCTSP(sp);
        List<ChiTietSanPham> list = ctSanPhamRepository.getAll();
        m.addAttribute("listCTSP", list);
        m.addAttribute("tb","Thêm thành công !");
        return "productdt/ctsanpham";
    }

    @PostMapping("/updatectsp/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("ctt") ChiTietSanPham sp) {
        int check = 0;
        if(sp.getAnh().trim().isEmpty()){
            m.addAttribute("loiCTA","Không để trống ảnh !");
            check =1 ;
        }
        if(sp.getNamBH()<=0){
            m.addAttribute("loiCTBH","Không để trống năm bảo hành (>0) !");
            check =1 ;
        }
        if(sp.getMoTa().trim().isEmpty()){
            m.addAttribute("loiCTMT","Không để trống mô tả !");
            check =1 ;
        }
        if(sp.getSoLuongTon()<=0){
            m.addAttribute("loiCTSL","Số lượng tồn lớn hơn 0 !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("ctt",sp);
            m.addAttribute("listSP", sanPhamRepository.getAll());
            m.addAttribute("listSX", nsxRepository.getAll());
            m.addAttribute("listMS", mauSacRepository.getAll());
            m.addAttribute("listDSP", dongSPRepository.getAll());
            return "productdt/detail";
        }
        ctSanPhamService.updateCTSP(UUID.fromString(id), sp);
        List<ChiTietSanPham> list = ctSanPhamRepository.getAll();
        m.addAttribute("listCTSP", list);
        m.addAttribute("tb","Sửa thành công !");
        return "productdt/ctsanpham";
    }
}
