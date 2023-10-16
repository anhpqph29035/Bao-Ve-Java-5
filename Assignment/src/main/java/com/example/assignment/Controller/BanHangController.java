package com.example.assignment.Controller;

import com.example.assignment.Model.*;
import com.example.assignment.Repository.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/cua-hang")
public class BanHangController {

    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    NhanVienRespository nhanVienRespository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    CTSanPhamRepository ctSanPhamRepository;
    @Autowired
    HDCTRes hoaDonctRepository;


    @GetMapping("/banhang")
    public String hi(Model m){
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("tenNV")) {
                    value = cookie.getValue();
                }
            }
        }
        List<HoaDon> list =  hoaDonRepository.findByNv_TenAndTinhTrang(value,0);
        m.addAttribute("listHD",list);
        return "manager/banhang";
    }

    @GetMapping("/addhd")
    public String addHD(Model m){

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
        if(hoaDonRepository.findByNvAndTinhTrang(nv,0).size()==4){
           m.addAttribute("tb","Tối đa 4 hóa đơn chờ");
            List<HoaDon> list =  hoaDonRepository.findByNvAndTinhTrang(nv,0);
            m.addAttribute("listHD",list);
           return "manager/banhang";
        };
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNv(nhanVienRespository.findNhanVienById(UUID.fromString(value)));
        int min = 1000000; // Số nguyên tối thiểu với 7 chữ số
        int max = 9999999; // Số nguyên tối đa với 7 chữ số
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        hoaDon.setMa("HD"+randomNumber);
        LocalDate localDate = LocalDate.now();
        hoaDon.setNgayTao(Date.valueOf(localDate));
        hoaDonRepository.save(hoaDon);
        m.addAttribute("listHD",hoaDonRepository.findByNvAndTinhTrang(nhanVienRespository.findNhanVienById(UUID.fromString(value)),0));
        return "manager/banhang";
    }

    @GetMapping("/deletehd/{id}")
    public String delete(Model m, @PathVariable String id) {
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("idNV")) {
                    value = cookie.getValue();
                }
            }
        }
        hoaDonRepository.deleteById(UUID.fromString(id));
        m.addAttribute("tb","Xóa thành công !");
        m.addAttribute("listHD",hoaDonRepository.findByNvAndTinhTrang(nhanVienRespository.findNhanVienById(UUID.fromString(value)),0));
        return "manager/banhang";
    }

    @GetMapping("/update-hd-khach-hang/{id}")
    private String updateKHbyHD(@PathVariable String id, Model model){
        HoaDon hd = hoaDonRepository.findHoaDonById(UUID.fromString(id));
        model.addAttribute("idHD",hd.getId());
        model.addAttribute("sv",new KhachHang());
        model.addAttribute("listKH",khachHangRepository.findAll());
        return "manager/chonkh";
    }

    @GetMapping("/add-khach-hang/{idKH}/{idHD}")
    private String addKH(@PathVariable("idKH") String idKH,
                         @PathVariable("idHD") String idHD,Model m

    ){
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
        KhachHang kh = khachHangRepository.findKhachHangById(UUID.fromString(idKH));
        HoaDon hoaDon= hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
        hoaDon.setKh(kh);
        hoaDonRepository.save(hoaDon);
        m.addAttribute("listHD",hoaDonRepository.findByNvAndTinhTrang(nhanVienRespository.findNhanVienById(UUID.fromString(value)),0));
        return "redirect:/cua-hang/banhang";
    }
    @GetMapping("/showsp/{idHD}")
    private String addKHcc(@PathVariable("idHD") String idHD,Model m){
        m.addAttribute("listSP",ctSanPhamRepository.findAll());
        return "manager/chonsp";
    }

    @GetMapping("/chonHD/{idHD}")
    private String chiTietHD(@PathVariable("idHD") String idHD,Model model){
        HoaDon hoaDon= hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
        model.addAttribute("hd",hoaDon);
        List<HoaDonChiTiet> listHDCT= hoaDonctRepository.findHoaDonChiTietsById_IdHoaDon_Id(UUID.fromString(idHD));

        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("idNV")) {
                    value = cookie.getValue();
                }
            }
        }
        NhanVien nhanVien=nhanVienRespository.findNhanVienById(UUID.fromString(value));
        List<HoaDon> list = hoaDonRepository.findByNvAndTinhTrang(nhanVien,0);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listHD",list);
        model.addAttribute("tongTien",hoaDonctRepository.getTongTien(UUID.fromString(idHD)));
        return "manager/banhang";
    }

    @PostMapping("/addgh")
    private String addSPGH(@RequestParam ("idSP") String idSp, Model model ){
        String idHD = request.getParameter("idHD");
        System.out.println(idHD);
        if(hoaDonctRepository.findHoaDonChiTietById_IdHoaDon_IdAndId_IdChiTietSp_Id(UUID.fromString(idHD),UUID.fromString(idSp))==null){
            ChiTietSanPham chiTietSP = ctSanPhamRepository.findChiTietSanPhamById(UUID.fromString(idSp));
            String sl = request.getParameter("soLuong");
            HoaDon hd = hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
            HoaDonChiTiet hoaDonChiTiet= new HoaDonChiTiet();
            IdHoaDonChiTiet idHoaDonChiTiet = new IdHoaDonChiTiet();
            idHoaDonChiTiet.setIdChiTietSp(chiTietSP);
            idHoaDonChiTiet.setIdHoaDon(hd);
            hoaDonChiTiet.setId(idHoaDonChiTiet);
            Double gia=Integer.valueOf(sl)*chiTietSP.getGiaBan().doubleValue();
            hoaDonChiTiet.setSoLuong(Integer.valueOf(sl));
            hoaDonChiTiet.setDonGia(BigDecimal.valueOf(gia));
            hd.setTongTien(BigDecimal.valueOf(gia));
            hoaDonRepository.save(hd);
            hoaDonctRepository.save(hoaDonChiTiet);

        }
        else {
            ChiTietSanPham chiTietSP = ctSanPhamRepository.findChiTietSanPhamById(UUID.fromString(idSp));
            HoaDon hd = hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
            HoaDonChiTiet hoaDonChiTiet= new HoaDonChiTiet();
            IdHoaDonChiTiet idHoaDonChiTiet = new IdHoaDonChiTiet();
            idHoaDonChiTiet.setIdChiTietSp(chiTietSP);
            idHoaDonChiTiet.setIdHoaDon(hd);
            hoaDonChiTiet.setId(idHoaDonChiTiet);
            String sl = request.getParameter("soLuong");
            HoaDonChiTiet hoaDonChiTiet1= hoaDonctRepository.findHoaDonChiTietById_IdHoaDon_IdAndId_IdChiTietSp_Id(UUID.fromString(idHD),UUID.fromString(idSp));
            int slUPdate= Integer.parseInt(sl)+hoaDonChiTiet1.getSoLuong();
            hoaDonChiTiet.setSoLuong(slUPdate);
            Double gia=(Integer.valueOf(sl)*chiTietSP.getGiaBan().doubleValue())+hoaDonChiTiet1.getDonGia().doubleValue();
            hoaDonChiTiet.setDonGia(BigDecimal.valueOf(gia));
            hd.setTongTien(BigDecimal.valueOf(gia));
            hoaDonRepository.save(hd);
            hoaDonctRepository.save(hoaDonChiTiet);
        }
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("idNV")) {
                    value = cookie.getValue();
                }
            }
        }
        NhanVien nhanVien= nhanVienRespository.findNhanVienById(UUID.fromString(value));
        HoaDon hoaDon= hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
        model.addAttribute("hd",hoaDon);
        List<HoaDon> list = hoaDonRepository.findByNvAndTinhTrang(nhanVien,0);
        List<HoaDonChiTiet> listHDCT= hoaDonctRepository.findHoaDonChiTietsById_IdHoaDon_Id(UUID.fromString(idHD));
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listHD",list);
        model.addAttribute("tongTien",hoaDonctRepository.getTongTien(UUID.fromString(idHD)));
        model.addAttribute("idHD",idHD);
        return "manager/banhang";
    }

    @GetMapping("/deletespgh/{idHD}/{idCTSP}")
    private  String xoaSP(@PathVariable("idHD") String idHD,@PathVariable("idCTSP") String idCTSP, Model model){

        HoaDonChiTiet hoaDonChiTiet= hoaDonctRepository.findHoaDonChiTietById_IdHoaDon_IdAndId_IdChiTietSp_Id(UUID.fromString(idHD),UUID.fromString(idCTSP));
        hoaDonctRepository.delete(hoaDonChiTiet);
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("idNV")) {
                    value = cookie.getValue();
                }
            }
        }
        NhanVien nhanVien=nhanVienRespository.findNhanVienById(UUID.fromString(value));
        HoaDon hoaDon=hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
        model.addAttribute("hd",hoaDon);
        List<HoaDon> list = hoaDonRepository.findByNvAndTinhTrang(nhanVien,0);
        List<HoaDonChiTiet> listHDCT=hoaDonctRepository.findHoaDonChiTietsById_IdHoaDon_Id(UUID.fromString(idHD));
        model.addAttribute("listHD",list);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("tongTien",hoaDonctRepository.getTongTien(UUID.fromString(idHD)));
        model.addAttribute("idHD",idHD);
        return "manager/banhang";
    }

    @PostMapping("/thanh-toan")
    private String thanhToan(@RequestParam("id") String idHD, Model model){
        HoaDon hoaDon= hoaDonRepository.findHoaDonById(UUID.fromString(idHD));
        hoaDon.setId(UUID.fromString(idHD));
        LocalDate localDate =  LocalDate.now();
        hoaDon.setNgayThanhToan(Date.valueOf(localDate));
        hoaDon.setTinhTrang(1);
        hoaDonRepository.save(hoaDon);
        String value = "";
        Cookie ck[] = request.getCookies();
        if (ck != null) {
            for (Cookie cookie : ck) {
                if (cookie.getName().equals("idNV")) {
                    value = cookie.getValue();
                }
            }
        }

        NhanVien nhanVien=nhanVienRespository.findNhanVienById(UUID.fromString(value));
        List<HoaDon> list = hoaDonRepository.findByNvAndTinhTrang(nhanVien,0);
        model.addAttribute("listHD",list);
        return "manager/banhang";
    }
}
