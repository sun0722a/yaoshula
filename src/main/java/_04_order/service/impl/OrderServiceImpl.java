package _04_order.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _04_order.dao.OrderDao;
import _04_order.dao.OrderItemDao;
import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _04_order.service.OrderService;
import _05_product.dao.ProductDao;
import _05_product.model.ProductBean;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderItemDao oidao;
	@Autowired
	private OrderDao odao;
	@Autowired
	private ProductDao pdao;

	public OrderServiceImpl() {
	}

	@Transactional
	@Override
	public void insertOrder(OrderBean ob) {
		odao.insertOrder(ob);
	}

	// 儲存從購物車來的訂單
	@Transactional
	@Override
	public void persistOrder(OrderBean ob, ShoppingCart cart) {
		// 儲存訂單資料
		odao.insertOrder(ob);
		// 更新銷售量
		pdao.addSales(ob);
		// 更新庫存量
		checkStock(ob, cart);
	}

	// 儲存從直接購買來的訂單
	@Transactional
	@Override
	public void persistOrder(OrderBean ob) {
		// 儲存訂單資料
		odao.insertOrder(ob);
		// 更新銷售量
		pdao.addSales(ob);
		// 更新庫存量
		checkStock(ob);
	}

	// 更新庫存量、移除已加入訂單的購物車內商品
	private void checkStock(OrderBean ob, ShoppingCart cart) {
		Set<OrderItemBean> items = ob.getOrderItems();
		for (OrderItemBean oib : items) {
			ProductBean pb = pdao.getProduct(oib.getProductId());
			oidao.updateProductStock(oib, pb, cart);
		}
	}

	// 更新庫存量
	private void checkStock(OrderBean ob) {
		Set<OrderItemBean> items = ob.getOrderItems();
		for (OrderItemBean oib : items) {
			ProductBean pb = pdao.getProduct(oib.getProductId());
			oidao.updateProductStock(oib, pb);
		}
	}

	@Transactional
	@Override
	public List<OrderBean> getMemberOrders(Integer memberId) {
		List<OrderBean> list = null;
		list = odao.getMemberOrders(memberId);
		return list;
	}

	@Transactional
	public String checkOrderStatus(Integer orderNo) {
		String status = null;
		status = checkOrderStatus(orderNo);
		return status;
	}

	@Transactional
	@Override
	public List<OrderBean> getAllOrders(String searchStr) {
		List<OrderBean> list = null;
		list = odao.getAllOrders(searchStr);
		return list;
	}

	@Override
	public OrderBean getOrder(int orderNo) {
		OrderBean bean = null;
		bean = odao.getOrder(orderNo);
		return bean;
	}

}
