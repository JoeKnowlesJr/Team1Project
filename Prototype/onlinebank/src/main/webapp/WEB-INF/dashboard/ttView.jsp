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
		<form:form id='transaction' method='post' modelAttribute="dm.tfo">
			<fieldset class='fieldset-auto-width'>
				<legend align="left">Transfer</legend>
				<table><tbody>
					<c:if test="${dm.isTfoError() == true}">
						<tr><td>${dm.getError()}</td></tr>
					</c:if>
					<tr>
						<td>${account.toString()}</td>
						<td>									
						    <form:select  path="tAcct" required="true">
							    <form:option value="">Select Target</form:option>
							    <c:forEach items="${accounts}" var="a">
							    	<form:option value="${a.getAccountNumber()}">${a.toString()}</form:option>
							    </c:forEach>
						    </form:select>								
						</td>
						<td><span class="dollar-sign">$</span><form:input id="amount-input" type="number" step="0.01" path="amount"></form:input></td>	
					</tr>
					<tr>
						<td colspan="2"><input formaction='/transaction' type='submit' value='Transfer'/></td>
					</tr>
				</tbody></table>						
			</fieldset>	
		</form:form>
	</body>
</html>