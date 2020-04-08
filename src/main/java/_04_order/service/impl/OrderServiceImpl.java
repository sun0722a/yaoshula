package _04_order.service.impl;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _04_order.dao.OrderDao;
import _04_order.dao.OrderItemDao;

import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _04_order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
//	private SessionFactory factory;
	@Autowired
	private OrderItemDao oidao;
	@Autowired
	private OrderDao odao;

	public OrderServiceImpl() {
//		factory = HibernateUtils.getSessionFactory();
//		oidao = new OrderItemDaoImpl();
//		odao = new OrderDaoImpl();
	}
	
	@Transactional
	@Override
	public void persistOrder(OrderBean ob) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			checkStock(ob);
			odao.insertOrder(ob);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//				System.out.println("發生異常" + e.getMessage());
//				throw new RuntimeException(e);
//			}
//		}

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
	
	@Transactional
	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = odao.getAllOrders();
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//				throw new RuntimeException(e);
//			}
//		}
		return list;
	}
	
	@Transactional
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
	
	@Transactional
	public String checkOrderStatus(Integer orderNo) {
		String status = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			status = checkOrderStatus(orderNo);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//				throw new RuntimeException(e);
//			}
//		}
		return status;
	}

}
