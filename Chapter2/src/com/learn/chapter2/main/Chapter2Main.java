package com.learn.chapter2.main;

import org.apache.ibatis.session.SqlSession;

import com.learn.chapter2.mapper.RoleMapper;
import com.learn.chapter2.po.Role;
import com.learn.chapter2.util.SqlSessionFactoryUtil;

public class Chapter2Main {
	public static void main(String[] args) {
		SqlSession sqlSession=null;
		try {
			sqlSession=SqlSessionFactoryUtil.openSqlSession();
			RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
			Role role=new Role();
			role.setRoleName("testName");
			role.setNote("testNote");
			roleMapper.insertRole(role);
			roleMapper.deleteRole(1L);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			sqlSession.rollback();
		}finally {
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
}
