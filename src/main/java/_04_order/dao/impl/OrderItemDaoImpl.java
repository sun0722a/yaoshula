package _04_order.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_order.dao.OrderItemDao;
import _04_order.model.OrderItemBean;
import _05_product.model.ProductFormatBean;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
	@Autowired
	SessionFactory factory;

	public OrderItemDaoImpl() {
//		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Integer findItemsTotalPrice(OrderItemBean oib) {
		Integer total = oib.getQuantity() * oib.getUnitPrice();
		return total;
	}

	@Override
	public Integer updateProductStock(OrderItemBean oib) {
		int n = 0;
		Session session = factory.getCurrentSession();
		String hql1 = "FROM ProductFormatBean pfb WHERE pfb.formatContent1 = :formatContent1 AND pfb.formatContent2 = :formatContent2";
		ProductFormatBean pfb = (ProductFormatBean) session.createQuery(hql1)
				.setParameter("formatContent1", oib.getFormatContent1())
				.setParameter("formatContent1", oib.getFormatContent2()).getSingleResult();
		
		String hql2 = "UPDATE ProductFormatBean pfb SET pfb.stock = stock - :orderQuantity WHERE productFormatId = :productFormatId";
		n = session.createQuery(hql2).setParameter("productFormatId", pfb.getProductFormatId())
				.setParameter("orderAmount", oib.getQuantity()).executeUpdate();
		return n;
	}

}
