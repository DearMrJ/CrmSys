package org.jkl.crm.dao.provider;

import static org.jkl.crm.util.common.CrmConstants.USERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.crm.entity.User;

public class UserDynSqlProvider {
	/**
	 * 分页动态查询
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM " + USERTABLE + ")AS T");
				if (params.get("user") != null) {
					User user = (User) params.get("user");
					if (user.getName() != null && !user.getName().equals("")) {
						WHERE(" name LIKE '%'+#{user.name}+'%'");// mysql的concat中+连接,SqlServer“,”连接
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
	 * 动态查询符合条件的人数（可扩展，如添加用户状态）
	 * @param params
	 * @return
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if (params.get("user") != null) {
					User user = (User) params.get("user");
					if (user.getName() != null && !user.getName().equals("")) {
						WHERE(" name LIKE '%'+#{user.name}+'%'");
					}
				}
			}
		}.toString();
	}

	/**
	 * 插入用户（用户注册 或 管理员增加用户）
	 * @param user
	 * @return
	 */
	public String insertUser(User user) {
		return new SQL() {
			{
				INSERT_INTO(USERTABLE);
				if (user.getName() != null && !user.getName().equals("")) {
					VALUES("name", "#{name}");
				}
				if (user.getPassword() != null && !user.getPassword().equals("")) {
					VALUES("password", "#{password}");
				}
				if (user.getEmail() != null && !user.getEmail().equals("")) {
					VALUES("email", "#{email}");
				}
			}
		}.toString();
	}

	/**
	 * 用户修改用户名密码邮箱
	 * 
	 * @param user
	 * @return
	 */
	public String updateUser(User user) {
		String sql = new SQL() {
			{
				UPDATE(USERTABLE);
				if (user.getName() != null && !user.getName().equals("")) {
					SET("name= #{name}");
				}
				if (user.getPassword() != null && !user.getPassword().equals("")) {
					SET("password= #{password}");
				}
				if (user.getEmail() != null && !user.getEmail().equals("")) {
					SET("email= #{email}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
		return sql;
	}
}
