package org.jkl.crm.dao;

import static org.jkl.crm.util.common.CrmConstants.USERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jkl.crm.dao.provider.UserDynSqlProvider;
import org.jkl.crm.entity.User;

public interface UserDao {
	/**
	 * 根据登录名和密码查询用户
	 * @param name
	 * @param password
	 * @return
	 */
	@Select("select * from "+USERTABLE+" where name = #{name} and password = #{password}")
	User selectByLoginnameAndPassword(
			@Param("name") String name,
			@Param("password") String password);
	
	
	/**
	 * 根据用户名查询（可用于显示当前用户 或 管理员查询）
	 * @param name
	 * @return
	 */
	@Select("select * from "+USERTABLE+" where name = #{name}")
	List<User> selectUserByName(String name);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Select("select * from "+USERTABLE+" where id = #{id}")
	User selectUserById(Integer id);
	/**
	 * 用户修改信息
	 * @param user
	 */
	@SelectProvider(type=UserDynSqlProvider.class,method="updateUser")
	void updateUser(User user);
	/**
	 * 新增用户
	 * @param user
	 */
	@SelectProvider(type=UserDynSqlProvider.class,method="insertUser")
	void save(User user);
	/**
	 * 查询用户是否存在，用于用户注册时确定库中是否存在用户名相同的数据，后续完善可以新增字段：昵称
	 * @param name
	 * @return
	 */
	@Select("select count(*) from "+USERTABLE+" where name = #{name}")
	boolean isExist(String name);
	
	/***********************************后续完善************************************/
	/**
	 * 管理员操作：查询所有
	 * @return
	 */
	@Select("select * from user "+USERTABLE+"")
	List<User> selectAllUsers() ;
	/**
	 * 管理员操作：删除用户
	 * @param id
	 */
	@Delete("delete from "+USERTABLE+" where id = #{id}")
	void deleteUserById(int id);
	/**
	 * 管理员操作：分页动态查询用户信息
	 * @return
	 */
	@SelectProvider(type=UserDynSqlProvider.class,method="selectWithParam")
	List<User> selectByPage(Map<String, Object> params);
	/**
	 * 管理员操作：查询符合条件的人数
	 * @return
	 */
	@SelectProvider(type=UserDynSqlProvider.class,method="count")
	int count(Map<String, Object> params);
	
}
