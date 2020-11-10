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
        Contacts
    </h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li id="actual"><a href="Controller?command=Contacts">Contacts</a></li>
            <li><a href="Controller?command=Register">Register</a></li>
        </ul>
    </nav>
    <h2>
        Contacts Overview
    </h2>
</header>
<main>
    <table>
        <c:choose>
            <c:when test="${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach items="${errors}" var="error">
                            <li>${error}</li>
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
                        <td>${contact.date}</td>
                        <td>${contact.hour}</td>
                        <td>${contact.firstName + " " + contact.lastName}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <caption>Contacts Overview</caption>
    </table>

    <form method = "POST" action="Controller?command=AddContact" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                           required value="${firstName}"> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                         required value="${lastName}"> </p>
        <p><label for="date">Date</label><input type="date" id="date" name="date"
                                                     required value="${date}"> </p>
        <p><label for="hour">Hour</label><input type="time" id="hour" name="hour"
                                                     required value="${hour}"> </p>
        <p><label for="gsm">GSM</label><input type="text" id="gsm" name="gsm"
                                                     required value="${gsm}"> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email"
                                                  required value="${email}"></p>
        <p><input type="submit" id="AddContact" value="Add Contact"></p>

    </form>
    <h3>Remove Contact</h3>
    <p>You can remove a contact if you have given wrong information about a contact</p>
    <form method = "POST" action="Controller?command=RemoveContact" novalidate="novalidate">
        <p><input type="submit" id="RemoveContact" value="Remove Contact"></p>
    </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
