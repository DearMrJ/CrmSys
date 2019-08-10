package org.jkl.crm.service;

import java.util.List;

import org.jkl.crm.entity.Good;
import org.jkl.crm.entity.OrderCart;
import org.jkl.crm.entity.OrderInfo;
import org.jkl.crm.entity.ShoppingCart;
import org.jkl.crm.entity.User;
import org.jkl.crm.util.tag.PageModel;

public interface CrmService {
	
	/*************************************用户部分***********************************/
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	User login(String name, String password);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 */
	List<User> findUserByName(String name);
	/**
	 * 查询库中是否存在相同用户名的用户
	 * @param name
	 * @return
	 */
	boolean isExist(String name);
	/**
	 * 按条件查询用户,分页显示
	 * @return
	 */
	List<User> findUsers(User user,PageModel pageModel);
	/**
	 * 根据id删除用户
	 * @param id
	 */
	void removeUserById(Integer id );
	/**
	 * 修改用户信息
	 * @param user
	 */
	void modifyUser(User user );
	/**
	 * 用户注册
	 * @param user
	 */
	void addUser(User user);
	
	/*************************************商品部分***********************************/
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	Good findGoodById(Integer id);
	/**
	 * 根据条件查询商品，并分页显示返回结果
	 * @param good
	 * @return
	 */
	List<Good> findGoods(Good good,PageModel pageModel);
	/**
	 * 修改商品信息，管理员操作
	 * @param good
	 */
	void modifyGood(Good good);
	/**
	 *添加商品，管理员操作
	 * @param good
	 */
	void addGood(Good good );
	/**
	 * 下架商品，删除商品，管理员操作
	 * @param good
	 */
	void deleteGood(Integer id);
	
	
	
	/*************************************购物车部分***********************************/
	/**
	 * 根据id查询购物车，系统操作
	 * @param id
	 * @return
	 */
	ShoppingCart findCartById(Integer  id);
	
	/**
	 * 传递用户id，根据用户id匹配对应的购物车
	 * @param shoppingCart
	 * @return
	 */
	List<ShoppingCart> findCarts(ShoppingCart shoppingCart,PageModel pageModel);
	/**
	 * 修改购物车
	 * @param shoppingCart
	 */
	void modifyCart(ShoppingCart shoppingCart);
	/**
	 * 根据点击删除购物车
	 * @param shoppingCart
	 */
	void deleteCarts(Integer id);
	/**
	 * 新增ordercart
	 * @param orderCart
	 */
	void addOrderCart(ShoppingCart shoppingCart);
	
	
	/************************orderCart部分，与order联查*******************************/
	/*******区别与购物车shoppingcart，shoppingcart显示未下单的商品，此处用来存储已经下单的商品******/
	/**
	 * 根据id查询，系统操作
	 * @param id
	 * @return
	 */
	OrderCart findOrderCartById(Integer id);
	/**
	 * 传递用户id，根据用户id匹配对应的orderCart
	 * @param orderCart
	 * @return
	 */
	List<OrderCart> findOrderCarts(OrderCart orderCart,PageModel pageModel);
	/**
	 * 修改ordercart，点击下单后，二次确定下单之前，还可以修改商品数量
	 * @param orderCart
	 */
	void modifyOrderCart(OrderCart orderCart);
	/**
	 * 根据点击删除购物车
	 * @param orderCart
	 */
	void deleteOrderCart(Integer id);
	/**
	 * 新增ordercart
	 * @param orderCart
	 */
	void addOrderCart(OrderCart orderCart);
	
	/*************************************订单信息部分***********************************/
	/**
	 * 根据订单号查询订单
	 * @param id
	 * @return
	 */
	OrderInfo findOrderById(String id);
	/**
	 * 根据条件查询相应的订单（比如根据user.id查询用户所有订单且分页显示）
	 * @param order
	 * @return
	 */
	List<OrderInfo> findOrders(OrderInfo order,PageModel pageModel);
	/**
	 * 根据id删除订单
	 * @param id
	 */
	void deleteOrder(String id);
	/**
	 * 更改订单信息（如修改订单状态，收货人电话地址等），管理员操作
	 * @param order
	 */
	void modifyOrder(OrderInfo order);
	/**
	 * 插入/生成订单
	 * @param order
	 */
	void addOrder(OrderInfo order);

}
