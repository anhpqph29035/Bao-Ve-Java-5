package com.example.assignment.Controller;

import com.example.assignment.Model.DongSP;
import com.example.assignment.Model.SanPham;
import com.example.assignment.Repository.DongSPRepository;
import com.example.assignment.Repository.SanPhamRepository;
import com.example.assignment.Services.DongSPService;
import com.example.assignment.Services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class DongSPController {

    @Autowired
    DongSPService dongSPService;
    @Autowired
    DongSPRepository dongSPRepository;

    @GetMapping("/dongsanpham")
    public String show(Model m) {
        List<DongSP> list = dongSPRepository.getAll();
        m.addAttribute("listDSP", list);
        return "productline/dongsp";
    }

    @GetMapping("/showadddsp")
    public String detail(
            Model m,
            @ModelAttribute("dsp") DongSP sp) {
        m.addAttribute("dsp",sp);
        return "productline/add";
    }

    @GetMapping("/deletedsp/{id}")
    public String delete(Model m, @PathVariable String id) {
        dongSPService.deleteDSP(UUID.fromString(id));
        List<DongSP> list = dongSPRepository.getAll();
        m.addAttribute("listDSP", list);
        m.addAttribute("tb","Xóa thành công !");
        return "productline/dongsp";
    }

    @GetMapping("/detaildsp/{id}")
    public String detail(Model m, @PathVariable String id) {
        DongSP n = dongSPService.detail(UUID.fromString(id));
        m.addAttribute("dspp", n);
        return "productline/detail";
    }

    @PostMapping("/adddsp")
    public String add(
            Model m,
            @ModelAttribute("dsp") DongSP sp) {
        int check = 0;
        if(sp.getTen().trim().isEmpty()){
            m.addAttribute("loiTDSP","Không để trống tên !");
            check =1 ;
        }
        if(sp.getMa().trim().isEmpty()){
            m.addAttribute("loiMDSP","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("dsp",sp);
            return "productline/add";
        }
        dongSPService.addDSP(sp);
        List<DongSP> list = dongSPRepository.getAll();
        m.addAttribute("listDSP", list);
        m.addAttribute("tb","Thêm thành công !");
        return "productline/dongsp";
    }

    @PostMapping("/updatedsp/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("dspp") DongSP ch) {
        int check = 0;
        if(ch.getTen().trim().isEmpty()){
            m.addAttribute("loiTDSP","Không để trống tên!");
            check =1 ;
        }
        if(ch.getMa().trim().isEmpty()){
            m.addAttribute("loiMDSP","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("dspp",ch);
            return "productline/detail";
        }
        dongSPService.updateDSP(UUID.fromString(id), ch);
        List<DongSP> list = dongSPRepository.getAll();
        m.addAttribute("listDSP", list);
        m.addAttribute("tb","Sửa thành công !");
        return "productline/dongsp";
    }
}
