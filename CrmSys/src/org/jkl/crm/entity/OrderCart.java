package org.jkl.crm.entity;

public class OrderCart {
	private Integer id;//cartid
	private User user;
	private Good good;
	private Integer count; //单样商品 个数
	private float price;//最后插入订单信息的总价钱
	
	
	public OrderCart() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public float getPrice() {
		return this.good.getPrice()*count;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
