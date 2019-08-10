package org.jkl.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jkl.crm.dao.GoodDao;
import org.jkl.crm.dao.OrderCartDao;
import org.jkl.crm.dao.OrderDao;
import org.jkl.crm.dao.ShoppingCartDao;
import org.jkl.crm.dao.UserDao;
import org.jkl.crm.entity.Good;
import org.jkl.crm.entity.OrderCart;
import org.jkl.crm.entity.OrderInfo;
import org.jkl.crm.entity.ShoppingCart;
import org.jkl.crm.entity.User;
import org.jkl.crm.service.CrmService;
import org.jkl.crm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**@Transactional只有readonly="false",才可以增删改，如下**/
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service
public class CrmServiceImpl implements CrmService{
	/**
	 * 自动注入持久层对象
	 */
	@Autowired
	private UserDao userDao;
	@Autowired
	private GoodDao goodDao;
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	@Autowired
	private OrderCartDao orderCartDao;
	@Autowired
	private OrderDao orderDao;
	
	
	
	/*******************************UserService************************************/
	/**
	 * CrmServiceImpl 接口login方法实现
	 * @see org.jkl.crm.service.CrmService#login(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public User login(String name, String password) {
		System.out.println("CrmServiceImpl login  ======>>>");
		return userDao.selectByLoginnameAndPassword(name, password);
	}
	/**
	 * CrmServiceImpl 接口findUserById方法实现
	 * @see org.jkl.crm.service.CrmService#findUserById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectUserById(id);
	}
	/**
	 * @see org.jkl.crm.service.CrmService#findUserByName(java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUserByName(String name) {
		return userDao.selectUserByName(name);
	}
	/**
	 * @see org.jkl.crm.service.CrmService#isExist(java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public boolean isExist(String name) {
		return userDao.isExist(name);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.crm.service.CrmService#findUsers()
	 */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUsers(User user,PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("user", user);
		/**
		 * 获取总数据条数
		 */
		int recordCount = userDao.count(params);
		System.out.println("recordCount ====>> " + recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<User> users = userDao.selectByPage(params);
		return users;
	}
	/**
	 * 删除用户实现
	 * @see org.jkl.crm.service.CrmService#removeUserById(java.lang.Integer)
	 */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteUserById(id);
	}
	/**
	 * 用户修改实现
	 * @see org.jkl.crm.service.CrmService#modifyUser(org.jkl.crm.entity.User)
	 */
	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
		
	}
	/**
	 * 用户注册实现
	 * @see org.jkl.crm.service.CrmService#addUser(org.jkl.crm.entity.User)
	 */
	@Override
	public void addUser(User user) {
		userDao.save(user);
	}
	
	
	
	
	/*******************************GoodService************************************/
	/**
	 * 根据id查询商品实现
	 * @see org.jkl.crm.service.CrmService#findGoodById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public Good findGoodById(Integer id) {
		return goodDao.selectById(id);
	}
	/**
	 * 分页显示商品实现
	 * @see org.jkl.crm.service.CrmService#findGoods(org.jkl.crm.entity.Good)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Good> findGoods(Good good,PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("good", good);
		/**
		 * 获取商品总数，用于分页处理
		 */
		int recordCount = goodDao.count(params);
		pageModel.setRecordCount(recordCount);
		System.out.println("recordCount ===>> "+recordCount);
		if(recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<Good> goods = goodDao.selectByPage(params);
		return goods;
	}
	/**
	 * 修改商品信息实现
	 * @see org.jkl.crm.service.CrmService#modifyGood(org.jkl.crm.entity.Good)
	 */
	@Override
	public void modifyGood(Good good) {
		goodDao.updateGood(good);
	}
	/**
	 * 添加商品实现
	 * @see org.jkl.crm.service.CrmService#addGood(org.jkl.crm.entity.Good)
	 */
	@Override
	public void addGood(Good good) {
		goodDao.save(good);
	}
	/**
	 * 删除商品实现
	 * @see org.jkl.crm.service.CrmService#deleteGood(org.jkl.crm.entity.Good)
	 */
	@Override
	public void deleteGood(Integer id) {
		goodDao.deleteGood(id);
	}
	
	
	
	
	
	
	/*******************************shoppingCartDao**********************************/
	/**
	 * 根据id查询购物车，好像并无甚卵用，多写了。。。
	 * @see org.jkl.crm.service.CrmService#findCartById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public ShoppingCart findCartById(Integer id) {
		return shoppingCartDao.selectCartById(id);
	}
	/**
	 * 用户查询购物车实现
	 * @see org.jkl.crm.service.CrmService#findCarts(org.jkl.crm.entity.ShoppingCart, org.jkl.crm.util.tag.PageModel)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShoppingCart> findCarts(ShoppingCart shoppingCart,PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("shoppingCart", shoppingCart);
		/**
		 * 获取符合条件的购物车数据总条数
		 */
		int recordCount = shoppingCartDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<ShoppingCart> shoppingCarts = shoppingCartDao.selectByPage(params);
		return shoppingCarts;
	}
	/**
	 * 用户修改购物车数据（后续完善时管理员禁用）
	 * @see org.jkl.crm.service.CrmService#modifyCart(org.jkl.crm.entity.ShoppingCart)
	 */
	@Override
	public void modifyCart(ShoppingCart shoppingCart) {
		shoppingCartDao.updateCart(shoppingCart);
	}
	/**
	 * 删除购物车数据实现
	 * @see org.jkl.crm.service.CrmService#deleteCarts(java.lang.Integer)
	 */
	@Override
	public void deleteCarts(Integer id) {
		shoppingCartDao.delete(id);
	}
	/**
	 * 新增购物车实现
	 * @see org.jkl.crm.service.CrmService#addOrderCart(org.jkl.crm.entity.ShoppingCart)
	 */
	@Override
	public void addOrderCart(ShoppingCart shoppingCart) {
		shoppingCartDao.save(shoppingCart);
	}
	
	
	
	
	/*****************************orderCartDao实现***********************************/
	/**************ordercart与orderinfo密切关联，两者联查得到完整的订单信息*******************/
	/************用户不需要知道这一层，用于实现购买，以及历史订单联查，区别于购物车******************/
	/**
	 * 通过id找ordercart
	 * @see org.jkl.crm.service.CrmService#findOrderCartById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public OrderCart findOrderCartById(Integer id) {
		return orderCartDao.selectById(id);
	}
	/**
	 * 查询购买记录，并分页显示
	 * @see org.jkl.crm.service.CrmService#findOrderCarts(org.jkl.crm.entity.OrderCart)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<OrderCart> findOrderCarts(OrderCart orderCart,PageModel pageModel ) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderCart", orderCart);
		
		int recordCount = orderCartDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0) {
			params.put("pageModel", pageModel);
		}
		List<OrderCart> orderCarts = orderCartDao.selectByPage(params);
		return orderCarts;
	}
	/**
	 * 修改下单的数据，好像更没有什么卵用，马丹又多写了。（注：配送信息在orderinfo里）
	 * 应该要禁止修改，可以不写，删除可以，但不可以修改。-----------------------
	 * @see org.jkl.crm.service.CrmService#modifyOrderCart(org.jkl.crm.entity.OrderCart)
	 */
	@Override
	public void modifyOrderCart(OrderCart orderCart) {
		orderCartDao.updateCart(orderCart);
	}
	/**
	 * 删除订单实现一
	 * @see org.jkl.crm.service.CrmService#deleteOrderCart(java.lang.Integer)
	 */
	@Override
	public void deleteOrderCart(Integer id) {
		orderCartDao.delete(id);
	}
	/**
	 * 购买下单添加数据到ordercart表（含直接下单，或者购物车下单） 
	 * @see org.jkl.crm.service.CrmService#addOrderCart(org.jkl.crm.entity.OrderCart)
	 */
	@Override
	public void addOrderCart(OrderCart orderCart) {
		orderCartDao.save(orderCart);
	}
	
	
	
	
	/***********************************orderDao实现*********************************/
	/*************存放订单号、配送信息，与ordercart实现联查可以得到完整的订单信息****************/
	/*****可以考虑在查询订单的时候先加载orderinfo中的数据，延时加载ordercart中具体的商品信息*******/
	
	/**
	 * 根据id查询订单信息
	 * @see org.jkl.crm.service.CrmService#findOrderById(java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public OrderInfo findOrderById(String id) {//String id
		return orderDao.selectById(id);
	}
	/**
	 * 动态查询历史订单，并分页显示
	 * @see org.jkl.crm.service.CrmService#findOrders(org.jkl.crm.entity.OrderInfo, org.jkl.crm.util.tag.PageModel)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<OrderInfo> findOrders(OrderInfo order,PageModel pageModel) {
		Map<String, Object> params = new HashMap<>();
		params.put("order", order);
		int recordCount = orderDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0 ) {
			params.put("order", order);
		}
		List<OrderInfo> orderInfos = orderDao.selectByPage(params);
		return orderInfos;
	}
	/**
	 * 删除订单
	 * @see org.jkl.crm.service.CrmService#deleteOrder(java.lang.String)
	 */
	@Override
	public void deleteOrder(String id) {
		orderDao.deleteOrder(id);
	}
	/**
	 * 修改的是配送信息，这里真不是多余的
	 * @see org.jkl.crm.service.CrmService#modifyOrder(org.jkl.crm.entity.OrderInfo)
	 */
	@Override
	public void modifyOrder(OrderInfo order) {
		orderDao.updateOrderInfo(order);
	}
	/**
	 * 插入/生成 订单
	 * @see org.jkl.crm.service.CrmService#addOrder(java.util.Map)
	 */
	@Override
	public void addOrder(OrderInfo order) {
		orderDao.save(order);
	}
	
}
