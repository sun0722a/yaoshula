package _01_register.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_register.dao.MemberDao;
import _01_register.model.MemberBean;

@Repository
public class MemberDaoImpl_Hibernate implements MemberDao {

	@Autowired
	SessionFactory factory;

	public MemberDaoImpl_Hibernate() {
	}

	// 新增會員資料
	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	// 判斷帳號是否已經被使用，如果是傳回true， 否則傳回false
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String hql = "FROM MemberBean m Where m.memberId = :id";
		Session session = factory.getCurrentSession();
		try {
			session.createQuery(hql).setParameter("id", id).getSingleResult();
			exist = true;
		} catch (NonUniqueResultException e) {
			exist = true;
		} catch (NoResultException e) {
			exist = false;
		}
		return exist;
	}

	// 判斷信箱是否已經被使用，如果是傳回true， 否則傳回false
	@Override
	public boolean emailExists(String email) {
		boolean exist = false;
		String hql = "FROM MemberBean m WHERE m.email = :email";
		Session session = factory.getCurrentSession();
		try {
			session.createQuery(hql).setParameter("email", email).getSingleResult();
			exist = true;
		} catch (NoResultException ex) {
			exist = false;
		}
		return exist;

	}

	// 更新會員資料
	@Override
	public int updateMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "UPDATE MemberBean m SET m.email = :email, m.phone = :phone, m.city = :city, "
				+ "m.area = :area, m.address = :address, m.fileName = :fileName, "
				+ "m.picture = :picture WHERE m.id = :id";
		session.createQuery(hql0).setParameter("email", mb.getEmail()).setParameter("phone", mb.getPhone())
				.setParameter("city", mb.getCity()).setParameter("area", mb.getArea())
				.setParameter("address", mb.getAddress()).setParameter("fileName", mb.getFileName())
				.setParameter("picture", mb.getPicture()).setParameter("id", mb.getId()).executeUpdate();
		n++;
		return n;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean checkIdPassword(String memberId, String password) {
		MemberBean mb = null;
		List<MemberBean> beans = null;
		String hql = "FROM MemberBean m WHERE m.memberId = :id and m.password = :password";
		Session session = factory.getCurrentSession();
		beans = session.createQuery(hql).setParameter("id", memberId).setParameter("password", password)
				.getResultList();
		if (beans.size() > 0) {
			mb = beans.get(0);
		}
		return mb;
	}

	// 取得會員資料
	@Override
	public MemberBean getMember(int id) {
		Session session = factory.getCurrentSession();
		MemberBean mb = null;
		mb = session.get(MemberBean.class, id);
		return mb;
	}

}
