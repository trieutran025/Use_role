package com.example.user_role.model;

import java.util.List;

public class User {
    int idUser;
    String fullName;
    String code;

    String birth;
    String startDate;

    private List<Role> roles ;

    public User(int idUser, String fullName, String code, String birth, String startDate, List<Role> roles) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.code = code;
        this.birth = birth;
        this.startDate = startDate;
        this.roles = roles;
    }

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
