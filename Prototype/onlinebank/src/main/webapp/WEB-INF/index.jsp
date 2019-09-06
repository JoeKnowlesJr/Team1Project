<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Merit Banking</title>
      <link href="css/style.css" rel="stylesheet" type="text/css">
      <link href="css/bank.css" rel="stylesheet" type="text/css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <script type="text/javascript">
      </script>
   </head>
   <body>
      <img id="logo" src="MeritBankLogo.gif" alt="MeritBankOfAmericaLogo"/>
      <div id="top_links">
         <div id="header">
            <h3>Your Number One Bank</h3>
         </div>
         <div id="navigation">
               <ul>
                  <li><a href="login.jsp">LOGIN</a></li>
                  <li><a href="locations.jsp">LOCATIONS</a></li>
                  <li><a href="about.jsp">ABOUT US</a></li>
                  <li><a href="contact.jsp">CONTACT US </a></li>
               </ul>
               <div id="formcontainer">
                  <form class="navbar-form navbar-right">
                     <input class="form-control col-lg-8" type="text" placeholder="  Search" aria-label="Search">
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
               <a href="login.jsp" class="w3-bar-item w3-button">LOGIN</a>
               <a href="locations.jsp" class="w3-bar-item w3-button">LOCATIONS</a>
               <a href="about.jsp" class="w3-bar-item w3-button">ABOUT US</a>
               <a href="contact.jsp" class="w3-bar-item w3-button">CONTACT US</a>
               </div>
            </div>
         </div>
         
         <div id="wrapper">
            <div id="main_content">
               <div class="subcontents lg md sm">
                  <h1 class="content_header">Random Latin</h1>
                  <p>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
               </div>

               <div class="logincontents lg md sm">
                  	<h1 class="content_header">Welcome!</h1>
					<div class="inputs">
						<form:form method='post' modelAttribute="lfo">
							<fieldset class='fieldset-auto-width'>
								<legend>Login</legend>
								<table><tbody>
								<c:if test="${lfo.isFailed()} == true">
									<tr><td class='alert'>Invalid credentials!</td></tr>
								</c:if>
									<tr>
										<td>Email:</td><td><input type='email' name='email' required/></td>
									</tr>
									<tr>
										<td>Password:</td><td><input type='password' name='password' required/></td>
									</tr>
								</tbody></table>
			
								<input formaction='/signup' type='submit' value='Sign Up' />
								<input formaction='/signin' type='submit' value='Sign In' />
							</fieldset>				
						</form:form>
					</div>                  
               </div>
               <div class="subcontents lg md sm">
                  <h1 class="content_header">Mission Statement</h1>
                  <p> "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.</p>
               </div>               
               <div id="advertisement"></div>
            </div>
         </div>
   <!--end of footer-->

      </div>
         <!-- Bootstrap core JavaScript
           ================================================== -->
           <!-- Placed at the end of the document so the pages load faster -->
           <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
           <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
           <script src="../../assets/js/vendor/popper.min.js"></script>
           <script src="../../dist/js/bootstrap.min.js"></script>
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
      </script>
   </body>
</html>





