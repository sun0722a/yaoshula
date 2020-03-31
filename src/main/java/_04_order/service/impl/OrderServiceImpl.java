package _04_order.service.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _04_order.dao.OrderDao;
import _04_order.dao.OrderItemDao;
import _04_order.dao.impl.OrderDaoImpl;
import _04_order.dao.impl.OrderItemDaoImpl;
import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _04_order.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private SessionFactory factory;
	private OrderItemDao oidao;
	private OrderDao odao;

	public OrderServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		oidao = new OrderItemDaoImpl();
		odao = new OrderDaoImpl();
	}

	@Override
	public void persistOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			checkStock(ob);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println("發生異常" + e.getMessage());
				throw new RuntimeException(e);
			}
		}

	}

	private void checkStock(OrderBean ob) {
		Set<OrderItemBean> items = ob.getOrderItems();
		for (OrderItemBean oib : items) {
			oidao.updateProductStock(oib);
		}

	}

	@Override
	public OrderBean getOrder(int orderNo) {
		OrderBean bean = null;
		bean = odao.getOrder(orderNo);
		return bean;
	}

	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = odao.getAllOrders();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	@Override
	public List<OrderBean> getMemberOrders(Integer memberId) {
		List<OrderBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		list = odao.getMemberOrders(memberId);
//			tx.commit();
//		}catch(Exception e) {
//			if(tx != null) {
//				tx.rollback();
//				throw new RuntimeException(e);
//			}
//		}
		return list;
	}

	public String checkOrderStatus(Integer orderNo) {
		String status = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			status = checkOrderStatus(orderNo);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}
		return status;
	}

}
