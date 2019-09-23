<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="dashStyle.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Dashboard</title>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
</head>
<body>
	<img src="img/MeritBankLogo.gif" alt="MeritBankOfAmericaLogo"
		width="600" />
	<div id="top_links">
		<div id="header">
			<h2>Your Number One Bank
				${user-session.getUser().getFirstName()}</h2>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="index.jsp">HOME</a></li>
				<li><a href="locations.jsp">LOCATIONS</a></li>
				<li><a href="about.jsp">ABOUT US</a></li>
				<li><a href="contact.jsp">CONTACT US </a></li>
			</ul>
			<div id="formcontainer">
				<form class="navbar-form navbar-right">
					<input class="form-control col-lg-8" type="text"
						placeholder="  Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>

			<div class="w3-dropdown-hover">
				<button class="w3-button">
					<div class="bar"></div>
					<div class="bar"></div>
					<div class="bar"></div>
				</button>
				<div class="w3-dropdown-content w3-bar-block w3-border">
					<a href="index.jsp" class="w3-bar-item w3-button">HOME</a> <a
						href="locations.jsp" class="w3-bar-item w3-button">LOCATIONS</a> <a
						href="about.jsp" class="w3-bar-item w3-button">ABOUT US</a> <a
						href="contact.jsp" class="w3-bar-item w3-button">CONTACT US</a>
				</div>
			</div>
		</div>
		<div id="wrapper">
			<h1 class="content_header">Welcome, ${dm.user.getFirstName()}!</h1>
			<div id="main_content">
				<div class="row1">
					<!-- Top Left BOX-->
					<div class="dsubcontents dlg md sm">
						<div class="w3-respnsive">
							<p style="font-size: 20px">${dm.user.getName()}</p>
							<p style="font-size: 15px">${dm.user.getEmail()}</p>
							<p style="font-size: 13px">${dm.user.getAddress().getCity()}, ${dm.user.getAddress().getState()}</p>
							<p style="font-size: 13px">Customer since ${dm.user.getCreatedDate()}</p>

						</div>
						<div id="newAcct-view">
							<div class="w3-respnsive">
								<div class="w3-respnsive">
									<select onchange="toggleAcctAction(value)">
										<option label="Open Account" value="open">
										<option label="Close Account" value="close">
									</select>
								</div>
								<h3>Open a new account</h3>
								<form:form id='newacct' method='post' modelAttribute="dm.nafo"
									style="margin-top: 25px;">
									<fieldset class='fieldset-auto-width'>
										<legend>New Account</legend>
										<table class="w3-table-all w3-hoverable">
											<tbody>
												<tr class="w3-light-grey">
													<td>Account Type:</td>
													<td><form:select path="acctType" required="true">
															<option value="">Account Type</option>
															<form:options />
														</form:select></td>
												</tr>
												<tr>
													<td>Deposit Amount:</td>
													<td><span class="input-symbol-ds"><form:input
																type="number" step="0.01" path="amount"></form:input></span></td>
												</tr>
												<tr>
													<td><form:input type="hidden" path="user_id"
															value="${dm.user.getId()}"></form:input></td>
													<td><input formaction='/createAccount' type='submit'
														value='Open Account' /></td>
												</tr>
											</tbody>
										</table>
									</fieldset>
								</form:form>
							</div>						
						</div>
					</div>
					<!-- Top right box-->
					<div class="dsubcontents dlg md sm">
						<div id="account-view" class="w3-respnsive">
							<table class="w3-table-all w3-hoverable">
								<thead>
									<tr class="w3-light-grey">
										<th>Account #</th>
										<th>Acct Type</th>
										<th>Balance</th>
										<th>Rate (%)</th>
									</tr>
								</thead>
								<tr>
									<td>${dm.account.getAccountNumber()}</td>
									<td>${dm.account.getAccountType().getTypeName()}</td>
									<th>${dm.account.getBalance()}</</th>
									<th>${dm.account.getRate()}</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="row2">
					<!-- Bottom left box-->
					<div class="dsubcontents dlg md sm">
						<table class="w3-table-all w3-hoverable">
							<thead>
								<tr class="w3-light-grey">
									<th>Account #</th>
									<th>Acct Type</th>
									<th>Balance</th>
									<th>Rate (%)</th>
								</tr>
							</thead>
							<c:forEach items="${dm.user.getAccounts()}" var="acct">
								<tr onclick="updateAcctView(${acct.getId()});">
									<td>${acct.getAccountNumber()}</td>
									<td>${acct.getAccountType().getTypeName()}</td>
									<th>${acct.getBalance()}</th>
									<th>${acct.getRate()}</th>
								</tr>
							</c:forEach>
						</table>
					</div>
					<!--Bottom right -->
					<div class="dsubcontents dlg md sm">
						<span>Transaction</span>
						<select name="tType" id="tType" onchange="transactionSelect(value)">
							<option value="">Select Transaction</option>
							<c:forEach items="${dm.getTransactionTypes()}" var="t">
								<option value="${t}">${t}</option>
							</c:forEach>
						</select>					
						<div id="transaction-view" class="w3-responsive">
							<form:form id='transaction' method='post' modelAttribute="dm.tfo">
								<fieldset class='fieldset-auto-width'>
									<legend>Transaction</legend>

								</fieldset>
							</form:form>
						</div>
					</div>
				</div>
				<div id="footer">
					<h1 class="copyright">Copyright&#169; Team Sparks</h1>
				</div>
			</div>
		</div>
		<!--end of footer-->
		<!-- Bootstrap core JavaScript
           ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
		<script src="../../assets/js/vendor/popper.min.js"></script>
		<script src="../../dist/js/bootstrap.min.js"></script>
	</div>
	<script>
         // When the user scrolls the page, execute myFunction 
         window.onscroll = function() {myFunction()};
         // Get the navbar
         var navbar = document.getElementById("navigation");
         // Get the offset position of the navbar
         var sticky = navbar.offsetTop;
         // Add the sticky class to the navbar when you reach its scroll position. Remove "sticky" when you leave the scroll position
         function myFunction() {
	         if (window.pageYOffset >= sticky) {
	            navbar.classList.add("sticky")
	         } else {
	            navbar.classList.remove("sticky");
	         }
         }

		function transactionSelect($val){
			var mygetrequest=new XMLHttpRequest();
			mygetrequest.onreadystatechange=function() {
				if (mygetrequest.readyState==4){
					if (mygetrequest.status==200 || window.location.href.indexOf("http")==-1){
						document.getElementById("transaction-view").innerHTML = mygetrequest.responseText;
					} else {
						alert("An error has occured making the request " + mygetrequest.readyState + " " + mygetrequest.status);
					}
				}
			}
			mygetrequest.open("POST", "/transaction/" + $val, true);
			mygetrequest.send(null);
		}		    

		 function updateAcctView($val){
			    var mygetrequest=new XMLHttpRequest();
			    mygetrequest.onreadystatechange=function(){
			     if (mygetrequest.readyState==4){
			      if (mygetrequest.status==200 || window.location.href.indexOf("http")==-1){
			       document.getElementById("account-view").innerHTML = mygetrequest.responseText;
			      }
			      else{
			       alert("An error has occured making the request " + mygetrequest.readyState + " " + mygetrequest.status);
			      }
			     }
			    }
			    mygetrequest.open("GET", "/dashboard/acctView/" + $val, true);
			    mygetrequest.send(null);
			}

			function toggleAcctAction($val) {
				if ($val == 'close')
					closeAcct();
				else
					openAcct();
			}

			function openAcct() {
				var mygetrequest=new XMLHttpRequest();
				mygetrequest.onreadystatechange=function() {
					if (mygetrequest.readyState==4){
						if (mygetrequest.status==200 || window.location.href.indexOf("http")==-1){
							document.getElementById("newAcct-view").innerHTML = mygetrequest.responseText;
						} else {
							alert("An error has occured making the request " + mygetrequest.readyState + " " + mygetrequest.status);
						}
					}
				}
				mygetrequest.open("GET", "/dashboard/open", true);
				mygetrequest.send(null);
			}

			function closeAcct() {
				var mygetrequest=new XMLHttpRequest();
				mygetrequest.onreadystatechange=function() {
					if (mygetrequest.readyState==4){
						if (mygetrequest.status==200 || window.location.href.indexOf("http")==-1){
							document.getElementById("newAcct-view").innerHTML = mygetrequest.responseText;
						} else {
							alert("An error has occured making the request " + mygetrequest.readyState + " " + mygetrequest.status);
						}
					}
				}
				mygetrequest.open("GET", "/dashboard/close", true);
				mygetrequest.send(null);
			}
      </script>
</body>
</html>