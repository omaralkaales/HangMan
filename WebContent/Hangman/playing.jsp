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
		<select name="guess" >
			  	<option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option>
			    <option value="E">E</option><option value="F">F</option><option value="G">G</option><option value="H">H</option>
			    <option value="I">I</option><option value="J">J</option><option value="K">K</option><option value="L">L</option>
			    <option value="M">M</option><option value="N">N</option><option value="O">O</option><option value="P">P</option>
			    <option value="Q">Q</option><option value="R">R</option><option value="S">S</option><option value="T">T</option>
			    <option value="U">U</option><option value="V">V</option><option value="W">W</option><option value="X">X</option>
		      	<option value="Y">Y</option><option value="Z">Z</option>
			</select>
			<input type="submit" value="Guess!"> 
	</form>
	
	<br><br>
	
	You chose: ${guess} ${alreadyChosen}<br>
	You have ${mistakesLeft} mistakes left, if zero you lose! <br>
	Result is: ${result}<br>








</body>
</html>