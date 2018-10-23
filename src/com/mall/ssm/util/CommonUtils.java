package com.mall.ssm.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {
	/**
	 * 返回一个不重复的字符串
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 将Bean中的属性取出
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> describe(Object bean){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//获得Bean的描述
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			//获得属性的描述
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			//遍历所有属性
			for (PropertyDescriptor descriptor : descriptors) {
				//属性名称
				String fieldName = descriptor.getName();
				//读取属性的方法
				Method getter = descriptor.getReadMethod();
				Object fieldValue = getter.invoke(bean, new Object[]{});
				if(!fieldName.equalsIgnoreCase("class")){
					map.put(fieldName, fieldValue);
				}
			}
		} catch (Exception e) {
			System.err.println("describe---"+e);
		}
		return map;
	}
	/**
	 * 将Map中的key-value赋值到Bean中
	 * @param bean
	 * @param properties
	 */
	public static void populate(Object bean,Map<String, Object> properties){
		try {
			//获得Bean的描述
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			//获得属性的描述
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			//遍历所有属性
			for (PropertyDescriptor descriptor : descriptors) {
				//属性名称
				String fieldName = descriptor.getName();
				if(properties.containsKey(fieldName)){
					//得到写属性的方法
					Method setter = descriptor.getWriteMethod();
					setter.setAccessible(true);
					setter.invoke(bean, new Object[]{properties.get(fieldName)});
				}
			}
		} catch (Exception e) {
			System.err.println("populate---"+e);
		}
	}
	
	
}
