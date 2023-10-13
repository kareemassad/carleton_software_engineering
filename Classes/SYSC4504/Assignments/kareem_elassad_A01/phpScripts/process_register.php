<?php
	if (isset($_GET["first_name"])){
		echo "<h1>Values were sent fromt he form using <em>GET</em> method</h1>";
		echo "<h2>Personal information</h2>";
		echo "<p><strong>First Name: </strong>".$_GET["first_name"]."</p>";
		echo "<p><strong>Last Name: </strong>".$_GET["last_name"]."</p>";
		echo "<p><strong>Date of Birth: </strong>".$_GET["DOB"]."</p>";
		echo "<h2>Profile information</h2>";
		echo "<p><strong>Student Email: </strong>".$_GET["student_email"]."</p>";
		echo "<p><strong>Program: </strong>".$_GET["Program"]."</p>";
	}else if(isset($_POST["first_name"])){
		echo "<h1>Values were sent fromt he form using <em>POST</em> method</h1>";
		echo "<h2>Personal information</h2>";
		echo "<p><strong>First Name: </strong>".$_POST["first_name"]."</p>";
		echo "<p><strong>Last Name: </strong>".$_POST["last_name"]."</p>";
		echo "<p><strong>Date of Birth: </strong>".$_POST["DOB"]."</p>";
		echo "<h2>Profile information</h2>";
		echo "<p><strong>Student Email: </strong>".$_POST["student_email"]."</p>";
		echo "<p><strong>Program: </strong>".$_POST["Program"]."</p>";
	}
?>