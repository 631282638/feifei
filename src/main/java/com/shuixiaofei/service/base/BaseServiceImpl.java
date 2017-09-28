package com.shuixiaofei.service.base;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
/**
 * 20170807
 * 分页查询
 * @author xu
 *
 */
public class BaseServiceImpl implements BaseService{
	@Autowired
	private org.mybatis.spring.SqlSessionFactoryBean  sqlSessionFactory;
	
	public List<?> getListByPages(Class<?> mapperClass, String sqlId,
			Object sqlParameter, int pageIndex, int pageSize) {
		SqlSession session = null;
		try {
			SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
			session = SqlSessionUtils.getSqlSession(sessionFactory);
			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			if (pageSize <= 0) {
				pageSize = 10;
			}
			PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
			return session.selectList(mapperClass.getName() + "." + sqlId,
					sqlParameter, pageBounds);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

}
