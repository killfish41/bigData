package test.Dao;

import test.mybatis.User;

import java.util.List;

public interface IUserDao {
    
    List<User> findAll();
}
