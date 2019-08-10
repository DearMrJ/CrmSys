package org.jkl.crm.entity;

public class ShoppingCart {
	private Integer id;
	private User user;
	private Good good;
	private Integer count ;
	private float price;//单样总价
	
	public ShoppingCart() {
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
