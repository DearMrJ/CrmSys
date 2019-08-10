package org.jkl.crm.dao.provider;

import static org.jkl.crm.util.common.CrmConstants.GOODTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.crm.entity.Good;

public class GoodDynSqlProvider {
	/**
	 * 分页动态查询(分类、作者、名字)
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM " + GOODTABLE + ")AS T");
				if (params.get("good") != null) {
					Good good = (Good) params.get("good");
					if (good.getName() != null && !good.getName().equals("")) {
						WHERE(" name LIKE '%'+#{good.name}+'%'");
					}
					if (good.getDescription() != null && !good.getDescription().equals("")) {
						WHERE(" description LIKE '%'+#{good.description}+'%'");
					}
					if (good.getAuthor() != null && !good.getAuthor().equals("")) {
						WHERE(" author LIKE '%'+#{good.author}+'%'");
					}
					if (good.getType() != null && !good.getType().equals("")) {
						WHERE(" type LIKE '%'+#{good.type}+'%'");
					}
					if (good.getId()!=null && !good.getId().equals("") && good.getId()!=0) {
						WHERE(" id LIKE '%'+#{good.id}+'%'");
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
	 * 动态查询符合条件的商品数
	 * @param params
	 * @return
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(GOODTABLE);
				if (params.get("good") != null) {
					Good good = (Good) params.get("good");
					if (good.getName() != null && !good.getName().equals("")) {
						WHERE(" name LIKE '%'+#{good.name}+'%'");
					}
					if (good.getDescription() != null && !good.getDescription().equals("")) {
						WHERE(" description LIKE '%'+#{good.description}+'%'");
					}
					if (good.getAuthor() != null && !good.getAuthor().equals("")) {
						WHERE(" author LIKE '%'+#{good.author}+'%'");
					}
					if (good.getType() != null && !good.getType().equals("")) {
						WHERE(" type LIKE '%'+#{good.type}+'%'");
					}
				}
			}
		}.toString();
	}

	/**
	 * 插入商品
	 * @param good
	 * @return
	 */
	public String insertGood(Good good) {
		return new SQL() {
			{
				INSERT_INTO(GOODTABLE);
				if (good.getName() != null && !good.getName().equals("")) {
					VALUES("name", "#{name}");
				}
				if (good.getPrice() != 0) {
					VALUES("price", "#{price}");
				}
				if (good.getAuthor() != null && !good.getAuthor().equals("")) {
					VALUES("author", "#{author}");
				}
				if (good.getImagePath() != null && !good.getImagePath().equals("")) {
					VALUES("imagepath", "#{imagePath}");
				}
				if (good.getStock() != null && !good.getStock().equals("")) {
					VALUES("stock", "#{stock}");
				}
				if (good.getType() != null && !good.getType().equals("")) {
					VALUES("type", "#{type}");
				}
				
			}
		}.toString();
	}

	/**
	 * 用户修改用户名密码邮箱
	 * 
	 * @param good
	 * @return
	 */
	public String updateGood(Good good) {
		String sql = new SQL() {
			{
				UPDATE(GOODTABLE);
				if (good.getName() != null && !good.getName().equals("")) {
					SET("name= #{name}");
				}
				if (good.getAuthor() != null && !good.getAuthor().equals("")) {
					SET("author= #{author}");
				}
				if (good.getImagePath() != null && !good.getImagePath().equals("")) {
					SET("imagepath= #{imagePath}");
				}
				if (good.getStock() != null && !good.getStock().equals("")) {
					SET("stock= #{stock}");
				}
				if (good.getType() != null && !good.getType().equals("")) {
					SET("type= #{type}");
				}
				if (good.getPrice()!= 0) {
					SET("price= #{price}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
		return sql;
	}
}
