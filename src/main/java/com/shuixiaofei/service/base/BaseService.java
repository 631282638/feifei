package com.shuixiaofei.service.base;

import java.util.List;
/**
 * 20170807
 * @author xu
 *
 */
public interface BaseService {

	/**
	 * 按照条件进行分页查询
	 * @param mapperClass
	 * @param sqlId
	 * @param sqlParameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<?> getListByPages(Class<?> mapperClass, String sqlId,
			            Object sqlParameter, int pageIndex, int pageSize);
}