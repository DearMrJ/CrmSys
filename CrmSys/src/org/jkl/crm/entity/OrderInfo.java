package org.jkl.crm.entity;

import java.util.Date;

public class OrderInfo {
	private String id;
	private Integer cartId;
	private User user;//用户信息
	private Good good;//商品信息
	private String name;
	private String tel;
	private String address;
	private Date dealTime;
	private float price;
	private int status; //订单状态
	
	public OrderInfo() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
