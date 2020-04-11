package _04_order.dao;

import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _05_product.model.ProductBean;

public interface OrderItemDao {

	Integer updateProductStock(OrderItemBean oib, ProductBean pb, ShoppingCart cart);

	Integer updateProductStock(OrderItemBean oib, ProductBean pb);

}
