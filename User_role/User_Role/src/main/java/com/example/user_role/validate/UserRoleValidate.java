package com.example.user_role.validate;

import com.example.user_role.bean.User;
import com.example.user_role.regex.Regex;
import com.example.user_role.service.UserRoleServiceI;
import com.example.user_role.service.UserRoleServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRoleValidate {
     static UserRoleServiceI serviceI = new UserRoleServiceImpl();

    public static String validateName(String name) {
        try {
            Pattern pattern = Pattern.compile(Regex.REGEX_NAME);
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()){
                return "Chỉ chứa chữ";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String validateCode(String code){
        try {
            Pattern pattern = Pattern.compile(Regex.REGEX_CODE);
            Matcher matcher = pattern.matcher(code);
            if(!matcher.matches()){
                return "Khong dung dinh dang (U-XXXX) X la number";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String validateId(String id) {
        try {
            int temp = Integer.parseInt(id);
            if (temp < 0) {
                return "Id phải lớn hơn 0";
            }
        } catch (Exception e) {
            return "Id phải là số nguyên";
        }
        User user = serviceI.findUserById(Integer.parseInt(id));
        if(user!=null){
            return "ID đã tồn tại trong DB";
        }
        return null;
    }

    public static void main(String[] args) {
        String result = validateCode("U-1022");
        System.out.println(result);
    }
}
