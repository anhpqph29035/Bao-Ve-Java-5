package com.example.assignment.Controller;

import com.example.assignment.Model.CuaHang;
import com.example.assignment.Repository.CuaHangRepository;
import com.example.assignment.Services.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class CuaHangController {

    @Autowired
    CuaHangService cuaHangService;
    @Autowired
    CuaHangRepository cuaHangRepository;

    @GetMapping("/cuahang")
    public String show(Model m) {
        List<CuaHang> list = cuaHangRepository.getAll();
        m.addAttribute("listCH", list);
        return "shop/cuahang";
    }

    @GetMapping("/showaddch")
    public String detail(
            Model m,
            @ModelAttribute("") CuaHang sp) {
        m.addAttribute("ch",sp);
        return "shop/add";
    }

    @GetMapping("/deletech/{id}")
    public String delete(Model m, @PathVariable String id) {
        cuaHangService.deleteCH(UUID.fromString(id));
        List<CuaHang> list = cuaHangRepository.getAll();
        m.addAttribute("listCH", list);
        m.addAttribute("tb","Xóa thành công !");
        return "shop/cuahang";
    }

    @GetMapping("/detailch/{id}")
    public String detail(Model m, @PathVariable String id) {
        CuaHang n = cuaHangService.detail(UUID.fromString(id));
        m.addAttribute("cch", n);
        return "shop/detail";
    }

    @PostMapping("/addch")
    public String add(
            Model m,
            @ModelAttribute("ch") CuaHang sp) {
        int check = 0;
        if(sp.getTen().trim().isEmpty()){
            m.addAttribute("loiTCH","Không để trống tên !");
            check =1 ;
        }
        if(sp.getMa().trim().isEmpty()){
            m.addAttribute("loiMCH","Không để trống mã !");
            check =1 ;
        }
        if(sp.getDiaChi().trim().isEmpty()){
            m.addAttribute("loiDCCH","Không để trống địa chỉ !");
            check =1 ;
        }
        if(sp.getThanhPho().trim().isEmpty()){
            m.addAttribute("loiTPCH","Không để trống thành phố !");
            check =1 ;
        }
        if(sp.getQuocGia().trim().isEmpty()){
            m.addAttribute("loiQGCH","Không để trống quốc gia !");
            check =1 ;
        }

        if(check==1){
            m.addAttribute("ch",sp);
            return "shop/add";
        }
        cuaHangService.addCH(sp);
        List<CuaHang> list = cuaHangRepository.getAll();
        m.addAttribute("listCH", list);
        m.addAttribute("tb","Thêm thành công !");
        return "shop/cuahang";
    }

    @PostMapping("/updatech/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("cch") CuaHang sp) {
        int check = 0;
        if(sp.getTen().trim().isEmpty()){
            m.addAttribute("loiTCH","Không để trống tên !");
            check =1 ;
        }
        if(sp.getMa().trim().isEmpty()){
            m.addAttribute("loiMCH","Không để trống mã !");
            check =1 ;
        }
        if(sp.getDiaChi().trim().isEmpty()){
            m.addAttribute("loiDCCH","Không để trống địa chỉ !");
            check =1 ;
        }
        if(sp.getThanhPho().trim().isEmpty()){
            m.addAttribute("loiTPCH","Không để trống thành phố !");
            check =1 ;
        }
        if(sp.getQuocGia().trim().isEmpty()){
            m.addAttribute("loiQGCH","Không để trống quốc gia !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("cch",sp);
            return "shop/detail";
        }
        cuaHangService.updateCH(UUID.fromString(id), sp);
        List<CuaHang> list = cuaHangRepository.getAll();
        m.addAttribute("listCH", list);
        m.addAttribute("tb","Sửa thành công !");
        return "shop/cuahang";
    }
}
