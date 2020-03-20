package _04_Shopping.dao;


import _04_Shopping.model.OrderItemBean;

public interface OrderItemDao {
	
	Integer findItemsTotalPrice(OrderItemBean oib);
	
	Integer updateProductStock(OrderItemBean oib);
	
}
