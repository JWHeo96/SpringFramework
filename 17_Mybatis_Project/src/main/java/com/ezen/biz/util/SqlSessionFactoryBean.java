package com.ezen.biz.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {

	private static SqlSessionFactory sessionFactory = null;
	static {
		if (sessionFactory == null) {
			try {
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * sql-map-config파일의 data source 설정을 읽어 데이터베이스에 연결 후, 세션객체를 리턴함
	 */
	public static SqlSession getSessionInstance() {
		return sessionFactory.openSession();
	}
}
