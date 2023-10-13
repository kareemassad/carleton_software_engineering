<?php
	if (isset($_GET["first_name"])){
		echo "<h1>Values were sent fromt he form using <em>GET</em> method</h1>";
		echo "<h2>Personal information</h2>";
		echo "<p><strong>First Name: </strong>".$_GET["first_name"]."</p>";
		echo "<p><strong>Last Name: </strong>".$_GET["last_name"]."</p>";
		echo "<p><strong>Date of Birth: </strong>".$_GET["DOB"]."</p>";
		echo "<h2>Address</h2>";
		echo "<p><strong>Street Number: </strong>".$_GET["street_number"]."</p>";
		echo "<p><strong>Street Name: </strong>".$_GET["street_name"]."</p>";
		echo "<p><strong>City: </strong>".$_GET["city"]."</p>";
		echo "<p><strong>Provence: </strong>".$_GET["provence"]."</p>";
		echo "<p><strong>Postal Code: </strong>".$_GET["postal_code"]."</p>";
		echo "<h2>Profile information</h2>";
		echo "<p><strong>Student Email: </strong>".$_GET["student_email"]."</p>";
		echo "<p><strong>Program: </strong>".$_GET["Program"]."</p>";
		echo "<p><strong>Avatar: </strong>".$_GET["avatar"]."</p>";
	}else if(isset($_POST["first_name"])){
		echo "<h1>Values were sent fromt he form using <em>POST</em> method</h1>";
		echo "<h2>Personal information</h2>";
		echo "<p><strong>First Name: </strong>".$_POST["first_name"]."</p>";
		echo "<p><strong>Last Name: </strong>".$_POST["last_name"]."</p>";
		echo "<p><strong>Date of Birth: </strong>".$_POST["DOB"]."</p>";
		echo "<h2>Address</h2>";
		echo "<p><strong>Street Number: </strong>".$_POST["street_number"]."</p>";
		echo "<p><strong>Street Name: </strong>".$_POST["street_name"]."</p>";
		echo "<p><strong>City: </strong>".$_POST["city"]."</p>";
		echo "<p><strong>Provence: </strong>".$_POST["provence"]."</p>";
		echo "<p><strong>Postal Code: </strong>".$_POST["postal_code"]."</p>";
		echo "<h2>Profile information</h2>";
		echo "<p><strong>Student Email: </strong>".$_POST["student_email"]."</p>";
		echo "<p><strong>Program: </strong>".$_POST["Program"]."</p>";
		echo "<p><strong>Avatar: </strong>".$_POST["avatar"]."</p>";
	}
?>