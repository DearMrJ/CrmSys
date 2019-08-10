package org.jkl.crm.dao.provider;

import static org.jkl.crm.util.common.CrmConstants.ORDERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.crm.entity.OrderInfo;

public class OrderDynSqlProvider {
	/**
	 * 分页动态查询历史订单
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM " + ORDERTABLE + ")AS T");
				if (params.get("order") != null) {
					OrderInfo order = (OrderInfo) params.get("order");
					if (order.getUser().getId() != null && !order.getUser().getId().equals("") && order.getUser().getId() != 0 ) {
						WHERE(" USER_ID = #{order.user.id} ");
					}
					// 分页
					if (params.get("pageModel") != null) {
						WHERE(" RowId between #{pageModel.firstLimitParam} and #{pageModel.firstLimitParam}+#{pageModel.pageSize}-1");
					}
				}
			}
		}.toString();
		return sql;
	}
	/**
	 * 动态查询当前用户的历史订单数
	 * @param params
	 * @return
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(ORDERTABLE);
				if (params.get("order") != null) {
					OrderInfo order = (OrderInfo) params.get("order");
					if (order.getUser().getId() != null && !order.getUser().getId().equals("") && order.getUser().getId() != 0 ) {
						WHERE(" USER_ID = #{order.user.id} ");
					}
				}
			}
		}.toString();
	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	public String insertOrderInfo(OrderInfo order) {
		return new SQL() {
			{
				INSERT_INTO(ORDERTABLE);
				if (order.getId() != null && !order.getId().equals("")) {
					VALUES("id", "#{order.id}");
				}
				if (order.getCartId() != null && !order.getCartId().equals("")) {
					VALUES("cart_id", "#{order.cart.id}");
				}
				if (order.getUser().getId() != null && !order.getUser().getId().equals("") && order.getUser().getId() !=0) {
					VALUES("USER_ID", "#{order.user.id}");
				}
				//解决一个订单，多个商品的问题
				if (order.getGood().getId() != null && !order.getGood().getId().equals("") && order.getGood().getId() !=0) {
					VALUES("good_id", "#{order.good.id}");
				}
				if (order.getAddress() != null && !order.getAddress().equals("")) {
					VALUES("address", "#{order.address}");
				}
				if (order.getDealTime()!= null && !order.getDealTime().equals("")) {
					VALUES("dealtime", "#{order.dealtime}");
				}
				if (order.getTel() != null && !order.getTel().equals("")) {
					VALUES("address", "#{order.address}");
				}
				if (order.getPrice() != 0) {
					VALUES("price", "#{order.price}");
				}
				if (order.getTel() != null && !order.getTel().equals("")) {
					VALUES("tel", "#{order.tel}");
				}
			}
		}.toString();
	}
	
	/**
	 * 修改订单信息，不过限定只能修改配送信息（姓名、地址、电话）
	 * @param order
	 * @return
	 */
	public String updateOrderInfo(OrderInfo order) {
		String sql = new SQL() {
			{
				UPDATE(ORDERTABLE);
				if(order.getAddress() != null && !order.getAddress().equals("")) {
					SET("address","#{order.address}");
				}
				if(order.getName() != null && !order.getName().equals("")) {
					SET("name","#{order.name}");
				}
				if(order.getTel() != null && !order.getTel().equals("")) {
					SET("tel","#{order.tel}");
				}
			}
		}.toString();
		return sql;
	}
}
