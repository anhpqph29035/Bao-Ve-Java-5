package com.example.assignment.Controller;

import com.example.assignment.Model.SanPham;
import com.example.assignment.Repository.SanPhamRepository;
import com.example.assignment.Services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class SanPhamController {

    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    SanPhamRepository sanPhamRepository;

    @GetMapping("/sanpham")
    public String showSanPham(Model m) {
        List<SanPham> list = sanPhamRepository.getAll();
        m.addAttribute("listSP", list);
        return "product/sanpham";
    }

    @GetMapping("/showaddsanpham")
    public String detail(
            Model m,
            @ModelAttribute("sp") SanPham sp) {
        m.addAttribute("sp",sp);
        return "product/add";
    }

    @GetMapping("/deletesp/{id}")
    public String delete(Model m, @PathVariable String id) {
        sanPhamService.deleteSP(UUID.fromString(id));
        List<SanPham> list = sanPhamRepository.getAll();
        m.addAttribute("listSP", list);
        m.addAttribute("tb","Xóa thành công !");
        return "product/sanpham";
    }

    @GetMapping("/detailsp/{id}")
    public String detail(Model m, @PathVariable String id) {
        SanPham n = sanPhamService.detail(UUID.fromString(id));
        m.addAttribute("chh", n);
        return "product/detail";
    }

    @PostMapping("/addsp")
    public String add(
            Model m,
            @ModelAttribute("sp") SanPham sp) {
        int check = 0;
        if(sp.getTen().trim().isEmpty()){
            m.addAttribute("loiTSP","Không để trống tên !");
            check =1 ;
        }
        if(sp.getMa().trim().isEmpty()){
            m.addAttribute("loiMSP","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("sp",sp);
            return "product/add";
        }
        sanPhamService.addSP(sp);
        List<SanPham> list = sanPhamRepository.getAll();
        m.addAttribute("listSP", list);
        m.addAttribute("tb","Thêm thành công !");
        return "product/sanpham";
    }

    @PostMapping("/updatesp/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("chh") SanPham ch) {
        int check = 0;
        if(ch.getTen().trim().isEmpty()){
            m.addAttribute("loiTSP","Không để trống tên!");
            check =1 ;
        }
        if(ch.getMa().trim().isEmpty()){
            m.addAttribute("loiMSP","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("chh",ch);
            return "product/detail";
        }
        sanPhamService.updateSP(UUID.fromString(id), ch);
        List<SanPham> list = sanPhamRepository.getAll();
        m.addAttribute("listSP", list);
        m.addAttribute("tb","Sửa thành công !");
        return "product/sanpham";
    }
}
