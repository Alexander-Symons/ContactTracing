<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            Change Role
        </h1>
        <nav>
            <ul>
                <li id="actual"><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contacts">Contacts</a></li>
                <c:choose>
                    <c:when test="${not empty login}">
                        <li><a href="Controller?command=Testresult">Register testresult</a></li>
                        <li><a href="Controller?command=Search">Search</a></li>
                    </c:when>
                </c:choose>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>

        <h2>Change Role</h2>

    </header>
    <main>
        <c:choose>
            <c:when test="${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach items="${errors}" var="error">
                            <li><c:out value='${error}' /></li>

                        </c:forEach>
                    </ul>
                </div>
            </c:when>
        </c:choose>

        <form action="Controller?command=ChangeRoleConfirm" method="POST">
            <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
                                                         required value="<c:out value='${userid}'/>"> </p>
            <p>
                <label for="role">Role</label>
                <input type="text" id="role" name="role" required value="<c:out value='${role}'/>">
            </p>
            <p>
                <input type="submit" id="changeroleconfirm" value="Change role">
            </p>
        </form>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>
