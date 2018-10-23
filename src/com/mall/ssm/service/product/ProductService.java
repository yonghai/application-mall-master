package com.mall.ssm.service.product;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.product.ProductMapper;
import com.mall.ssm.po.custom.ProductCustom;
import com.mall.ssm.po.vo.ProductVo;

@Service
public class ProductService extends CrudService<ProductMapper, ProductVo,ProductCustom>{

}
