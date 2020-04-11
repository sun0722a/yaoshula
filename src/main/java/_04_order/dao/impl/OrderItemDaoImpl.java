package _04_order.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_order.dao.OrderItemDao;
import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

	@Autowired
	SessionFactory factory;

	@Autowired

	public OrderItemDaoImpl() {
	}

	// 更新庫存量、移除已加入訂單的購物車內商品
	@Override
	public Integer updateProductStock(OrderItemBean oib, ProductBean pb, ShoppingCart cart) {
		int n = 0;
		Session session = factory.getCurrentSession();
		String hql1 = "FROM ProductFormatBean pfb WHERE pfb.formatContent1 = :formatContent1 AND pfb.formatContent2 = :formatContent2 AND pfb.product= :product";
		ProductFormatBean pfb = (ProductFormatBean) session.createQuery(hql1)
				.setParameter("formatContent1", oib.getFormatContent1())
				.setParameter("formatContent2", oib.getFormatContent2()).setParameter("product", pb).getSingleResult();

		cart.deleteProduct(pfb.getProductFormatId());

		String hql2 = "UPDATE ProductFormatBean pfb SET pfb.stock = stock - :orderQuantity WHERE productFormatId = :productFormatId";
		n = session.createQuery(hql2).setParameter("productFormatId", pfb.getProductFormatId())
				.setParameter("orderQuantity", oib.getQuantity()).executeUpdate();
		return n;
	}
	
	// 更新庫存量
		@Override
		public Integer updateProductStock(OrderItemBean oib, ProductBean pb) {
			int n = 0;
			Session session = factory.getCurrentSession();
			String hql1 = "FROM ProductFormatBean pfb WHERE pfb.formatContent1 = :formatContent1 AND pfb.formatContent2 = :formatContent2 AND pfb.product= :product";
			ProductFormatBean pfb = (ProductFormatBean) session.createQuery(hql1)
					.setParameter("formatContent1", oib.getFormatContent1())
					.setParameter("formatContent2", oib.getFormatContent2()).setParameter("product", pb).getSingleResult();

			String hql2 = "UPDATE ProductFormatBean pfb SET pfb.stock = stock - :orderQuantity WHERE productFormatId = :productFormatId";
			n = session.createQuery(hql2).setParameter("productFormatId", pfb.getProductFormatId())
					.setParameter("orderQuantity", oib.getQuantity()).executeUpdate();
			return n;
		}

}
