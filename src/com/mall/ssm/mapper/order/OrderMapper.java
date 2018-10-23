package com.mall.ssm.mapper.order;

import java.util.List;

import com.mall.ssm.po.custom.OrderCustom;
import com.mall.ssm.po.vo.OrderItemVo;
import com.mall.ssm.po.vo.OrderVo;

/**
 * 订单类操作
 * @author Administrator
 *
 */
public interface OrderMapper{
	List<OrderCustom> getOrderByDate(String dateStr);
	Long getCount(OrderVo entity);
	
	/**
	 * 获得订单的详细信息
	 * @return
	 */
	OrderCustom getOrderWithItems(String orderNumber);
	
	/**
	 * 获得订单列表
	 * @param order
	 * @return
	 */
	List<OrderCustom> getOrderListByPage(OrderVo order);
	/***
	 * 获得订单
	 * @param order
	 * @return
	 */
	public OrderCustom getOrderSimple(OrderVo order);
	/**
	 * 插入订单
	 * @param orderVo
	 */
	public void insertOrder(OrderVo orderVo);
	/**
	 * 插入子订单
	 * @param list
	 */
	public void insertOrderItemBatch(List<OrderItemVo> list);
	
	/**
	 * 更新订单
	 * @param orderVo
	 */
	public void updateOrder(OrderVo orderVo);
	
	/**
	 * 删除订单
	 */
	public void deleteOrder(String orderNumber);
	
	/**
    * 总数
    * @param entity
    * @return
    */
   //Long getCount(Object entity);

   /**
    * 获取单条数据
    * @param entity
    * @return
    */
   //Object get(Object entity);
   
   /**
    * 分页查询
    * @param entity
    * @return
    */
   //List<Object> getListByPage(Object entity);
	
   /**
	 * 获取所有
	 * @param entity
	 * @return
	 */
	//public List<Object> getAllList(Object entity);
	
	/**
	 * 新增
	 * @param entity
	 */
	//public void insert(Object entity);
	
	/**
	 * 修改
	 * @param entity
	 */
	//public void update(Object entity);
	
	/**
	 * 删除
	 * @param entity
	 */
	//public void delete(Integer id);
}
