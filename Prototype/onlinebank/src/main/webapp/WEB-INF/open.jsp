<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	</head>
	<body>		
		<form:form id='signup' method='post' modelAttribute="nafo">
			<fieldset class='fieldset-auto-width'>
				<legend>New Account</legend>
				<table><tbody>
					<tr>
						<td>User</td>
						<td>									
						    <form:select  path="user_id" required="true">
							    <form:option value="">Select User</form:option>
							    <c:forEach items="${users}" var="u">
							    	<form:option value="${u.getId()}">${u.getName()}</form:option>
							    </c:forEach>
						    </form:select>								
						</td>
					</tr>
					<tr>
						<td>Account Type:</td>
						<td>
						    <form:select  path="acctType" required="true">
						    	<option value="">Account Type</option>
							    <form:options />
						    </form:select>									
						</td>
					</tr>
					<tr>
						<td colspan="2"><input formaction='/createAccount' type='submit' value='Open Account'/></td>
					</tr>
				</tbody></table>						
			</fieldset>	
		</form:form>
	</body>
</html>