package com.mall.ssm.service.product;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.product.ProductDetailMapper;
import com.mall.ssm.po.custom.ProductDetailCustom;
import com.mall.ssm.po.vo.ProductDetailVo;

@Service
public class ProductDetailService extends CrudService<ProductDetailMapper, ProductDetailVo,ProductDetailCustom>{

}
