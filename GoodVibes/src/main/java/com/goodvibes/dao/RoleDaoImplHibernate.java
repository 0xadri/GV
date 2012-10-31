package com.goodvibes.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.goodvibes.exception.RoleNotFoundException;
import com.goodvibes.model.RoleEntity;


@Repository
public class RoleDaoImplHibernate implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public RoleEntity getRoleEntityForRoleName(String roleName) throws RoleNotFoundException {

		String hql = "FROM RoleEntity WHERE roleName = '" + roleName + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		RoleEntity role = (RoleEntity) query.uniqueResult();
		
		if (role == null){
			throw new RoleNotFoundException("role not found for roleName: " + roleName);
		}
		
		return role;
	}

	
}
