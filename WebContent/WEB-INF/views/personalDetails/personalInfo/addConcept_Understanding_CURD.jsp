<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CURD Practice</title>
		<script type="text/javascript">
		function goSave(){
			document.form1.action="saveWithStringMethodThirdPrac.html";
			//document.form1.action="saveCURDSecondPrac.html";
			//document.form1.action="saveCURD.html";
			document.form1.submit();
			 
	}
		</script>
	</head>
	<body>
		<h2>CURD</h2>
		<form:form method="POST" name="form1"  action="/sdnext/save.html">
	   		<table>
	   			 <tr><a href="../../security/login/index.html">Go Home </a></tr>
			    <tr>
			        <%-- <td><form:label path="id">Employee ID:</form:label></td> --%>
			        <td><form:hidden path="id"  /></td>
			    </tr>
			    <tr>
			        <td><form:label path="name">Employee Name:</form:label></td>
			        <td><form:input path="name" value="${curd.name}" /></td>
			    </tr>
			    <tr>
			        <td><form:label path="age">Age:</form:label></td>
			        <td><form:input path="age" value="${curd.age}" /></td>
			    </tr>
			    <tr>
			    	<td> <input type="button" value="Submit1" onclick="goSave()"> </td>
			    	 <!-- <td colspan="2"><input type="submit" value="Submit"/></td> -->
		      </tr>
			</table> 
		</form:form>
		
  <c:if test="${!empty curdList}">
		<h2> Personal Information List</h2>
	<table align="left" border="1">
		<tr>
			<th>ID</th>
			<th>Age</th>
			<th>Name</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${curdList}" var="curd">
			<tr>
				<td><c:out value="${curd.id}"/></td>
				<td><c:out value="${curd.age}"/></td>
				<td><c:out value="${curd.name}"/></td>
				<td align="center"><a href="editCurd.html?id=${curd.id}">Edit</a> | <a href="deleteCurd.html?id=${curd.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
	</body>
</html>