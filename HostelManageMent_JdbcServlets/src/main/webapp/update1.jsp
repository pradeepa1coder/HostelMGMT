<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String paramValue = request.getParameter("paramName");
	int id = Integer.parseInt(paramValue);
	%>

	 <form action="updatebyid1" method="get">
        <label for="num"> Your ID Is :</label>
        <input type="number" name="num" value="<%=id%>" readonly><br>

        <label for="nm">Enter Your Name:</label>
        <input type="text" name="nm"><br>

        <label for="ph">Enter Your Phone:</label>
        <input type="number" name="ph"><br>

        <label for="psd">Enter Your email:</label>
        <input type="email" name="psd"><br>

        <input type="submit" value="Request">
    </form>

</body>
</html>