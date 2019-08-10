package org.jkl.crm.dao.provider;

import static org.jkl.crm.util.common.CrmConstants.ORDERCARTTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.crm.dao.ShoppingCartDao;
import org.jkl.crm.dao.UserDao;
import org.jkl.crm.entity.OrderCart;;

public class OrderCartDynSqlProvider {
	/**
	 * 分页动态查询//上层判断用户是否已经登录，没有则跳转至登录界面
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM " + ORDERCARTTABLE + ")AS T");
				if (params.get("cart") != null) {
					OrderCart cart = (OrderCart) params.get("cart");
					if (cart.getUser().getId() != null && !cart.getUser().getId().equals("") && cart.getUser().getId() != 0) {
						WHERE(" user_id = #{cart.user.id}");
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
	 * 动态查询符合条件的商品数（可扩展，如添加用户状态）
	 * @param params
	 * @return
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(ORDERCARTTABLE);
				if (params.get("cart") != null) {
					OrderCart cart = (OrderCart) params.get("cart");
					if (cart.getUser().getId() != null && !cart.getUser().getId().equals("") && cart.getUser().getId()!=0) {
						WHERE(" user_id = #{cart.user.id} ");
					}
				}
			}
		}.toString();
	}

	/**
	 * 新增商品时调用insert，由前端判断购物车中是否已经存在该商品，如果没有，调用insert，否则调用update
	 * @param cart
	 * @return
	 */
	public String insertCart(OrderCart cart) {
		return new SQL() {
			{
				INSERT_INTO(ORDERCARTTABLE);
				//令购物车关联的user_id = user.id，实现购物车关联
				if (cart.getUser() != null && !cart.getUser().equals("")) {
					VALUES("user_id", "#{user.id}");
				}
				if (cart.getGood() != null && !cart.getGood().equals("")) {
					VALUES("good_id", "#{good.id}");
				}
				if (cart.getCount() != null && !cart.getCount().equals("")) {
					VALUES("count", "#{count}");
				}
			}
		}.toString();
	}

	/**
	 * 用户修改商品数量、、修改至0时由前端判断 调用CartDao中的delete方法
	 * @param cart
	 * @return
	 */
	public String updateCart(OrderCart cart) {//购物车判断更新count（商品数量）是否为0，如果为0，则调用delete
		String sql = new SQL() {
			{
				UPDATE(ORDERCARTTABLE);
				//如果是0,调用delete，前端判断
				if (cart.getCount() != null && !cart.getCount().equals("") && cart.getCount()!= 0) {
					SET("count= #{count}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
		return sql;
	}
}
