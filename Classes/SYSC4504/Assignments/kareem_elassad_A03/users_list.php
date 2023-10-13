<?php
session_start();
include_once("connection.php");

if (!isset($_SESSION["student_ID"])) {
    header("Location: login.php");
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Register on SYSCBOOK</title>
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
    <header>
        <h1>SYSCBOOK</h1>
        <p>Social media for SYSC students in Carleton University</p>
        <?php
        if (isset($_SESSION["student_ID"])) {
            echo "Logged in as: " . $_SESSION["first_name"] . " " . $_SESSION["last_name"] . " <br>";
        }
        ?>
    </header>
    <table class="main-table">
        <tr>
            <td class="navbar">
                <ul>
                    <li><a href="index.php">Home</a></li>
                    <li><a href="profile.php">Profile</a></li>
                    <li><a class="active" href="register.php">Register</a></li>
                    <li><a class="active" href="user_list.php">User Lists</a></li>
                    <li style=<?php echo (isset($_SESSION["account_type"]) ? (($_SESSION["account_type"] == 0) ? "display:none" : "") : "display:none") ?>><a href="users_list.php">User Lists</a></li>
                    <li onclick="location.href='login.php';" style="<?php echo (isset($_SESSION['student_id']) ? "" : "display:none") ?>"><a>Log Out</a></li>
                </ul>
            </td>
        </tr>
    </table>
    <main>
        <?php
        if ($_SESSION["account_type"] == 0) {
            include_once("connection.php");
            try {
                $db = new mysqli($server_name, $username, $password, $database_name);
                if ($db->connect_error) {
                    die("Connection failed: " . $db->connect_error);
                }
                // Users Info
                $query_string = 'SELECT users_info.student_ID, users_info.student_email, users_info.first_name, users_info.last_name, users_program.Program FROM kareem_elassad_syscbook.users_info INNER JOIN kareem_elassad_syscbook.users_program ON users_info.student_ID = users_program.student_ID;';
                $results = $db->query($query_string);

                $table_string = "
                <table class='AddGame'>
                   <caption>Student Table</caption>
                   <thead>
                       <tr>
                           <th>Student ID</th>
                           <th>First Name</th>
                           <th>Last Name</th>
                           <th>Student Email</th>
                           <th>Program</th>
                       <tr>
                   </thead>
                   <tbody>";

                while ($row = $results->fetch_assoc()) {
                    $table_string .= "<tr>";
                    $table_string .= "<td>" . $row['student_ID'] . "</td>";
                    $table_string .= "<td>" . $row['first_name'] . "</td>";
                    $table_string .= "<td>" . $row['last_name'] . "</td>";
                    $table_string .= "<td>" . $row['student_email'] . "</td>";
                    $table_string .= "<td>" . $row['Program'] . "</td>";
                    $table_string .= "</tr>";
                }
                $table_string .= "</tbody></table>";
                echo $table_string;

                $db->close();
            } catch (mysqli_sql_exception $e) {
                echo $e->getMessage();
            }
        } else {
            echo "You are not authorized to view this page.";
        }
        ?>
    </main>
</body>

</html>

<?php
include_once("connection.php");
$db = new mysqli($server_name, $username, $password, $database_name);

try {
    if (isset($_POST["submit"])) {
        $qs = 'INSERT INTO kareem_elassad_syscbook.users_posts (student_id, new_post, post_date) VALUES (' . $_SESSION["student_id"] . ', "' . $_POST["new_post"] . '", "' . date("Y-m-d") . '");';
        $results = $db->query($qs);
    } else {
        die("");
    }
    $db->close();
} catch (mysqli_sql_exception $e) {
    echo $e->getMessage();
}
header("Location: index.php");
exit();

?>