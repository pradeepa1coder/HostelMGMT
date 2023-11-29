<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Welcome</h1>
	<form action="save" method="get">
        <label for="num">Enter Your ID:</label>
        <input type="number" name="num" required><br>

        <label for="nm">Enter Your Name:</label>
        <input type="text" name="nm" required><br>

        <label for="ph">Enter Your Phone:</label>
        <input type="number" name="ph" required><br>

        <label for="psd">Enter Your email:</label>
        <input type="email" name="psd" required><br>

        <input type="submit" value="Request">
    </form>
</body>
</html>