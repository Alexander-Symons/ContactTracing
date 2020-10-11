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
        <h1><span>XXX</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li id="actual"><a href="Controller?command=overview">Overview</a></li>
                <li><a href="Controller?command=register">Register</a></li>
            </ul>
        </nav>
        <h2>
        User Overview
        </h2>

    </header>
    <main>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach var="persoon" items="${users}">
                <tr>
                    <td>${persoon.email}</td>
                    <td>${persoon.firstName}</td>
                    <td>${persoon.lastName}</td>
                </tr>
            </c:forEach>

            <caption>Users Overview</caption>
        </table>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>