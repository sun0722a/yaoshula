package _04_shopping.service.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _04_shopping.dao.OrderDao;
import _04_shopping.dao.OrderItemDao;
import _04_shopping.dao.impl.OrderDaoImpl;
import _04_shopping.dao.impl.OrderItemDaoImpl;
import _04_shopping.model.OrderBean;
import _04_shopping.model.OrderItemBean;
import _04_shopping.service.OrderService;

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
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
				System.out.println("發生異常" + e.getMessage());
				throw new RuntimeException(e);
			}
		}
		
	}

	private void checkStock(OrderBean ob) {
		Set<OrderItemBean> items = ob.getOrderItems();
		for(OrderItemBean oib : items) {
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
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	@Override
	public List<OrderBean> getMemberOrders(String memberId) {
		List<OrderBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = odao.getMemberOrders(memberId);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
				throw new RuntimeException(e);
			}
		}
		return list;
	}
	
	
	
}
