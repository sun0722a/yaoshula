package _04_order.model;

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
	String memberName;
	Integer totalPrice;
	String address;
	String phoneNumber;
	String orderNote;
	Date orderDate;
	Date shippingDate;
	Date arriveDate;

	@OneToMany(mappedBy = "orderBean", cascade = CascadeType.ALL)
	Set<OrderItemBean> orderItems = new LinkedHashSet<>();

	public OrderBean(Integer orderNo, String memberId,String memberName,Integer totalPrice, String address, String phoneNumber,
			String orderNote, Date orderDate, Date shippingDate, Date arriveDate) {
		super();
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.totalPrice = totalPrice;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.orderNote = orderNote;
		this.orderDate = orderDate;
		this.shippingDate = shippingDate;
		this.arriveDate = arriveDate;
	}

	public OrderBean() {

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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
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

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Set<OrderItemBean> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemBean> orderItems) {
		this.orderItems = orderItems;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	

}
