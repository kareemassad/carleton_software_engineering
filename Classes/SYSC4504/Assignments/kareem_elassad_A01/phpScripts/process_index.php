<?php
	if (isset($_GET["new_post"])){
		echo "<h1>Values were sent from the form using <em>GET</em> method</h1>";
		echo "<p><strong>The new post is: </strong>".$_GET["new_post"]."</p>";
	}else if(isset($_POST["new_post"])){
		echo "<h1>Values were sent from the form using <em>POST</em> method</h1>";
		echo "<p><strong>The new post is: </strong>".$_POST["new_post"]."</p>";
	}
?>