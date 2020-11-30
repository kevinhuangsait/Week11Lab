<%-- 
    Document   : reset
    Created on : Nov 29, 2020, 9:46:32 PM
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
        <h1>Reset Password</h1>
        Please enter your email address to reset your password.
        <form method="post" action="reset">
            Email Address: <input type="text" name="email">
            <br>
            <input type="hidden" name="action" value="requestreset">
            <input type="submit" value="Submit">
        </form>
    <c:if test="${message != null}">
        ${message}
        <a href="login">Login</a>
    </c:if>
    </body>
</html>
