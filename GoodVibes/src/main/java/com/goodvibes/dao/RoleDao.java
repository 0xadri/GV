package com.goodvibes.dao;

import com.goodvibes.exception.RoleNotFoundException;
import com.goodvibes.model.RoleEntity;

public interface RoleDao {

	RoleEntity getRoleEntityForRoleName(String role) throws RoleNotFoundException;

	//TODO:	RoleEntity getRoleEntityForRoleId(String role);

	//TODO:	Set<RoleEntity> getCollectionOfUsersWithRoleEntity(RoleEntity role);
	
}
