<!-- create five fields NAME, DOB, INCOME, COURSE_ID, create a form using POST method -->

<!DOCTYPE html>
<html>
    <head>
        <title>Add Student</title>
    </head>
    <body>
        <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
            ID: <input type="number" name="id"><br>
            NAME: <input type="text" name="name"><br>
            DOB: <input type="date" name="dob"><br>
            INCOME: <input type="number" name="income"><br>
            COURSE_ID: <input type="number" name="course_id"><br>
            <input type="submit" value="Submit">
	    </form> 

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

                echo "\n COURSE RECORD CREATEAD SUCCESSFULLY!!! \n";
                echo "\n ID: " . $id;
                echo "\n NAME: " . $name;
                echo "\n DOB: " . $dob;
                echo "\n COURSE_ID: " . $course_id;
                echo "\n INCOME: " . $income;
                //show most recent record
                // $sql_show = "SELECT * FROM STUDENT";
                // $result = $db->query($sql_show);
                // $row = $result->fetch_assoc();
                // echo "COURSE RECORD CREATEAD SUCCESSFULLY!!!";
                // echo "ID: " . $row["id"];
                // echo "NAME: " . $row["name"];
                // echo "DOB: " . $row["dob"];
                // echo "COURSE_ID: " . $row["course_id"];
                // echo "INCOME: " . $row["income"];
            }
            
            $db->close();
        ?>    
    </body>
</html>