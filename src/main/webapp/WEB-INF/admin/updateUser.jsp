<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "/updateUser" method="post">
    <input required type="number" name="id" placeholder="id">
    <input required type="text" name="name" placeholder="New name">
    <input required type="password" name="password" placeholder="New password">
    <input required type="number" name="age" placeholder="New age">
    <input type="submit" value="Update user">
</form>
</body>
</html>
