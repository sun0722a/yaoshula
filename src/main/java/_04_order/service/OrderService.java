package _04_order.service;

import java.util.List;

import _04_order.model.OrderBean;

public interface OrderService {
	void persistOrder(OrderBean ob);

	OrderBean getOrder(int orderNo);

	List<OrderBean> getAllOrders();

	List<OrderBean> getMemberOrders(Integer memberId);

	String checkOrderStatus(Integer orderNo);
}
