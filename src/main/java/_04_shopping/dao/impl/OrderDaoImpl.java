package _04_shopping.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.util.HibernateUtils;
import _04_shopping.dao.OrderDao;
import _04_shopping.model.OrderBean;

public class OrderDaoImpl implements OrderDao{
	private String memberId = null;
	SessionFactory factory;
	int orderNo = 0;
	
	
	public OrderDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	@Override
	public void insertOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
		session.save(ob);
		
	}
	@Override
	public OrderBean getOrder(int orderNo) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		ob = session.get(OrderBean.class, orderNo);
		return ob;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getMemberOrders(String memberId) {

		List<OrderBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean ob Where ob.memberId = :mid";
		list = session.createQuery(hql).setParameter("mid", memberId).getResultList();
		return list;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
}
