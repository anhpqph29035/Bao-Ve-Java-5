package com.example.assignment.Controller;

import com.example.assignment.Model.ChucVu;
import com.example.assignment.Repository.ChucVuRepository;
import com.example.assignment.Services.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class ChucVuController {

    @Autowired
    ChucVuService chucVuService;
    @Autowired
    ChucVuRepository chucVuRepository;

    @GetMapping("/chucvu")
    public String show(Model m) {
        List<ChucVu> list = chucVuRepository.getAll();
        m.addAttribute("listCV", list);
        return "position/chucvu";
    }

    @GetMapping("/showaddcv")
    public String detail(
            Model m,
            @ModelAttribute("") ChucVu sp) {
        m.addAttribute("cv",sp);
        return "position/add";
    }

    @GetMapping("/deletecv/{id}")
    public String delete(Model m, @PathVariable String id) {
        chucVuService.deleteCV(UUID.fromString(id));
        List<ChucVu> list = chucVuRepository.getAll();
        m.addAttribute("listCV", list);
        m.addAttribute("tb","Xóa thành công !");
        return "position/chucvu";
    }

    @GetMapping("/detailcv/{id}")
    public String detail(Model m, @PathVariable String id) {
        ChucVu n = chucVuService.detail(UUID.fromString(id));
        m.addAttribute("cvv", n);
        return "position/detail";
    }

    @PostMapping("/addcv")
    public String add(
            Model m,
            @ModelAttribute("cv") ChucVu sp) {
        int check = 0;
        if(sp.getTen().trim().isEmpty()){
            m.addAttribute("loiTCV","Không để trống tên !");
            check =1 ;
        }
        if(sp.getMa().trim().isEmpty()){
            m.addAttribute("loiMCV","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("cv",sp);
            return "position/add";
        }
        chucVuService.addCV(sp);
        List<ChucVu> list = chucVuRepository.getAll();
        m.addAttribute("listCV", list);
        m.addAttribute("tb","Thêm thành công !");
        return "position/chucvu";
    }

    @PostMapping("/updatecv/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("cvv") ChucVu ch) {
        int check = 0;
        if(ch.getTen().trim().isEmpty()){
            m.addAttribute("loiTCV","Không để trống tên!");
            check =1 ;
        }
        if(ch.getMa().trim().isEmpty()){
            m.addAttribute("loiMCV","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("cvv",ch);
            return "position/detail";
        }
        chucVuService.updateCV(UUID.fromString(id), ch);
        List<ChucVu> list = chucVuRepository.getAll();
        m.addAttribute("listCV", list);
        m.addAttribute("tb","Sửa thành công !");
        return "position/chucvu";
    }
}
