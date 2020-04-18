<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>Delete user</H1> <br>
<form action = "/deleteUser" method="post">
    <input required type="number" name="id" placeholder="Enter id">
    <input type="submit" value="Delete">
</form>
</body>
</html>
