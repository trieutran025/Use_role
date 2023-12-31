package com.example.user_role.controler;

import com.example.user_role.bean.Role;
import com.example.user_role.bean.User;
import com.example.user_role.regex.Regex;
import com.example.user_role.service.UserRoleServiceI;
import com.example.user_role.service.UserRoleServiceImpl;
import com.example.user_role.validate.UserRoleValidate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@WebServlet(name = "USerServlet", value = "/user")
public class UserServlet extends HttpServlet {
    UserRoleServiceI serviceI = new UserRoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "null";
        }
        switch (action) {
            case "add":
                viewCreate(req, resp);
                break;
            case "list":
                viewList(req, resp);
                break;
            case  "edit":
                try {
                    viewEdit(req,resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    private void viewEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = serviceI.findUserById(id);
        req.setAttribute("roles", serviceI.findAllRoles());
        req.setAttribute("user", user);
        req.getRequestDispatcher("user/edit.jsp").forward(req,resp);
    }

    private void viewList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = serviceI.findAllUser();
        req.setAttribute("users", users);
        req.getRequestDispatcher("user/list.jsp").forward(req, resp);
    }

    private void viewCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", serviceI.findAllRoles());
        req.getRequestDispatcher("user/addUser.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = null;
        }
        switch (action) {
            case "add":
                try {
                    doCreate(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "delete":
                try {
                    doDeletes(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    doEdit(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                doSearch(req,resp);
                break;
            default:
                break;
        }
    }
    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        boolean isValid =true;
        String testId = req.getParameter("id");
        String errorId = UserRoleValidate.validateId(testId);
        int id = 0;
        if(errorId!=null){
            isValid = false;
            req.setAttribute("errorId",errorId);
        }else {
            id =Integer.parseInt(testId);
        }
        String testName= req.getParameter("name");
        String errorName = UserRoleValidate.validateName(testName);
        String name = null;
        if(errorName!=null){
            isValid =false;
            req.setAttribute("errorName",errorName);
        }else {
            name =testName;
        }
        String testCode = req.getParameter("code");
        String errorCode = UserRoleValidate.validateCode(testCode);
        String code = null;
        if(errorCode!=null){
            isValid = false;
            req.setAttribute("errorCode",errorCode);
        }else {
            code = testCode;
        }

        String birth = req.getParameter("birth");
        String startDate = LocalDate.now().toString();
        String[] roleList = req.getParameterValues("role");

        List<Role> roles = new ArrayList<>();
        for (String r : roleList) {
            Role role = new Role();
            role.setIdRole(parseInt(r));
            roles.add(role);
        }
        if(isValid) {
            User user = new User(id, name, code, birth, startDate, roles);
            serviceI.add(user);
            resp.sendRedirect("/user?action=list");
        }
        else {
            req.getRequestDispatcher("user/addUser.jsp").forward(req,resp);
        }
    }

    private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("inputChange");
        String startDate = req.getParameter("inputChange");
        String role_name = req.getParameter("inputChange");
        List<User> users = serviceI.showBy(code,startDate,role_name);
        req.setAttribute("users", users);
        req.setAttribute("roles", serviceI.findAllRoles());
        req.getRequestDispatcher("user/list.jsp").forward(req, resp);
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String idParam = req.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String name = req.getParameter("name");
        String code = req.getParameter("code");
        String birth = req.getParameter("birth");
        String startDate = LocalDate.now().toString();
        String[] roleList = req.getParameterValues("role");

        List<Role> roles = new ArrayList<>();
        for (String r : roleList) {
            Role role = new Role();
            role.setIdRole(parseInt(r) );
            roles.add(role);
        }
        User user = new User(id, name, code, birth, startDate, roles);
        serviceI.update(user);
        resp.sendRedirect("/user?action=list");
    }

    private void doDeletes(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String[] idRemove = req.getParameterValues("idRemove");
        for (String idR : idRemove) {
            serviceI.remove(Integer.parseInt(idR));
        }
        resp.sendRedirect("/user?action=list");
    }
}
