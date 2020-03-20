package _04_shopping.dao;

import java.util.List;

import _04_shopping.model.OrderBean;

public interface OrderDao {
	void insertOrder(OrderBean ob);
	
	OrderBean getOrder(int orderNo);
	
	List<OrderBean> getAllOrders();
	
	List<OrderBean> getMemberOrders(String memberId);
	
}
