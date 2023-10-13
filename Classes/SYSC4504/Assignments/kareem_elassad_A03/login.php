<?php
session_start();
include_once("connection.php");
//unset session otherwise it'll stay
unset($_SESSION['student_id']);
unset($_SESSION['street_number']);
unset($_SESSION['street_name']);
unset($_SESSION['city']);
unset($_SESSION['province']);
unset($_SESSION['postal_code']);
// unset($_SESSION['first_name']);
// unset($_SESSION['last_name']);
// unset($_SESSION['DOB']);
// unset($_SESSION['student_email']);
unset($_SESSION['Program']);
unset($_SESSION['avatar']);
unset($_SESSION['account_type']);
unset($_SESSION['submit_login']);
unset($_SESSION['submit_register']);
unset($_SESSION['submit_profile'])
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
    </header>
    <table class="main-table">
        <tr>
            <td class="navbar">
                <ul>
                    <li><a href="index.php">Home</a></li>
                    <li><a href="profile.php">Profile</a></li>
                    <li><a href="register.php">Register</a></li>
                    <li><a class="active">Login</a></li>
                    <li style=<?php echo (isset($_SESSION["account_type"]) ? (($_SESSION["account_type"] == 0) ? "display:none" : "") : "display:none") ?>><a href="user_list.php">User Lists</a></li>
                    <li onclick="location.href='login.php';" style="<?php echo (isset($_SESSION['student_id']) ? "" : "display:none") ?>"></li>
                </ul>
            </td>
            <td class="align-body">
                <section>
                    <!-- action="register.php" -->
                    <form method="post" action="">
                        <fieldset>
                            <legend>
                                <h1>Login</h1>
                            </legend>
                            <!-- <div class="section-content"> -->
                            <table>
                                <tr>
                                    <td class="textbox">
                                        <label>Email Address:</label>
                                        <input type="text" name="student_email">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="textbox">
                                        <label>Password:</label>
                                        <input type="password" name="password">
                                    </td>
                                </tr>
                                <tr>
                                    <!-- add submit and reset buttons -->
                                    <td class="textbox">
                                        <input type="submit" name="submit_login" value="Login">
                                        <input type="reset" name="reset" value="Reset">
                                    </td>
                                </tr>
                            </table>
                            <!-- </div> -->
                        </fieldset>
                    </form>
                </section>
                <p>Don't have an account? <a href="register.php">Register Here</a></p>
            </td>
        </tr>
    </table>
</body>

</html>
<?php
include_once("connection.php");
try {
    $db = new mysqli($server_name, $username, $password, $database_name);
    if (isset($_POST["submit_login"])) {
        //users id
        $users_id_email = $db->prepare('SELECT student_id FROM kareem_elassad_syscbook.users_info WHERE student_email = ?;');
        $users_id_email->bind_param('s', $_POST['student_email']);
        $users_id_email->execute();

        $res = $users_id_email->get_result();
        $row = $res->fetch_assoc();
        $_SESSION['student_id'] = $row['student_id'];

        $student_id = $_SESSION['student_id'];
        //password
        $password = $db->prepare('SELECT password_hash FROM kareem_elassad_syscbook.users_passwords WHERE student_id = ?;');
        $password->bind_param('i', $_SESSION['student_id']);
        $password->execute();
        $res = $password->get_result();
        $row = $res->fetch_assoc();
        $p_hash = $row['password_hash'];



        //verify pass
        if (password_verify($_POST['password'], $p_hash)) {
            echo "im here <br>";
            // Get student info
            $sql_student = $db->prepare("SELECT student_id, first_name, last_name, DOB, student_email FROM kareem_elassad_syscbook.users_info WHERE student_id = ?");
            $sql_student->bind_param('i', $student_id);
            $sql_student->execute();
            $res_student = $sql_student->get_result();
            $row_student = $res_student->fetch_assoc();

            // Get student address
            $sql_address = $db->prepare("SELECT street_number, street_name, city, provence, postal_code FROM kareem_elassad_syscbook.users_address WHERE student_id = ?");
            $sql_address->bind_param('i', $student_id);
            $sql_address->execute();
            $res_address = $sql_address->get_result();
            $row_address = $res_address->fetch_assoc();

            // Get student program
            $sql_program = $db->prepare("SELECT Program FROM kareem_elassad_syscbook.users_program WHERE student_id = ?");
            $sql_program->bind_param('i', $student_id);
            $sql_program->execute();
            $res_program = $sql_program->get_result();
            $row_program = $res_program->fetch_assoc();

            // Get student permissions
            $sql_permissions = $db->prepare("SELECT account_type FROM kareem_elassad_syscbook.users_permissions WHERE student_id = ?");
            $sql_permissions->bind_param('i', $student_id);
            $sql_permissions->execute();
            $res_permissions = $sql_permissions->get_result();
            $row_permissions = $res_permissions->fetch_assoc();

            // Get student avatar
            $sql_avatar = $db->prepare("SELECT avatar FROM kareem_elassad_syscbook.users_avatar WHERE student_id = ?");
            $sql_avatar->bind_param('i', $student_id);
            $sql_avatar->execute();
            $res_avatar = $sql_avatar->get_result();
            $row_avatar = $res_avatar->fetch_assoc();

            // Set session variables
            $_SESSION['student_id'] = $row_student['student_id'];
            $_SESSION['first_name'] = $row_student['first_name'];
            $_SESSION['last_name'] = $row_student['last_name'];
            $_SESSION['student_email'] = $row_student['student_email'];
            $_SESSION['account_type'] = $row_permissions['account_type'];

            // Redirect to index page
            header("Location: index.php");
        }
    } else {
        die("");
    }
} catch (mysqli_sql_exception $e) {
    echo "Error: " . $e->getMessage();
}
exit();

?>