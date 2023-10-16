package com.example.assignment.Controller;

import com.example.assignment.Model.HoaDon;
import com.example.assignment.Model.HoaDonChiTiet;
import com.example.assignment.Model.NhanVien;
import com.example.assignment.Repository.HDCTRes;
import com.example.assignment.Repository.HoaDonRepository;
import com.example.assignment.Repository.KhachHangRepository;
import com.example.assignment.Repository.NhanVienRespository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class HoaDonController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    HDCTRes hdctRes;
    @Autowired
    NhanVienRespository nhanVienRespository;
    @Autowired
    HttpServletRequest request;
    @Autowired
    KhachHangRepository khachHangRepository;

    @GetMapping("/hoadon")
    public String show(Model m) {
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
        m.addAttribute("tenNV",nv.getHo()+" "+nv.getTenDem()+" "+nv.getTen());
        LocalDate localDate = LocalDate.now();
        List<HoaDon> list = hoaDonRepository.findHoaDonsByNvAndTinhTrangAndNgayThanhToan(nv,1, Date.valueOf(localDate));
        m.addAttribute("soHD",list.size());
        m.addAttribute("soKH",khachHangRepository.findAll().size());
        List<HoaDon> listH = hoaDonRepository.findByNvAndTinhTrang(nv,0);
        if(listH.size()<=0){
            m.addAttribute("noHD","Không có hóa đơn chờ");
        }else{
            m.addAttribute("listHD",listH);
        }
        List<HoaDon> listLS = hoaDonRepository.findByNvAndTinhTrang(nv,1);
        m.addAttribute("listHDTT",listLS);
     return "manager/hoadon";
    }

    @GetMapping("/hoadonct/{id}")
    public String showct(Model m,@PathVariable String id) {
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
        m.addAttribute("tenNV",nv.getHo()+" "+nv.getTenDem()+" "+nv.getTen());
        LocalDate localDate = LocalDate.now();
        List<HoaDon> list = hoaDonRepository.findHoaDonsByNvAndTinhTrangAndNgayThanhToan(nv,1, Date.valueOf(localDate));
        m.addAttribute("soHD",list.size());
        m.addAttribute("soKH",khachHangRepository.findAll().size());
        List<HoaDon> listH = hoaDonRepository.findByNvAndTinhTrang(nv,0);
        if(listH.size()<=0){
            m.addAttribute("noHD","Không có hóa đơn chờ");
        }else{
            m.addAttribute("listHD",listH);
        }
        List<HoaDonChiTiet> listLS = hdctRes.findHoaDonChiTietsById_IdHoaDon_Id(UUID.fromString(id));
        m.addAttribute("listHDTT",listLS);
        return "manager/hoadonct";
    }

    @GetMapping("/lochd")
    public String showdd(Model m) {
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
        m.addAttribute("tenNV",nv.getHo()+" "+nv.getTenDem()+" "+nv.getTen());
        LocalDate localDate = LocalDate.now();
        List<HoaDon> list = hoaDonRepository.findHoaDonsByNvAndTinhTrangAndNgayThanhToan(nv,1, Date.valueOf(localDate));
        m.addAttribute("soHD",list.size());
        m.addAttribute("soKH",khachHangRepository.findAll().size());
        List<HoaDon> listH = hoaDonRepository.findByNvAndTinhTrang(nv,0);
        if(listH.size()<=0){
            m.addAttribute("noHD","Không có hóa đơn chờ");
        }else{
            m.addAttribute("listHD",listH);
        }

        String ngayBD = request.getParameter("ngayBD");
        String ngayKT = request.getParameter("ngayKT");

        if (ngayBD.isEmpty() && ngayKT.isEmpty()) {
            List<HoaDon> listLoc = hoaDonRepository.findByNvAndTinhTrang(nv,1);
            if (!list.isEmpty()) {
                request.setAttribute("listHDTT", listLoc);
                return "manager/hoadon";
            }
        }else{
            if (ngayBD.isEmpty()) {
                ngayBD = "1900-01-01";
            } else if (ngayKT.isEmpty()) {
                ngayKT = "2900-01-01";
            }
            request.setAttribute("listHDTT", hoaDonRepository.findHoaDonsByNvAndAndNgayThanhToanBetween(nv,Date.valueOf(ngayBD),Date.valueOf(ngayKT)));
            return "manager/hoadon";
        }
        return "manager/hoadon";
    }

    @GetMapping("/timKiem")
    public String showaa(Model m) throws UnsupportedEncodingException {
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
        m.addAttribute("tenNV",nv.getHo()+" "+nv.getTenDem()+" "+nv.getTen());
        LocalDate localDate = LocalDate.now();
        List<HoaDon> list = hoaDonRepository.findHoaDonsByNvAndTinhTrangAndNgayThanhToan(nv,1, Date.valueOf(localDate));
        m.addAttribute("soHD",list.size());
        m.addAttribute("soKH",khachHangRepository.findAll().size());
        List<HoaDon> listH = hoaDonRepository.findByNvAndTinhTrang(nv,0);
        if(listH.size()<=0){
            m.addAttribute("noHD","Không có hóa đơn chờ");
        }else{
            m.addAttribute("listHD",listH);
        }
        request.setCharacterEncoding("UTF-8");
        String ma = request.getParameter("maTim");
        List<HoaDon> listTim = hoaDonRepository.findHoaDonsByNvAndTinhTrangAndKh_TenOrNv_TenOrMa(nv,1,ma,ma,ma);
        if (listTim.isEmpty()) {
            m.addAttribute("tb","Không tìm thấy hóa đơn");
            request.setAttribute("listHDTT", hoaDonRepository.findByNvAndTinhTrang(nv,1));
            return "manager/hoadon";
        } else {
            request.setAttribute("listHDTT", listTim);
            return "manager/hoadon";
        }
    }
}
