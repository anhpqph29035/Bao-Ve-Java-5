package com.example.assignment.Controller;

import com.example.assignment.Model.NhanVien;
import com.example.assignment.Repository.NhanVienRespository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/cua-hang")
public class LoginController {
   @Autowired
    NhanVienRespository nhanVienRespository;
   @Autowired
   HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

   @PostMapping("/dangnhap")
    public String login(Model m){
       String ma = request.getParameter("maNV");
       String pass = request.getParameter("passNV");
       NhanVien nv = nhanVienRespository.findNhanVienByMaAndMatKhau(ma,pass);
       if (nv==null) {
           m.addAttribute("tb","Sai tên đăng nhập hoặc mật khẩu !");
          return "login/login";
       } else {
           request.setAttribute("NV", nv.getTen());
           request.setAttribute("logout", "Logout");
           Cookie idNV = new Cookie("idNV", nv.getId().toString());
           Cookie tenNV = new Cookie("tenNV", nv.getTen());
           idNV.setMaxAge(10 * 60 * 60);
           tenNV.setMaxAge(10 * 60 * 60);
           response.addCookie(idNV);
           response.addCookie(tenNV);
           m.addAttribute("tenHien", setCC());
           return "/manager/home";
       }
    }

    @GetMapping("/dangxuat")
    public String dx(Model m){
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
