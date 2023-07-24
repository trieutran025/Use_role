package com.example.user_role.util;

public class Constants {
    public static final String ADD_USER = "insert into user(id_user,full_name,code,birth,start_date) values(?,?,?,?,?)";
    public static final String ADD_USER_ROLE = "insert into user_role(id_user,id_role) values(?,?)";
    public static final String FIND_ALL_USER_ROLE = "SELECT user.* ,GROUP_CONCAT(role_name SEPARATOR ' ') as role_name\n" + "FROM user_role\n" + "join role USING(id_role)\n" + "join user using(id_user)\n" + "GROUP BY id_user;";
    public static final String UPDATE_USER = "update user set full_name = ?,code= ?,birth = ?,start_date = ? where id_user = ?";
    public static final String UPDATE_USER_ROLE = "UPDATE user_role SET  user_role.id_role = ? WHERE user_role.id_User = ?; ";
    public static final String DELETE_USER = "delete from user  where user.id_user =?";
    public static final String DELETE_USER_ROLE = "delete from user_role  where user_role.id_user =?";
    public static final String FIND_BY_ID_USER = "select *from user where id_user=?";
    public static final String SHOW_BY = "SELECT user.*, GROUP_CONCAT(role_name SEPARATOR ' ') as role_name" +
            "\n" + "FROM user_role" +
            "\n" + "JOIN role USING(id_role)" +
            "\n" + "JOIN user USING(id_user)" +
            "\n" + "WHERE code  like ? or  start_date like ?  or role_name like ?" +
            "\n" + "GROUP BY id_user;";
    public static final String FIND_BY_ID_USER_ROLE = "SELECT user.*, \n" +
            "       GROUP_CONCAT(role_name SEPARATOR ' ') as role_name, \n" +
            "       GROUP_CONCAT(id_role SEPARATOR ' ') as id_role\n" +
            "FROM user_role\n" +
            "JOIN role USING(id_role)\n" +
            "JOIN user USING(id_user)\n" +
            "WHERE id_user = ?\n" +
            "GROUP BY id_user;\n";

    public static final String FIND_ALL_ROLES = "select * from role";
    public static final String FIND_ROLES_BY_USER_ID = "select r.* from role r inner join user_role u on r.id_role = u.id_role " +
            "where u.id_user = ?";
    public static final String FIND_ALL_USER = "select * from user";
    public static final String FIND_USER_BY_ID = "select *from user where id_user=?";
}
