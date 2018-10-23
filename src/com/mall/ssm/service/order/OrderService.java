package com.mall.ssm.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.ssm.mapper.order.OrderMapper;
import com.mall.ssm.po.custom.OrderCustom;
import com.mall.ssm.po.vo.OrderItemVo;
import com.mall.ssm.po.vo.OrderVo;

@Service
public class OrderService{
	
	private OrderMapper dao;
	@Autowired
	public void setDao(OrderMapper dao) {
		this.dao = dao;
	}
	public List<OrderCustom> getOrderByDate(String dateStr){
		return dao.getOrderByDate(dateStr);
	}
	public Long getCount(OrderVo entity){
		return dao.getCount(entity);
	}
	public List<OrderCustom> getOrderListByPage(OrderVo order) {
		return dao.getOrderListByPage(order);
	}

	public OrderCustom getOrderWithItems(String orderNumber) {
		return dao.getOrderWithItems(orderNumber);
	}
	public OrderCustom getOrderSimple(OrderVo order) {
		return dao.getOrderSimple(order);
	}
	public void insertOrderItemBatch(List<OrderItemVo> list) {
		dao.insertOrderItemBatch(list);
	}

	public void insertOrder(OrderVo orderVo) {
		dao.insertOrder(orderVo);
	}

	public void updateOrder(OrderVo orderVo) {
		dao.updateOrder(orderVo);
	}
	
	public void deleteOrder(String orderNumber) {
		dao.deleteOrder(orderNumber);
	}
}
