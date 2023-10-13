<?php
    include('connection.php');

    $db = new mysqli($server_name, $username, $password, $database_name);
    
    if ($db->connect_error) {
        die('Connection failed: ' . $db->connect_error);
    }
        echo 'Connected successfully';
    $db->close();
?>