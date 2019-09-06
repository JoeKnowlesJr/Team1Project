<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="css/dashboard.css">
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<meta charset="ISO-8859-1">
		<title>Dashboard</title>
	</head>
	<body>
		<div id="top-left">
			<div id="user-view">
				<table>
					<thead>
						<tr>
							<th>First</th>
							<th>Last</th>
							<th>Email</th>
							<th>Location</th>
					</thead>
					<tbody>
						<tr>
							<td>${dm.user.getFirstName()}</td>
							<td>${dm.user.getLastName()}</td>
							<td>${dm.user.getEmail()}</td>
							<td>${dm.user.getAddress().getCity()}, ${dm.user.getAddress().getState()}</td>
					</tbody>
				</table>	
			</div>	
			<div id="open-acct-view">			
				<form:form id='newacct' method='post' modelAttribute="dm.nafo">
					<fieldset class='fieldset-auto-width'>
						<legend>New Account</legend>
						<table><tbody>
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
								<td>Deposit Amount:</td>
								<td>									
								    <span class="dollar-sign">$</span><form:input id="amount-input" type="number" step="0.01" path="amount"></form:input>						
								</td>
							</tr>							
							<tr>
								<td><form:input type="hidden" path="user_id" value="${dm.user.getId()}"></form:input></td>
								<td><input formaction='/createAccount' type='submit' value='Open Account'/></td>
							</tr>
						</tbody></table>						
					</fieldset>	
				</form:form>			
			</div>
		</div>
		<div id="top-right">
			<div id="account-view">
				<table>
					<thead>
						<tr>
							<th>Account #</th>
							<th>Acct Type</th>
							<th>Balance</th>
							<th>Rate (%)</th>
					</thead>
					<tbody>
						<tr>
							<td>${dm.account.getAccountNumber()}</td>
							<td>${dm.account.getAccountType().getTypeName()}</td>
							<td>${dm.account.getBalance()}</td>
							<td>${dm.account.getRate()}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="transaction-view">
				<form:form id='transaction' method='post' modelAttribute="dm.tfo">
				<form:input type="hidden" path="tAcct" value="${dm.account.getAccountNumber()}"></form:input>
					<fieldset class='fieldset-auto-width'>
						<legend>Transaction</legend>
						<table><thead>
							<tr>
								<th>Type</th>
								<th>To/From</th>
								<th>Amount</th>
							</tr>
						</thead><tbody>
							<c:if test="${dm.failed==true}">
								<tr><td>${dm.error}</td></tr>
							</c:if>
							<tr>
								<td>
								    <form:select  path="tType" required="true">
								    	<option value="">Transaction Type</option>
								    	<c:forEach items="${dm.getTransactionTypes()}" var="t">
									    	<form:option value="${t}">${t}</form:option>
									    </c:forEach>
								    </form:select>									
								</td>
								<td>			
								    <form:select  path="oAcct" required="true">
									    <form:option value="">Select</form:option>
									    <c:forEach items="${dm.user.getAccounts()}" var="a">
									    	<form:option value="${a.getId()}">${a.toString()}</form:option>
									    </c:forEach>
								    </form:select>								
								</td>
								<td><span class="dollar-sign">$</span><form:input id="amount-input" type="number" step="0.01" path="amount"></form:input></td>	
							</tr>
							<tr>
								<td></td><td><input formaction='/transaction' type='submit' value='GO'/></td>
							</tr>
						</tbody></table>						
					</fieldset>	
				</form:form>			
			</div>
		</div>
		<div id="bottom-left">
			<div id="account-list">
				<table>
					<thead>
						<tr>
							<td>Account #</td>
							<td>Type</td>
							<td>Balance</td>
							<td>Rate (%)</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dm.user.getAccounts()}" var="acct">
							<tr onclick="updateAcctView(${acct.getId()});">
								<td>${acct.getAccountNumber()}</td>
								<td>${acct.getAccountType().getTypeName()}</td>
								<td>${acct.getBalance()}</td>
								<td>${acct.getRate()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>			
			</div>
		</div>
		<div id="bottom-right">
		
		</div>
		<script>
		    function updateAcctView($val) {
		        $.ajax({
		            type: "GET",  
		            url: "/acctView/" + $val,
		            success: function(response) {
		                $("#account-view").html( response );
		            }
		        });
		    }
/////////////////////////////////////////////////////////////////////////////				    
		</script>
	</body>
</html>