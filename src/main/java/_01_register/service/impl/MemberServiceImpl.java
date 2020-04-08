package _01_register.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _01_register.dao.MemberDao;
import _01_register.dao.impl.MemberDaoImpl_Hibernate;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao dao;
	SessionFactory factory;

	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl_Hibernate();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.saveMember(mb);
			count++;
			tx.commit();
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException();
		}
		
		return count;
	}

	@Override
	public boolean idExists(String id) {
		boolean result;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.idExists(id);
			tx.commit();
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	@Override
	public boolean emailExists(String email) {
		boolean result;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.emailExists(email);
			tx.commit();
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException();
		}
		return result;
		
	}

//	@Override
//	public MemberBean queryMember(String id) {
//		MemberBean mb = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			mb = dao.queryMember(id);
//			tx.commit();
//		}catch(Exception ex) {
//			if(tx != null) {
//				tx.rollback();
//			}
//			ex.printStackTrace();
//			throw new RuntimeException();
//		}
//		return mb;
//	}
	
	@Override
	public MemberBean queryMember(int id) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = dao.queryMember(id);
			tx.commit();
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException();
		}
		return mb;
	}

	@Override
	public MemberBean checkIdPassword(String memberId, String password) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = dao.checkIdPassword(memberId, password);
			tx.commit();
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException();
		}
		return mb;
	}

	@Override
	public int updateMember(MemberBean mb) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.updateMember(mb);
			tx.commit();
			count++;
		}catch(Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
			throw new RuntimeException();
		}
		return count;
	}

}
