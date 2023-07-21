<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<%--<form action="/user?action=update&id=${users.getUser().getIdUser()}"method="post">--%>
<%--    Roles--%>
<%--    <input type="checkbox"value="1"name="role">admin--%>
<%--    <input type="checkbox"value="2"name="role">user--%>
<%--    Name--%>
<%--    <input type="text"name="name"value="${users.getUser().getFullName()}">--%>
<%--    Code--%>
<%--    <input type="text"name="code"value="${users.getUser().getCode()}">--%>
<%--    Birth--%>
<%--    <input type="date"name="birth_user"value="${users.getUser().getBirth()}">--%>
<%--    <input type="submit"value="create">--%>
<%--    <input type="hidden"name="action"value="update">--%>
<%--</form>--%>

<form action="/user?action=edit&id=${users.getUser().getIdUser()}" method="post">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputName">Full Name</label>
            <input type="text" class="form-control" id="inputName" value="${users.getUser().getFullName()}"
                   placeholder="Full Name" name="name">
        </div>
        <div class="form-group col-md-6">
            <label for="inputCode">Code</label>
            <input type="text" class="form-control" id="inputCode" value="${users.getUser().getCode()}"
                   placeholder="U-XXXX" name="code">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputBirth">Birth</label>
            <input type="date"class="form-control" id="inputBirth" value="${users.getUser().getBirth()}" name="birth">
        </div>

        <div class="form-group col-md-2">
            <c:forEach items="${roles}" var="r">
                <label>${r.roleName}</label>
                <input type="checkbox" class="form-control" value="${r.idRole}" name="role" ${r.idRole == users.role.idRole ? "checked" : ""}>
            </c:forEach>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </div>
</form>



</body>
</html>
