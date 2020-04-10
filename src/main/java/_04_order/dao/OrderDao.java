package _04_order.dao;

import java.util.List;

import _04_order.model.OrderBean;

public interface OrderDao {

	void insertOrder(OrderBean ob);

	List<OrderBean> getMemberOrders(Integer memberId);

	String checkOrderStatus(Integer orderNo);

//	List<OrderBean> getAllOrders();

	OrderBean getOrder(int orderNo);

}
