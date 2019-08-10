package org.jkl.crm.dao;

import static org.jkl.crm.util.common.CrmConstants.ORDERCARTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.jkl.crm.dao.provider.OrderCartDynSqlProvider;
import org.jkl.crm.entity.OrderCart;
import org.jkl.crm.entity.OrderCart;


public interface OrderCartDao {
	/**
	 * 动态查询用户购物车数据
	 * @param params
	 * @return
	 */
	@SelectProvider(type=OrderCartDynSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="USER_ID",property="user",
			one=@One(select="org.jkl.crm.dao.UserDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="good_id",property="good",
			one=@One(select="org.jkl.crm.dao.GoodDao.selectById",fetchType=FetchType.EAGER))
	})
	List<OrderCart> selectByPage(Map<String, Object> params);
	
	@Select("select price from "+ORDERCARTTABLE+" where id = #{id}")
	OrderCart selectById(Integer id);
	/**
	 * 查询 <单样> 商品总价格,用于购物车单样总价显示
	 * @param id
	 * @return
	 */
	@Select("select price from "+ORDERCARTTABLE+" where good_id = #{id}")
	OrderCart selectSinglePrice(Integer id);
	/**
	 * 查询 符合条件的 总数
	 * @param params
	 * @return
	 */
	@SelectProvider(type=OrderCartDynSqlProvider.class,method="count")
	int count(Map<String, Object> params);
	/**
	 * 新增购物车。。需要根据判断购物车是否已经存在该商品来选择插入或者更新
	 * @param id
	 * @return
	 */
	@SelectProvider(type=OrderCartDynSqlProvider.class,method="insertCart")
	void save(OrderCart cart);
	/**
	 * 更新购物车。。需要根据判断购物车是否已经存在该商品来选择插入或者更新
	 * //购物车判断更新count（商品数量）是否为0，如果为0，则调用delete
	 * @param good
	 */
	@SelectProvider(type=OrderCartDynSqlProvider.class,method="updateCart")
	void updateCart(OrderCart cart);
	/**
	 * 根据商品id删除商品。//购物车判断更新count（商品数量）是否为0，如果为0，则调用delete
	 * @param id
	 */
	@Delete("delete from "+ORDERCARTTABLE+" where id=#{id}")
	void delete(Integer id);
}
