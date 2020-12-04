
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            User Overview
        </h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contacts">Contacts</a></li>
                <c:choose>
                    <c:when test="${not empty login}">
                        <li><a href="Controller?command=Testresult">Register testresult</a></li>
                        <li id="actual"><a href="Controller?command=Search">Search</a></li>
                    </c:when>
                </c:choose>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>
            Overview
        </h2>

    </header>
    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li><c:out value='${error}'/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <table>
            <tr>
                <th>Name</th>
                <th>GSM</th>
                <th>Email</th>
            </tr>
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><c:out value='${contact.firstName}'/> <c:out value='${contact.lastName}'/></td>
                    <td><c:out value='${contact.gsm}'/></td>
                    <td><c:out value='${contact.email}'/></td>
                </tr>
            </c:forEach>

            <caption>Possible positive contacts</caption>
        </table>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>