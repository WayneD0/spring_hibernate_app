<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript">
    		function doLogout(){
    			form1.action = '../../j_spring_security_logout';
				form1.submit();
			}
    </script>
    <title>Spring3MVC with Hibernate3 CRUD Example using Annotations</title>
  </head>
  <body>
  		<form name="form1" method="post">
  			<h2>Spring3MVC with Hibernate3 CRUD Example using Annotations Second Level</h2>
  			<table align="right">
				<tr>
					<td align="right">
					Welcome : ${author}
					</td>
					<td>
						<input type="button" name="Logout" value = "Logout" onclick="doLogout()">
					</td>
					<td><a href="#" onclick="doLogout()" title="Logout123"></a></td>
				</tr>
			</table>
  			<h2>1. <a href="../../personalDetails/personalInfo/addConcept_Understanding_CURD.html">P.I Personal Information</a></h2>
  			
  			<input type="hidden" name="reqtrack" id="reqtrack"
			value="${TRACKERID}">
	    </form>
   </body>
</html>