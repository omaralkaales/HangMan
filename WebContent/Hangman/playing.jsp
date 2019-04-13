<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HangMan</title>
</head>
<body>
	
	<form action="PlayingHangMan" method="post">
		<input list="alphabet" name="guess">
			<datalist id="alphabet">
			  	<option value="A"><option value="B"><option value="C"><option value="D">
			    <option value="E"><option value="F"><option value="G"><option value="H">
			    <option value="I"><option value="J"><option value="K"><option value="L">
			    <option value="M"><option value="N"><option value="O"><option value="P">
			    <option value="Q"><option value="R"><option value="S"><option value="T">
			    <option value="U"><option value="V"><option value="W"><option value="X">
		      	<option value="Y"><option value="Z">
			</datalist>
			
			<input type="submit" value="Guess!"> 
	</form>
	
	<br><br>
	<%
		String guess = (String) session.getAttribute("guess");
		if (guess != null){
			out.write("You chose: " +guess+"<br>");
		}
	%>
	
	You have ${mistakesLeft} mistakes left, if zero you lose! <br>
	Result is: ${result}<br>








</body>
</html>