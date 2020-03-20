package _04_order.dao;


import _04_order.model.OrderItemBean;

public interface OrderItemDao {
	
	Integer findItemsTotalPrice(OrderItemBean oib);
	
	Integer updateProductStock(OrderItemBean oib);
	
}
