package com.example.assignment.Controller;

import com.example.assignment.Model.DongSP;
import com.example.assignment.Model.NSX;
import com.example.assignment.Repository.DongSPRepository;
import com.example.assignment.Repository.NSXRepository;
import com.example.assignment.Services.DongSPService;
import com.example.assignment.Services.NSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class NSXController {

    @Autowired
    NSXService nsxService;
    @Autowired
    NSXRepository nsxRepository;

    @GetMapping("/nsx")
    public String show(Model m) {
        List<NSX> list = nsxRepository.getAll();
        m.addAttribute("listPR", list);
        return "producer/nsx";
    }

    @GetMapping("/showaddpr")
    public String detail(
            Model m,
            @ModelAttribute("pr") NSX sp) {
        m.addAttribute("pr",sp);
        return "producer/add";
    }

    @GetMapping("/deletepr/{id}")
    public String delete(Model m, @PathVariable String id) {
        nsxService.deleteNSX(UUID.fromString(id));
        List<NSX> list = nsxRepository.getAll();
        m.addAttribute("listPR", list);
        m.addAttribute("tb","Xóa thành công !");
        return "producer/nsx";
    }

    @GetMapping("/detailpr/{id}")
    public String detail(Model m, @PathVariable String id) {
        NSX n = nsxService.detail(UUID.fromString(id));
        m.addAttribute("prr", n);
        return "producer/detail";
    }

    @PostMapping("/addpr")
    public String add(
            Model m,
            @ModelAttribute("pr") NSX sp) {
        int check = 0;
        if(sp.getTen().trim().isEmpty()){
            m.addAttribute("loiTPR","Không để trống tên !");
            check =1 ;
        }
        if(sp.getMa().trim().isEmpty()){
            m.addAttribute("loiMPR","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("pr",sp);
            return "producer/add";
        }
        nsxService.addNSX(sp);
        List<NSX> list = nsxRepository.getAll();
        m.addAttribute("listPR", list);
        m.addAttribute("tb","Thêm thành công !");
        return "producer/nsx";
    }

    @PostMapping("/updatepr/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("prr") NSX ch) {
        int check = 0;
        if(ch.getTen().trim().isEmpty()){
            m.addAttribute("loiTPR","Không để trống tên!");
            check =1 ;
        }
        if(ch.getMa().trim().isEmpty()){
            m.addAttribute("loiMPR","Không để trống mã !");
            check =1 ;
        }
        if(check==1){
            m.addAttribute("prr",ch);
            return "producer/detail";
        }
        nsxService.updateNSX(UUID.fromString(id), ch);
        List<NSX> list = nsxRepository.getAll();
        m.addAttribute("listPR", list);
        m.addAttribute("tb","Sửa thành công !");
        return "producer/nsx";
    }
}
