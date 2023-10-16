package com.example.assignment.Controller;

import com.example.assignment.Model.MauSac;
import com.example.assignment.Repository.MauSacRepository;
import com.example.assignment.Services.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;
    @Autowired
    MauSacRepository mauSacRepository;

    @GetMapping("/mausac")
    public String showSanPham(Model m) {
        List<MauSac> list = mauSacRepository.getAll();
        m.addAttribute("listMS", list);
        return "color/mausac";
    }

    @GetMapping("/showaddmausac")
    public String detail(
            Model m,
            @ModelAttribute("ms") MauSac ms) {
        m.addAttribute("ms", ms);
        return "color/add";
    }

    @GetMapping("/deletems/{id}")
    public String delete(Model m, @PathVariable String id) {
        mauSacService.deleteMS(UUID.fromString(id));
        List<MauSac> list = mauSacService.getAll();
        m.addAttribute("listMS", list);
        m.addAttribute("tb", "Xóa thành công !");
        return "color/mausac";
    }

    @GetMapping("/detailms/{id}")
    public String detail(Model m, @PathVariable String id) {
        MauSac n = mauSacService.detail(UUID.fromString(id));
        m.addAttribute("mss", n);
        return "color/detail";
    }

    @PostMapping("/addms")
    public String add(
            Model m,
            @ModelAttribute("ms") MauSac ms) {
        int check = 0;
        if (ms.getTen().trim().isEmpty()) {
            m.addAttribute("loiTMS", "Không để trống tên !");
            check = 1;
        }
        if (ms.getMa().trim().isEmpty()) {
            m.addAttribute("loiMMS", "Không để trống mã !");
            check = 1;
        }
        if (check == 1) {
            m.addAttribute("ms", ms);
            return "color/add";
        }
        mauSacService.addMS(ms);
        List<MauSac> list = mauSacService.getAll();
        m.addAttribute("listMS", list);
        m.addAttribute("tb", "Thêm thành công !");
        return "color/mausac";
    }

    @PostMapping("/updatems/{id}")
    public String update(
            Model m,
            @PathVariable String id,
            @ModelAttribute("mss") MauSac ms) {
        int check = 0;
        if (ms.getTen().trim().isEmpty()) {
            m.addAttribute("loiTMS", "Không để trống tên !");
            check = 1;
        }
        if (ms.getMa().trim().isEmpty()) {
            m.addAttribute("loiMMS", "Không để trống mã !");
            check = 1;
        }
        if (check == 1) {
            m.addAttribute("ms", ms);
            return "color/detail";
        }
        mauSacService.updateMS(UUID.fromString(id), ms);
        List<MauSac> list = mauSacService.getAll();
        m.addAttribute("listMS", list);
        m.addAttribute("tb", "Sửa thành công !");
        return "color/mausac";
    }

}
