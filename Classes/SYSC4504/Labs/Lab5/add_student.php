<!-- create five fields NAME, DOB, INCOME, COURSE_ID, create a form using POST method -->

<!DOCTYPE html>
<html>
    <head>
        <title>Add Student</title>
    </head>
    <body>
        <?php 
            include('connection.php');

            $db = new mysqli($server_name, $username, $password, $database_name);
            
            if ($db->connect_error) {
                die('Connection failed: ' . $db->connect_error);
            }
                echo 'Connected successfully';

            if($_SERVER['REQUEST_METHOD'] == 'POST') {
                $id = $_POST['id'];
                $name = $_POST['name'];
                $dob = $_POST['dob'];
                $income = $_POST['income'];
                $course_id = $_POST['course_id'];

                $inst = "INSERT INTO STUDENT (id, name, dob, income, course_id) VALUES ('$id', '$name', '$dob', '$income', '$course_id')";
                $inst = $db->query($inst);
            }
            $sql_show = "SELECT * FROM STUDENT";
            $result = $db->query($sql_show);
            if ($result->num_rows > 0) {
                echo "<h2>Student Information</h2>";
                echo "<table>";
                echo "<tr><th>ID</th><th>Name</th><th>DOB</th><th>Income</th><th>Course ID</th></tr>";
                while($row = $result->fetch_assoc()) {
                    echo "<tr><td>".$row["ID"]."</td><td>".$row["NAME"]."</td><td>".$row["DOB"]."</td><td>".$row["INCOME"]."</td><td>".$row["COURSE_ID"]."</td></tr>";
                }
                echo "</table>";
            }
            $db->close();
        ?>    
        <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
            ID: <input type="number" name="id"><br>
            NAME: <input type="text" name="name"><br>
            DOB: <input type="date" name="dob"><br>
            INCOME: <input type="number" name="income"><br>
            COURSE_ID: <input type="number" name="course_id"><br>
            <input type="submit" value="Submit">
	    </form> 

    </body>
</html>