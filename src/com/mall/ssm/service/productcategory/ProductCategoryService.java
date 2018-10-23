package com.mall.ssm.service.productcategory;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.productcategory.ProductCategoryMapper;
import com.mall.ssm.po.custom.ProductCategoryCustom;
import com.mall.ssm.po.vo.ProductCategoryVo;

@Service
public class ProductCategoryService extends CrudService<ProductCategoryMapper, ProductCategoryVo,ProductCategoryCustom>{

}
