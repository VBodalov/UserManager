<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="UTF-8">
	<title>User Manager</title>
</head>
<body>
	
	<b>
	Menu (JSP):
	<a href="/">Home</a>
	<a href="all-users">All Users</a>
	<a href="new-user">New User</a><br><br>
	</b>
	REST test: 
	<a href="rest-all-users">All Users</a>
	<a href="new-user-form.html">New User</a>
	<a href="update-user-form.html">Edit User</a>
	<a href="block-user-form.html">Block User</a>
	<a href="unblock-user-form.html">Unblock User</a>
	<a href="delete-user-form.html">Delete User</a>
	<a href="login-form.html">Login</a>
	<br><br><br>

	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<h2 align="center">Welcome to User Manager</h2>			
		</c:when>
		<c:when test="${mode == 'MODE_USERS'}">
			<h3>Users list</h3>
			<table border="1">
				<thead>
					<tr>
						<th>Id</th>
						<th>Login</th>
						<th>Password</th>
						<th>Blocked</th>
						<th colspan="3">Actions (JSP)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.id}</td>
							<td>${user.login}</td>
							<td>${user.password}</td>
							<td>${user.blocked}</td>
							<td><a href="update-user?id=${user.id}">Edit</a></td>
							<td><a href="block-user?id=${user.id}">
								<c:if test="${user.blocked == true}">
									Unblock
								</c:if>
								<c:if test="${user.blocked == false}">
									Block
								</c:if>
							</a></td>
							<td><a href="delete-user?id=${user.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>	
			</table>
		</c:when>
		<c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
			<form method="POST" action="save-user">
				<h3>Create new user</h3>
				<input type="hidden" name="id" value="${user.id}"/>
				<table>
					<tr>
						<td>Login</td>
						<td><input type="text" name="login" value="${user.login}"/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="text" name="password" value="${user.password}"/></td>
					</tr>
					<tr>
						<td>Blocked</td>
						<td>
							<input type="radio" name="blocked" value="true" <c:if test="${user.blocked == true}">checked</c:if>/>Yes
							<input type="radio" name="blocked" value="false" <c:if test="${user.blocked != true}">checked</c:if>/>No
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="Save"/>
						</td>
					</tr>
				</table>
			</form>
		</c:when>
	</c:choose>
</body>
</html>