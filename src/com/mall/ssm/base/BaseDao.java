package com.mall.ssm.base;

import java.util.List;

/**
 * DAO支持类实现
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDao<T,TR> {
	
	
	/**
    * 总数
    * @param entity
    * @return
    */
   Long getCount(T entity);

   /**
    * 获取单条数据
    * @param entity
    * @return
    */
   TR get(T entity);
   
   /**
    * 分页查询
    * @param entity
    * @return
    */
   List<TR> getListByPage(T entity);
	
   /**
	 * 获取所有
	 * @param entity
	 * @return
	 */
	public List<TR> getAllList(T entity);
	
	/**
	 * 新增
	 * @param entity
	 */
	public void insert(T entity);
	
	/**
	 * 修改
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 删除
	 * @param entity
	 */
	public void delete(Integer id);
	
}
