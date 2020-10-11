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
				<span>XXX</span>
			</h1>
			<nav>
				<ul>
					<li id="actual"><a href="Controller">Home</a></li>
					<li><a href="Controller?command=overview">Overview</a></li>
					<li><a href="Controller?command=register">Register</a></li>
				</ul>
			</nav>
			<h2>Home</h2>

		</header>
		<main>
			<article>
			Welcome to the corona contact tracing site.
			</article>
			<article>
				<c:choose>
					<c:when test="${not empty login}">
						<c:if test="${not empty errorschangepsswd}">
							<div class="alert-danger">
								<ul>
									<c:forEach items="${errorschangepsswd}" var="error">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<p>Welcome ${login.firstName}</p>
						<form action="Controller?command=logout" method="POST">
							<input type="submit" id="logout" value="Log out">
						</form>
						<form action="Controller?command=changepsswd" method="POST">
							<p><label for="psswd">New password</label>
								<input type="password" id="psswd" name="newpassword" required> </p>
							<p><input type="submit" id="changepsswd" value="Change password"></p>
						</form>
					</c:when>
					<c:otherwise>
						<c:if test="${not empty error}">
							<div class="alert-danger">
								<ul>
									<li>${error}</li>
								</ul>
							</div>
						</c:if>
						<form action="Controller?command=login" method="POST">
							<p>
								<label for="userid">User id</label>
								<input type="text" id="userid" name="userid" required>
							</p>
							<p>
								<label for="password">Password</label>
								<input type="password" id="password" name="password" required>
							</p>
							<p>
								<input type="submit" id="login" value="Log in">
							</p>
						</form>
					</c:otherwise>
				</c:choose>
			</article>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>