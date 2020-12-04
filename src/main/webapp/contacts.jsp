<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div id="container">
<header>
    <h1>
        Search
    </h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li id="actual"><a href="Controller?command=Contacts">Contacts</a></li>
            <c:choose>
                <c:when test="${not empty login}">
                    <li><a href="Controller?command=Testresult">Register testresult</a></li>
                    <li><a href="Controller?command=Search">Search</a></li>
                </c:when>
            </c:choose>
            <li><a href="Controller?command=Register">Register</a></li>
        </ul>
    </nav>
    <h2>
        Possible positive contacts
    </h2>
</header>
<main>
    <table>
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
            <c:when test="${contacts.size() <= 0}">
                <p>No contacts to show</p>
            </c:when>
            <c:otherwise>
                <tr>
                    <th>Date</th>
                    <th>Hour</th>
                    <th>Name</th>
                </tr>
                <c:forEach var="contact" items="${contacts}">
                    <tr>
                        <td><c:out value='${contact.date}' /></td>
                        <td><c:out value='${contact.hour}' /></td>
                        <td><c:out value='${contact.firstName}'/> <c:out value='${contact.lastName}' /></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${not empty login}">
                <form method = "POST" action="Controller?command=AddContact" novalidate="novalidate">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                                       required value="<c:out value='${firstName}'/>"> </p>
                    <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                                     required value="<c:out value='${lastName}'/>"> </p>
                    <p><label for="email">Email</label><input type="email" id="email" name="email"
                                                            required value="<c:out value='${email}'/>"></p>
                    <p><label for="date">Date</label><input type="date" id="date" name="date"
                                                              required value="<c:out value='${date}'/>"></p>
                    <p><label for="hour">Hour</label><input type="time" id="hour"  name="hour"
                                                                    required value="<c:out value='${hour}'/>"> </p>
                    <p><label for="gsm">GSM</label><input type="tel" id="gsm"  name="gsm"
                                                                required value="<c:out value='${gsm}'/>"> </p>
                    <p><input type="submit" id="addContact" value="Add Contact"></p>

                </form>
            </c:when>
        </c:choose>
        <caption>Contacts Overview</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
