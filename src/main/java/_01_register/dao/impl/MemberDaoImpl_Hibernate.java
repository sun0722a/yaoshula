package _01_register.dao.impl;

import java.sql.Connection;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.util.HibernateUtils;
import _01_register.dao.MemberDao;
import _01_register.model.MemberBean;



public class MemberDaoImpl_Hibernate implements MemberDao {

	SessionFactory factory;

	public MemberDaoImpl_Hibernate() {
		factory = HibernateUtils.getSessionFactory();
	}

	// 儲存MemberBean物件，將參數mb新增到Memberinfo表格內。

	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
		
	}

	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String hql = "FROM MemberBean m Where m.memberId = :id";
		Session session = factory.getCurrentSession();
		try {
			session.createQuery(hql)
					.setParameter("id", id)
					.getSingleResult();
			exist = true;
		}catch(NonUniqueResultException e) {
			exist = true;
		}catch(NoResultException e) {
			exist = false;
		}
		return exist;
	}

	// 判斷參數email(會員信箱)是否已經被現有客戶使用，如果是，傳回true，表示此email不能使用，
	// 否則傳回false，表示此email可使用。
	@Override
	public boolean emailExists(String email) {
		boolean exist = false;
		String hql = "FROM MemberBean m WHERE m.email = :email";
		Session session = factory.getCurrentSession();
		try {
			session.createQuery(hql)
				   .setParameter("email", email)
				   .getSingleResult();
			exist = true;
			
		} catch (NoResultException ex) {
			exist = false;
		}
		return exist;
		
	}

	// 更新MemberBean物件，將參數mb新增到Memberinfo表格內。
//	@Override
//	public int updateMember(MemberBean mb) {
//		String hql = "UPDATE Memberinfo "
//				+ " SET user_email= ?, user_phone= ?, user_address= ?, fileName= ?,user_picture= ?"
//				+ " WHERE user_id= ?";
//		int n = 0;
//		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
//			ps.setString(1, mb.getEmail());
//			ps.setString(2, mb.getPhone());
//			ps.setString(3, mb.getAddress());
//			ps.setString(4, mb.getFileName());
//			ps.setBlob(5, mb.getPicture());
//			ps.setInt(6, mb.getId());
//
//			n = ps.executeUpdate();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateMember()發生例外: " + ex.getMessage());
//		}
//		return n;
//	}
	
	@Override
	public int updateMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "UPDATE MemberBean m SET m.email = :email,m.phone = :phone,m.address = :address, "
				+ "m.fileName = :fileName,m.picture = :picture WHERE m.id = :id";
		session.createQuery(hql0)
			   .setParameter("email", mb.getEmail())
			   .setParameter("phone", mb.getPhone())
			   .setParameter("address", mb.getAddress())
			   .setParameter("fileName", mb.getFileName())
			   .setParameter("picture", mb.getPicture())
			   .setParameter("id", mb.getId())
			   .executeUpdate();
		n++;
		return n;
	}

	// 由參數 id (會員帳號) 到Memberinfo表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
//	@SuppressWarnings("unchecked")
//	@Override
//	public MemberBean queryMember(String id) {
//		MemberBean mb = null;
//		String hql = "FROM MemberBean m WHERE m.memberId = :id";
//		Session session = factory.getCurrentSession();
//		List<MemberBean> beans = null;
//		beans = session.createQuery(hql)
//			   .setParameter("memberId", id)
//			   .getResultList();
//		if(beans.size() > 0) {
//			mb = beans.get(0);
//		}
//		return mb;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean queryMember(int id) {
		MemberBean mb = null;
		String hql = "FROM MemberBean m WHERE m.id = :id";
		Session session = factory.getCurrentSession();
		List<MemberBean> beans = null;
		beans = session.createQuery(hql)
			   .setParameter("id", id)
			   .getResultList();
		if(beans.size() > 0) {
			mb = beans.get(0);
		}
		return mb;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean checkIdPassword(String memberId, String password) {
		MemberBean mb = null;
		List<MemberBean> beans = null;
		String hql = "FROM MemberBean m WHERE m.memberId = :id and m.password = :password";
		Session session = factory.getCurrentSession();
		beans =  session.createQuery(hql)
						.setParameter("id", memberId)
						.setParameter("password", password)
						.getResultList();
		if(beans.size() > 0) {
			mb = beans.get(0);
		}
		return mb;
	}

	@Override
	public void setConnection(Connection conn) {
//		this.conn = conn;
	}

}
