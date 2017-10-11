package com.learn.chapter2.mapper;

import com.learn.chapter2.po.Role;

public interface RoleMapper {
	public Role getRole(Long id);
	public int deleteRole(Long id);
	public int insertRole(Role role);
}
