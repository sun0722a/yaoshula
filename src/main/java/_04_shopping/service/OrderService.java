package _04_shopping.service;

import java.util.List;

import _04_shopping.model.OrderBean;

public interface OrderService {
	void persistOrder(OrderBean ob);
	
	OrderBean getOrder(int orderNo);
	
	List<OrderBean> getAllOrders();
	
	List<OrderBean> getMemberOrders(String memberId);
}
