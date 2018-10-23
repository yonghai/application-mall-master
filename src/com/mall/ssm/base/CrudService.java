package com.mall.ssm.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.ssm.po.dto.PageDto;

/**
 * Service基类
 * @author Administrator
 *
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends BaseDao<T,TR>, T extends PageDto,TR> {
	
	
	public D dao;
	@Autowired
	public void setDao(D dao) {
		this.dao = dao;
	}
	/**
	 * 获取总数
	 * @param entity
	 * @return
	 */
	public Long getCount(T entity) {
		return dao.getCount(entity);
	}
	/**
	 * 分页获取
	 * @param entity
	 * @return
	 */
	public List<TR> getListByPage(T entity) {
		return dao.getListByPage(entity);
	}

	/**
	 * 获取所有
	 * @param entity
	 * @return
	 */
	public List<TR> getAllList(T entity) {
		return dao.getAllList(entity);
	}
	
	/**
	 * 插入
	 * @param entity
	 */
	public void insert(T entity) {
		dao.insert(entity);
	}

	/**
	 * 更新
	 * @param entity
	 */
	public void update(T entity) {
		dao.update(entity);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id) {
		dao.delete(id);
	}

	/**
	 * 保存或者更新
	 * @param entity
	 */
	public void save(T entity) {
		if(entity.getId() == 0){
			dao.insert(entity);
		}else{
			dao.update(entity);
		}
	}

	/**
	 * 获取单个
	 * @param entity
	 * @return
	 */
	public TR get(T entity) {
		return dao.get(entity);
	}
}
