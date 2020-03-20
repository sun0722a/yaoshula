package _04_shopping.dao;


import _04_shopping.model.OrderItemBean;

public interface OrderItemDao {
	
	Integer findItemsTotalPrice(OrderItemBean oib);
	
	Integer updateProductStock(OrderItemBean oib);
	
}
