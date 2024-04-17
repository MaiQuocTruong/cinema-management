package test;

import java.util.List;

import dao.EntityManagerFactoryUtil;
import dao.Phim_dao;
import dao.PhongChieuPhim_dao;
import dao.XuatChieu_dao;
import enities.Phim;
import enities.PhongChieuPhim;
import enities.XuatChieu;

public class TestAnhXa {
	public static void main(String[] args) {
		EntityManagerFactoryUtil emfu = new EntityManagerFactoryUtil();
		Phim_dao phim_dao = new Phim_dao(emfu.getEm());
		PhongChieuPhim_dao phongChieu_dao = new PhongChieuPhim_dao(emfu.getEm());
		XuatChieu_dao xc_dao = new XuatChieu_dao(emfu.getEm());
		
		
		List<Phim> listMovies = phim_dao.getListMovies();
		for (Phim phim : listMovies) {
			System.out.println(phim);
		}
		
//		List<PhongChieuPhim> listPhongChieu = phongChieu_dao.getListPhongChieu();
//		for (PhongChieuPhim phongChieuPhim : listPhongChieu) {
//			System.out.println(phongChieuPhim);
//		}
		
//		List<XuatChieu> listXC = xc_dao.getListXuatChieu();
//		for (XuatChieu xuatChieu : listXC) {
//			System.out.println(xuatChieu);
//		}
		
	}
}
