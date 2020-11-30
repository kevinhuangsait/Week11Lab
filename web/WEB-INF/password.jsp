<%-- 
    Document   : password
    Created on : Nov 29, 2020, 9:55:28 PM
    Author     : 831719
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form method="post" action="reset">
            <input type="text" name="newPass">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="action" value="setpassword">
            <input type="submit" value="submit">
        </form>
    <c:if test="${changed == true}">
        Password successfully changed. <a href="login">Login</a>
    </c:if>
    <c:if test="${changed == false}">
        Error encountered when changing password.
    </c:if>
    </body>
</html>
