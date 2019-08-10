package org.jkl.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jkl.crm.dao.provider.GoodDynSqlProvider;
import org.jkl.crm.entity.Good;

import static org.jkl.crm.util.common.CrmConstants.GOODTABLE;;


public interface GoodDao {
	/**
	 * 分页动态查询
	 * @param params
	 * @return
	 */
	@SelectProvider(type=GoodDynSqlProvider.class,method="selectWithParam")
	List<Good> selectByPage(Map<String, Object> params);
	/**
	 * 查询所有
	 * @return
	 */
	@Select("select * from "+GOODTABLE+"")
	List<Good> selectAllGoods();
	/**
	 * 根据购物车号查找
	 * @param id
	 * @return
	 */
	@Select("select * from "+GOODTABLE+" where id=#{id}")
	Good selectById(Integer id);
	/**
	 * 查询 符合条件 的商品数量（用于显示数据条数，制作分页等）
	 * @param params
	 * @return
	 */
	@SelectProvider(type=GoodDynSqlProvider.class,method="count")
	int count(Map<String, Object> params);
	
	/***********************************管理员模式*************************************/
	/**
	 * 新增图书（商品）
	 * @param good
	 */
	@SelectProvider(type=GoodDynSqlProvider.class,method="insertGood")
	void save(Good good);
	/**
	 * 删除图书
	 */
	@Delete("delete from "+GOODTABLE+" where id = #{id}")
	void deleteGood(Integer id);
	/**
	 * 更新图书
	 * @param good
	 */
	@SelectProvider(type=GoodDynSqlProvider.class,method="updateGood")
	void updateGood(Good good);
	
	
}
