package com.example.user_role.repo;

import com.example.user_role.model.Role;
import com.example.user_role.model.User;
import com.example.user_role.model.UserRole;
import com.example.user_role.util.Constants;
import com.example.user_role.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleRepoImpl implements UserRoleRepoI {

    @Override
    public List<UserRole> findAll() {
        List<UserRole> userRoles = new ArrayList<>();
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.FIND_ALL_USER_ROLE)
        ) {
            ResultSet resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String fullName = resultSet.getString("full_Name");
                String code = resultSet.getString("code");
                String birth = resultSet.getString("birth");
                String startDate = resultSet.getString("start_date");
                Role role = new Role();
                role.setRoleName(resultSet.getString("role_name"));
                user = new User(idUser, fullName, code, birth, startDate, null);
                userRoles.add(new UserRole(user, role));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userRoles;
    }

    @Override
    public UserRole findById(int id) {
        UserRole userRole = null;
        PreparedStatement statement = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(Constants.FIND_BY_ID_USER_ROLE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                String fullName = resultSet.getString("full_Name");
                String code = resultSet.getString("code");
                String birth = resultSet.getString("birth");
                String startDate = resultSet.getString("start_date");
                Role role = new Role();
                String[] roles = resultSet.getString("id_role").split(" ");
                role.setIdRole(Integer.parseInt(roles[0]));
                role.setRoleName(resultSet.getString("role_name"));

                user = new User(id, fullName, code, birth, startDate, null);
                userRole = new UserRole(user, role);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userRole;
    }

//    private List<Role> findRolesBysUser(int userId) {
//
//    }

    @Override
    public boolean add(User user) throws SQLException {
        PreparedStatement statement = null;
        try (Connection connection = DatabaseConnection.getConnection();
        ) {

            statement = connection.prepareStatement(Constants.ADD_USER);
            //insert user
            statement.setInt(1, user.getIdUser());
            statement.setString(2, user.getFullName());
            statement.setString(3, user.getCode());
            statement.setString(4, user.getBirth());
            statement.setString(5, user.getStartDate());
            statement.executeUpdate();
            //insert role
            for (Role role : user.getRoles()) {
                statement = connection.prepareStatement(Constants.ADD_USER_ROLE);
                statement.setInt(1, user.getIdUser());
                statement.setInt(2, role.getIdRole());
                statement.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            statement.close();
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        PreparedStatement statement = null;
        try (Connection connection = DatabaseConnection.getConnection();
        ) {
            statement = connection.prepareStatement(Constants.DELETE_USER_ROLE);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement = connection.prepareStatement(Constants.DELETE_USER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        PreparedStatement statement = null;
        try (Connection connection = DatabaseConnection.getConnection();
        ) {

            statement = connection.prepareStatement(Constants.UPDATE_USER);
            //insert user
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getCode());
            statement.setString(3, user.getBirth());
            statement.setString(4, user.getStartDate());
            statement.setInt(5, user.getIdUser());

            statement.executeUpdate();
            removeUserRoleByUserId(user.getIdUser());
            for (Role role : user.getRoles()) {
                statement = connection.prepareStatement(Constants.ADD_USER_ROLE);
                statement.setInt(1, user.getIdUser());
                statement.setInt(2, role.getIdRole());
                statement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert statement != null;
            statement.close();
        }
        return false;
    }

    @Override
    public List<User> showBy(String code, String startDate, String role_name) {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(Constants.SHOW_BY);
            statement.setString(1, "%" + code + "%");
            statement.setString(2, "%" + startDate + "%");
            statement.setString(3, "%" + role_name + "%");
            ResultSet resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String fullName = resultSet.getString("full_Name");
                code = resultSet.getString("code");
                String birth = resultSet.getString("birth");
                startDate = resultSet.getString("start_date");
                Role role = new Role();
                role.setRoleName(resultSet.getString("role_name"));
                user = new User(id, fullName, code, birth, startDate, findRoleByUserId(id));
                users.add(user);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> res = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.FIND_ALL_ROLES)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int roleId = resultSet.getInt(1);
                String roleName = resultSet.getString(2);
                res.add(new Role(roleId, roleName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.FIND_ALL_USER)
        ) {
            ResultSet resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String fullName = resultSet.getString("full_Name");
                String code = resultSet.getString("code");
                String birth = resultSet.getString("birth");
                String startDate = resultSet.getString("start_date");
                user = new User(idUser, fullName, code, birth, startDate, findRoleByUserId(idUser));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findUserById(int id) {
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.FIND_USER_BY_ID)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user;
            if (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String fullName = resultSet.getString("full_Name");
                String code = resultSet.getString("code");
                String birth = resultSet.getString("birth");
                String startDate = resultSet.getString("start_date");
                user = new User(idUser, fullName, code, birth, startDate, findRoleByUserId(idUser));
                return user;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private List<Role> findRoleByUserId(int id_user) {
        List<Role> roles = new ArrayList<>();
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(Constants.FIND_ROLES_BY_USER_ID)
        ) {
            statement.setInt(1, id_user);
            ResultSet resultSet = statement.executeQuery();
            Role role;
            while (resultSet.next()) {
                int idRole = resultSet.getInt("id_role");
                String roleName = resultSet.getString("role_name");
                role = new Role(idRole, roleName);
                roles.add(role);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    private boolean removeUserRoleByUserId(int idUser) {
        PreparedStatement statement = null;
        try (Connection connection = DatabaseConnection.getConnection();
        ) {
            statement = connection.prepareStatement(Constants.DELETE_USER_ROLE);
            statement.setInt(1, idUser);
            statement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}
