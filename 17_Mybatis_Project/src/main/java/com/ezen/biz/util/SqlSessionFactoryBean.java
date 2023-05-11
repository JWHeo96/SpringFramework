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
	 * sql-map-config������ data source ������ �о� �����ͺ��̽��� ���� ��, ���ǰ�ü�� ������
	 */
	public static SqlSession getSessionInstance() {
		return sessionFactory.openSession();
	}
}
