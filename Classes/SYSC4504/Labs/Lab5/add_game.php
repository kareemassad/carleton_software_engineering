<?php 
    include('connection.php');

    $db = new mysqli($server_name, $username, $password, $database_name);
    
    if ($db->connect_error) {
        die('Connection failed: ' . $db->connect_error);
    }
        // echo 'Connected successfully';

    // create table if not exists
    $sql = "CREATE TABLE IF NOT EXISTS game_details (
        game_ID INT(10) AUTO_INCREMENT PRIMARY KEY,
        game_name VARCHAR(100) NOT NULL,
        release_date DATE NOT NULL,
        game_price DECIMAL(10,2) NOT NULL,
        game_description text NOT NULL
    )";

    if ($db->query($sql) === TRUE) {
        // echo "Table game_details created successfully";
    } else {
        echo "Error creating table: " . $db->error;
    }

    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $game_name = $_POST['game_name'];
        $release_date = $_POST['release_date'];
        $game_price = $_POST['game_price'];
        $game_description = $_POST['game_description'];

        $inst = "INSERT INTO game_details  (game_name, release_date, game_price, game_description) VALUES ( '$game_name', '$release_date', '$game_price', '$game_description')";
        $inst = $db->query($inst);
    }

?>    

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="images/Microsoft_logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <title>XBOX Fan Page</title>
</head>

<body>
    <header>
        <h1>Microsft Xbox</h1>
        <h2>by: Kareem El Assad</h2>
        <h2>Date: 2023-01-23</h2>
    </header>
    <nav>
        <a href="index.html">Home</a>
        <a href="catalog.html">Catalog</a>
        <a href="https://www.xbox.com/en-GB/">Company Website</a>
        <a href="contact.html">Contact Us</a>
        <a href="escape_js.html">Escape Room</a>
        <a href="add_game.php">Add Game</a>
    </nav>
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
        <fieldset>
        <legend>Add student information</legend>
            <label>Game Name: </label><input type="text" name="game_name"><br>
            <label>Release date: </label><input type="date" name="release_date"><br>
            <label>Price: </label><input type="text" name="game_price"><br>
            <label>Game Description: </label><br><textarea name="game_description" rows="4" cols="50" placeholder="max 500 characters"></textarea><br>
            <input type="submit" name="submit">
        </fieldset> 
    </form> 
    <?php
        $sql_show = "SELECT * FROM game_details ORDER BY release_date DESC";
        $result = $db->query($sql_show);
        if ($result->num_rows > 0) {
            echo "<h2>Student Information</h2>";
            echo "<table>";
            echo "<tr><th>game_ID</th><th>game_name</th><th>release_date</th><th>game_price</th><th>game_description</th></tr>";
            while($row = $result->fetch_assoc()) {
                echo "<tr><td>".$row["game_ID"]."</td><td>".$row["game_name"]."</td><td>".$row["release_date"]."</td><td>".$row["game_price"]."</td><td>".$row["game_description"]."</td></tr>";
            }
            echo "</table>";
        }
        $db->close();
    ?>
</body>

</html>