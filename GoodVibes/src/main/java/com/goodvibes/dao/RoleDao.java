package com.goodvibes.dao;

import com.goodvibes.exception.RoleNotFoundException;
import com.goodvibes.model.RoleEntity;


public interface RoleDao {
	
	RoleEntity saveRole(RoleEntity role);

	RoleEntity getRoleEntityForRoleName(String role) throws RoleNotFoundException;

	RoleEntity getRoleEntityForRoleId(int Id) throws RoleNotFoundException;
	
}
