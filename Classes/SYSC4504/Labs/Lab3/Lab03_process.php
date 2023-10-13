<?php
	if (isset($_GET["email_address"])){
		echo "<h1>Values were sent fromt he form using <em>GET</em> method</h1>";
		echo "<p><strong>Email Address: </strong>".$_GET["email_address"]."</p>";
		echo "<p><strong>Province: </strong>".$_GET["province"]."</p>";
		echo "<p><strong>Reason for contact: </strong>".$_GET["reason"]."</p>";
		echo "<p><strong>Question: </strong>".$_GET["question"]."</p>";
	}else if(isset($_POST["email_address"])){
		echo "<h1>Values were sent fromt he form using <em>POST</em> method</h1>";
		echo "<p><strong>Email Address: </strong>".$_POST["email_address"]."</p>";
		echo "<p><strong>Province: </strong>".$_POST["province"]."</p>";
		echo "<p><strong>Reason for contact: </strong>".$_POST["reason"]."</p>";
		echo "<p><strong>Question: </strong>".$_POST["question"]."</p>";
	}
?>