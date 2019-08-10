package org.jkl.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.jkl.crm.dao.provider.OrderDynSqlProvider;
import org.jkl.crm.entity.OrderInfo;

import static org.jkl.crm.util.common.CrmConstants.ORDERTABLE;

public interface OrderDao {
	/**
	 * 分页显示用户历史订单
	 * @param params
	 * @return
	 */
	@SelectProvider(type=OrderDynSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="USER_ID",property="user",
		one=@One(select="org.jkl.crm.dao.UserDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="good_id",property="good",
		one=@One(select="org.jkl.crm.dao.GoodDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="dealtime",property="dealTime",javaType=java.util.Date.class),
	})
	List<OrderInfo> selectByPage(Map< String, Object> params);
	/**
	 * 根据id查询历史订单
	 * @param id
	 * @return
	 */
	@Select("select * from "+ORDERTABLE+" where ID = #{id}")
	OrderInfo selectById(String id);
	
	
	@SelectProvider(type=OrderDynSqlProvider.class,method="count")
	int count(Map< String, Object> params);
	/**
	 * 删除历史订单记录
	 * @param id
	 */
	@Delete("delete from "+ORDERTABLE+" where id = #{id}")
	void deleteOrder(String id);
	/**
	 * 插入订单
	 * @param params
	 */
	@SelectProvider(type=OrderDynSqlProvider.class,method="insertOrderInfo")
	void save(OrderInfo order);
	/**
	 * 修改订单信息（用于后续完善，如联系客服修改配送信息）
	 * @param orderInfo
	 */
	@SelectProvider(type=OrderDynSqlProvider.class,method="updateOrderInfo")
	void updateOrderInfo(OrderInfo orderInfo);
	
}
