package com.examples.flywithus.dao;
import com.examples.flywithus.entity.UserInfo;
public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
}