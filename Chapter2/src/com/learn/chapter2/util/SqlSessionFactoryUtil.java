package com.learn.chapter2.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	private static SqlSessionFactory sqlsessionFactory=null;
	//类线程锁
	private static final Class CLASS_LOCK=SqlSessionFactoryUtil.class;
	
	private SqlSessionFactoryUtil(){}
	
	public static SqlSessionFactory initSqlSessionFactory(){
		String resource="mybatis-config.xml";
		InputStream inputStream=null;
		try {
			inputStream=Resources.getResourceAsStream(resource);
		} catch (IOException ex) {
			// TODO: handle exception
			Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE,null,ex);
		}
		synchronized (CLASS_LOCK) {
			if(sqlsessionFactory==null){
				sqlsessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			}
		}
		return sqlsessionFactory;
	}
	
	public static SqlSession openSqlSession(){
		if(sqlsessionFactory==null){
			initSqlSessionFactory();
		}
		return sqlsessionFactory.openSession();
	}
	
}
