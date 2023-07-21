package com.example.user_role.service;

import com.example.user_role.model.User;
import com.example.user_role.model.UserRole;
import com.example.user_role.repo.UserRoleRepoI;
import com.example.user_role.repo.UserRoleRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class UserRoleServiceImpl implements UserRoleServiceI {
    UserRoleRepoI repo = new UserRoleRepoImpl();

    @Override
    public List<UserRole> findAll() {
        return repo.findAll();
    }

    @Override
    public UserRole findById(int id) {
        return repo.findById(id);
    }

    @Override
    public boolean add(User user) throws SQLException {
        return repo.add(user);
    }

    @Override
    public boolean remove(int id) throws SQLException {
        return repo.remove(id);
    }

    @Override
    public boolean update(User user) throws SQLException {
        return repo.update(user);
    }

    @Override
    public List<UserRole> showBy(String code, String startDate, String role_name) {
        return repo.showBy(code,startDate,role_name);
    }


}
