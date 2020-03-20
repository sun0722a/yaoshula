package _04_Shopping.service;

import java.util.List;

import _04_Shopping.model.OrderBean;

public interface OrderService {
	void persistOrder(OrderBean ob);
	
	OrderBean getOrder(int orderNo);
	
	List<OrderBean> getAllOrders();
	
	List<OrderBean> getMemberOrders(String memberId);
}
