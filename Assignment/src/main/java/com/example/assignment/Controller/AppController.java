package com.example.assignment.Controller;

import com.example.assignment.Model.NhanVien;
import com.example.assignment.Repository.NhanVienRespository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class AppController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    NhanVienRespository nhanVienRespository;
    @Autowired
    HttpServletResponse response;

    @GetMapping("/home")
    public String hienThi(Model m) {
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("idNV")) {
                    value = cookie.getValue();
                }
            }
        }
        NhanVien nv = nhanVienRespository.findNhanVienById(UUID.fromString(value));
        m.addAttribute("tenHien", nv.getTenDem()+" "+setCC());
        return "manager/home";
    }

    @GetMapping("/login")
    public String hie(Model m) {
        return "login/login";
    }

    @GetMapping("/logout")
    public String hief(Model m) {
        Cookie ck = new Cookie("idNV", "");
        Cookie ck1 = new Cookie("tenNV", "");
        ck.setMaxAge(0);
        ck1.setMaxAge(0);
        response.addCookie(ck);
        response.addCookie(ck1);
        return "login/login";
    }

    public String setCC() {
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("tenNV")) {
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }
}
