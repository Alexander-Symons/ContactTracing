<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Register Testresult</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div id="container">
    <header>
        <h1>Register Testresult</h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contacts">Contacts</a></li>
                <c:choose>
                    <c:when test="${not empty login}">
                        <li id="actual"><a href="Controller?command=Testresult">Register testresult</a></li>
                        <li><a href="Controller?command=Search">Search</a></li>
                    </c:when>
                </c:choose>
                <li ><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>
            Regiser your testresult here
        </h2>
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
            <c:when test="${testresults.size() <= 0}">
                <p>No testresults to show</p>
            </c:when>
            <c:otherwise>
                <table>
                <tr>
                    <th>userid</th>
                    <th>date</th>
                </tr>
                <c:forEach var="testresult" items="${testresults}">
                    <tr>
                        <td><c:out value='${testresult.getUserId()}' /></td>
                        <td><c:out value='${testresult.date}' /></td>
                    </tr>
                </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

        <form method = "POST" action="Controller?command=AddTestresult" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="date">Date</label><input type="date" id="date" name="date"
                                                         required value="<c:out value='${date}'/>"> </p>
            <p><input type="submit" id="addtestresult" value="Covid 19 Positive"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>

