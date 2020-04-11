package _04_order.dao;

import _04_order.model.OrderItemBean;
import _05_product.model.ProductBean;

public interface OrderItemDao {

	Integer updateProductStock(OrderItemBean oib, ProductBean pb);

}
