package com.examples.flywithus.dao;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.examples.flywithus.entity.UserInfo;


@Repository
@Transactional(readOnly = true)
public class UserInfoDAO implements IUserInfoDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public UserInfo getActiveUser(String userName) {
		UserInfo activeUserInfo = new UserInfo();
		short enabled = 1;
	    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInfo.class);
	    detachedCriteria.add(Restrictions.and(Restrictions.eq("userName", userName), Restrictions.eq("enabled", enabled)));
	    
		List<?> list = hibernateTemplate.findByCriteria(detachedCriteria);
		if(!list.isEmpty()) {
			activeUserInfo = (UserInfo)list.get(0);
		}
		return activeUserInfo;
	}
}