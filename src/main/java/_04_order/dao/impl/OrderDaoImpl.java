package _04_order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _04_order.dao.OrderDao;
import _04_order.model.OrderBean;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	SessionFactory factory;

	public OrderDaoImpl() {
	}

	// 新增訂單資料
	@Override
	public void insertOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
		session.save(ob);
	}

	// 取得某個會員的訂單資料
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getMemberOrders(Integer memberId) {
		List<OrderBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean ob Where ob.memberId = :mid";
		list = session.createQuery(hql).setParameter("mid", memberId).getResultList();
		return list;
	}

	// 查詢訂單狀態
	public String checkOrderStatus(Integer orderNo) {
		OrderBean orderBean = null;
		String status = null;
		Session session = factory.getCurrentSession();
		String hql = "From OrderBean ob where ob.orderNo = :orderNo";
		orderBean = (OrderBean) session.createQuery(hql).setParameter("orderNo", orderNo).getSingleResult();
		if (orderBean.getShippingDate() == null) {
			status = "待出貨";
		} else if (orderBean.getArriveDate() == null) {
			status = "已出貨";
		} else {
			status = "完成";
		}
		return status;
	}

	// 取得所有訂單
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrders(String searchStr) {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean ob WHERE ob.orderNo LIKE :searchStr";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("searchStr", "%" + searchStr + "%").getResultList();
		return list;
	}

	// 取得訂單資料
	@Override
	public OrderBean getOrder(int orderNo) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		ob = session.get(OrderBean.class, orderNo);
		return ob;
	}

}
