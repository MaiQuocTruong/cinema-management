package test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import dao.CTHoaDonDichVu_dao;
import dao.CTHoaDonPhim_dao;
import dao.EntityManagerFactoryUtil;
import dao.NhanVien_dao;
import dao.Phim_dao;
import dao.PhongChieuPhim_dao;
import dao.XuatChieu_dao;
import enities.CTHoaDonDichVu;
import enities.NhanVien;
import enities.Phim;
import enities.PhongChieuPhim;
import enities.XuatChieu;

public class TestAnhXa {
	public static void main(String[] args) {
		EntityManagerFactoryUtil emfu = new EntityManagerFactoryUtil();
//		Phim_dao phim_dao = new Phim_dao(emfu.getEm());
//		PhongChieuPhim_dao phongChieu_dao = new PhongChieuPhim_dao(emfu.getEm());
		XuatChieu_dao xc_dao = new XuatChieu_dao(emfu.getEm());
		NhanVien_dao nv_dao = new NhanVien_dao(emfu.getEm());
//		
//		CTHoaDonPhim_dao cthd_phim = new CTHoaDonPhim_dao(emfu.getEm());
//		 Map<String, Double> totalRevenueMap = cthd_phim.getTotalRevenueByMovie();
//		
//		 for (Map.Entry<String, Double> entry : totalRevenueMap.entrySet()) {
//	            String tenPhim = entry.getKey();
//	            double totalRevenue = entry.getValue();
//	            System.out.println("Tên phim: " + tenPhim + ", Tổng doanh thu: " + totalRevenue);
//	       }
		
		
//		CTHoaDonDichVu_dao cthd_dv = new CTHoaDonDichVu_dao(emfu.getEm());
//		Map<String, Double> totalRevenueMap = cthd_dv.getDoanhThuDichVu();
//		 for (Map.Entry<String, Double> entry : totalRevenueMap.entrySet()) {
//			 String tenDichVu = entry.getKey();
//			 double totalRevenue = entry.getValue();
//			 System.out.println("Tên phim: " + tenDichVu + ", Tổng doanh thu: " + totalRevenue);
//		 }
		
		
		xc_dao.deleteXuatChieu("X003");
//		
//		List<Phim> listMovies = phim_dao.getListMovies();
//		for (Phim phim : listMovies) {
//			System.out.println(phim);
//		}
		
//		List<PhongChieuPhim> listPhongChieu = phongChieu_dao.getListPhongChieu();
//		for (PhongChieuPhim phongChieuPhim : listPhongChieu) {
//			System.out.println(phongChieuPhim);
//		}
		
//		List<XuatChieu> listXC = xc_dao.getListXuatChieu();
//		for (XuatChieu xuatChieu : listXC) {
//			System.out.println(xuatChieu);
//		}
		
		
//		NhanVien nhanvien = nv_dao.findEmployeeOnId("NV003");
//		LocalDate date = LocalDate.now();
//		nhanvien.setNgaySinh(date);
//		
//		nv_dao.updateNV(nhanvien);
		
		
		
	}
}
