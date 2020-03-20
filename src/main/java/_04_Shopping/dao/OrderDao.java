package _04_Shopping.dao;

import java.util.List;

import _04_Shopping.model.OrderBean;

public interface OrderDao {
	void insertOrder(OrderBean ob);
	
	OrderBean getOrder(int orderNo);
	
	List<OrderBean> getAllOrders();
	
	List<OrderBean> getMemberOrders(String memberId);
	
}
