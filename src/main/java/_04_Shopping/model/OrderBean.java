package _04_Shopping.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Orders")
public class OrderBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderNo;
	String memberId;
	Integer totalQuantity;
	String address;
	String phoneNumber;
	Date orderDate;
	Date shippingDate;
	
	@OneToMany(mappedBy = "orderBean", cascade = CascadeType.ALL)
	Set<OrderItemBean> orderItems = new LinkedHashSet<>();
	
	public OrderBean() {
		
	}
	
	








	public OrderBean(Integer orderNo, String memberId, Integer totalQuantity, String address, String phoneNumber,
			Date orderDate, Date shippingDate, Set<OrderItemBean> orderItems) {
		super();
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.totalQuantity = totalQuantity;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.orderDate = orderDate;
		this.shippingDate = shippingDate;
		this.orderItems = orderItems;
	}





	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Set<OrderItemBean> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemBean> orderItems) {
		this.orderItems = orderItems;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
