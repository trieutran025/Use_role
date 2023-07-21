<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
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


<%--<form action="/user?action=add"method="post">--%>
<%--    Roles--%>
<%--    <input type="checkbox"value="1"name="role">admin--%>
<%--    <input type="checkbox"value="2"name="role">user--%>
<%--    ID--%>
<%--    <input type="number"name="id">--%>
<%--    Name--%>
<%--    <input type="text"name="name">--%>
<%--    Code--%>
<%--    <input type="text"name="code">--%>
<%--    Birth--%>
<%--    <input type="date"name="birth_user">--%>
<%--    <input type="submit"value="create">--%>
<%--</form>--%>


<form action="/user?action=add" method="post">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputID">ID</label>
            <input type="number" class="form-control" id="inputID" placeholder="ID" name="id">
        </div>
        <div class="form-group col-md-6">
            <label for="inputName">Full Name</label>
            <input type="text" class="form-control" id="inputName" placeholder="Full Name" name="name">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputCode">Code</label>
            <input type="text" class="form-control" id="inputCode" placeholder="U-XXXX" name="code">
        </div>
        <div class="form-group col-md-6">
            <label for="inputBirth">Birth</label>
            <input type="date" class="form-control" id="inputBirth" name="birth">
        </div>
    </div>
    <div class="form-row">

        <div class="form-group col-md-2">
            <label for="inputRole1">User</label>
            <input type="checkbox" class="form-control" id="inputRole1" value="1" name="role">
            <label for="inputRole">Admin</label>
            <input type="checkbox" class="form-control" id="inputRole" value="2" name="role">
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </div>
</form>
</body>
</html>
