<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div id="container">
    <header>
        <h1>Remove Contact</h1>
        <h2>
            Remove Contact
        </h2>
    </header>
    <main>
        <p>Give the info about the contact you want to remove</p>
        <form method = "POST" action="Controller?command=RemoveContactConfirm" novalidate="novalidate">
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               required value="${firstName}"> </p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                             required value="${lastName}"> </p>
            <p><label for="date">Date</label><input type="date" id="date" name="date"
                                                    required value="${date}"> </p>
            <p><label for="hour">Hour</label><input type="time" id="hour" name="hour"
                                                    required value="${hour}"> </p>
            <p><input type="submit" id="removeContactconfirm" value="Remove Contact"></p>
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>