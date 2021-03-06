package _04_order.service;

import java.util.List;

import _04_order.model.OrderBean;
import _04_order.model.ShoppingCart;

public interface OrderService {

	void insertOrder(OrderBean ob);

	void persistOrder(OrderBean ob);

	void persistOrder(OrderBean ob, ShoppingCart cart);

	List<OrderBean> getMemberOrders(Integer memberId);

	String checkOrderStatus(Integer orderNo);

	List<OrderBean> getAllOrders(String searchStr);

	OrderBean getOrder(int orderNo);

}
