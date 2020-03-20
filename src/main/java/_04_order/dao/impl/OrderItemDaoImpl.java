package _04_order.dao.impl;

import org.hibernate.SessionFactory;

import _00_init.util.HibernateUtils;
import _04_order.dao.OrderItemDao;
import _04_order.model.OrderItemBean;

public class OrderItemDaoImpl implements OrderItemDao {
	SessionFactory factory;

	public OrderItemDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Integer findItemsTotalPrice(OrderItemBean oib) {
		Integer total = oib.getQuantity() * oib.getUnitPrice();
		return total;
	} 

	@Override
	public Integer updateProductStock(OrderItemBean oib) {
//		int n = 0;
//		Integer stock = 0;
//		Session session = factory.getCurrentSession();
//		String hql0 = "SELECT stock FROM ProductBean Where "
		return null;
	}

	
	
	
	
	
}