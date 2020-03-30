package _04_order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.util.HibernateUtils;
import _04_order.dao.OrderDao;
import _04_order.model.OrderBean;

public class OrderDaoImpl implements OrderDao {
	private Integer memberId = null;
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
	public List<OrderBean> getMemberOrders(Integer memberId) {
		List<OrderBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean ob Where ob.memberId = :mid";
		list = session.createQuery(hql).setParameter("mid", memberId.toString()).getResultList();
		return list;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String checkOrderStatus(Integer orderNo) {
		OrderBean orderBean = null;
		String status = null;
		Session session = factory.getCurrentSession();
		String hql = "From OrderBean ob where ob.orderNo = :orderNo";
		orderBean = (OrderBean) session.createQuery(hql).setParameter("orderNo", orderNo).getSingleResult();
		if (orderBean.getShippingDate() == null) {
			status = "待出貨";
		} else if (orderBean.getArriveDate() == null) {
			status = "已出貨";
		} else {
			status = "完成";
		}
		return status;
	}

}
