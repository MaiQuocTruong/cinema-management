package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import enities.KhachHang;


public class KhachHang_dao {
	//
	private EntityManager em;
	
	
	public KhachHang_dao(EntityManager em) {
		super();
		this.em = em;
	}




	public List<KhachHang> getListCustomer(){
		
		List<KhachHang> listKH = new ArrayList<KhachHang>();
		try {
			em.getTransaction().begin();
//	Khi get danh sách ra nếu không sử dụng trans begin và transcommit và clear thì danh sách này 
//	Sẽ được lưu vào trong cache và khi chúng ta gọi tới phương thức này trong 1 hàm khác mà ko có pthức clear
//	Thì nó sẽ lấy danh sách ở trong cache ra làm cho dữ liệu bị cũ p thức này dùng để xóa toàn bộ cache trong
//	Entity Manger và khi gọi lại với phương thức như thế này nó sẽ xóa cachce và lấy dữ liệu mới
			em.clear();
			TypedQuery<KhachHang> resultQuery = em.createQuery("Select kh from KhachHang kh" , KhachHang.class);
			listKH = resultQuery.getResultList();
			em.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return listKH;
	}
	
	public void addKhachHang(KhachHang kh) {
		try {
			em.getTransaction().begin();
			em.persist(kh);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			e.printStackTrace();
		}
	}
	
	public KhachHang findCustomerOnPhoneNumber(String phoneNumber) {
		try {
	        KhachHang kh = em.createQuery("SELECT kh FROM KhachHang kh WHERE kh.sdt = :sdt", KhachHang.class)
	                .setParameter("sdt", phoneNumber)
	                .getSingleResult();
	        
	        return kh; // Trả về khách hàng nếu tìm thấy
	    } catch (NoResultException e) {
	        return new KhachHang(); // Trả về null nếu không tìm thấy khách hàng
	    }
	}
	
	public void updateKhachHang(KhachHang kh_update) {
		try {
			em.getTransaction().begin();
			em.merge(kh_update);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction().isActive()) {
				System.out.println("Vẫn Đang Active");
			}
			
			e.printStackTrace();
		}
	}
	
	public void deleteKhachHang(String idKhachHang) {
		try {
//			// Kiểm tra xem đối tượng có trong persistence context hay không
			 KhachHang khachHangDelete = em.find(KhachHang.class, idKhachHang);
			 if (khachHangDelete != null && em.contains(khachHangDelete)) {
				 	em.getTransaction().begin();
	                em.remove(khachHangDelete); // Xóa đối tượng nếu nó được quản lý
	                em.getTransaction().commit();
	          }
		}catch (Exception e) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback transaction nếu có lỗi xảy ra
            }
            e.printStackTrace();
		}
	}
	
	
}
