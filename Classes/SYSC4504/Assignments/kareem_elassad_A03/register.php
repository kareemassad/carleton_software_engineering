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
?>
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <title>Register on SYSCBOOK</title>
   <link rel="stylesheet" href="assets/css/reset.css">
   <link rel="stylesheet" href="assets/css/style.css">
</head>
<script>
   const validate = () => {
      const {
         value: password
      } = document.getElementById("password") ?? {};
      const {
         value: password_confirm
      } = document.getElementById("password_confirm") ?? {};
      const label_warning = document.getElementById("label_warning_password");

      if (password !== password_confirm) {
         event.preventDefault();
         label_warning.style.visibility = "visible";
         return false;
      } else {
         label_warning.style.visibility = "hidden";
      }
   };
</script>

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
               <li><a class="active" href="register.php">Register</a></li>
               <li style=<?php echo (isset($_SESSION["account_type"]) ? (($_SESSION["account_type"] == 0) ? "display:none" : "") : "display:none") ?>><a href="users_list.php">User Lists</a></li>
               <li onclick="location.href='login.php';" style="<?php echo (isset($_SESSION['student_id']) ? "" : "display:none") ?>"><a>Log Out</a></li>
            </ul>
         </td>
         <td class="align-body">
            <section>
               <h2>Register a new profile</h2>
               <!-- action="register.php" -->
               <form method="post" action="" onsubmit="return validate();">
                  <fieldset>
                     <legend>
                        <h1>Personal information</h1>
                     </legend>
                     <div class="section-content">
                        <table>
                           <tr>
                              <td class="textbox">
                                 <label>First Name:</label>
                                 <input type="text" name="first_name">
                              </td>
                              <td class="textbox">
                                 <label>Last Name:</label>
                                 <input type="text" name="last_name">
                              </td>
                              <td class="textbox">
                                 <label>DOB:</label>
                                 <input type="date" name="DOB">
                              </td>
                           </tr>
                        </table>
                        <fieldset>
                           <legend>
                              <h1>Profile Information</h1>
                           </legend>
                        </fieldset>
                        <table>
                           <tr>
                              <td class="textbox">
                                 <label for="email">Email:</label>
                                 <input type="email" id="email" name="student_email">
                              </td>
                           </tr>
                           <tr>
                              <td class="textbox">
                                 <label for="password">Password:</label>
                                 <input type="password" id="password" name="password">
                                 <input type="password" id="password_confirm" name="password_confirm">
                                 <label id="label_warning_password" style="visibility: hidden; color: red;">Passwords do
                                    not match</label>
                              </td>
                           </tr>
                           <tr>
                              <td class="textbox">
                                 <label>Program:</label>
                                 <select name="Program">
                                    <option value="Choose">Choose Program</option>
                                    <option value="COMPENG">Computer Systems Engineering</option>
                                    <option value="Software">Software Engineering</option>
                                    <option value="Comms">Communication Engineering</option>
                                    <option value="BioElec">Biomedical and Electrical</option>
                                    <option value="Elec">Electrical Engineering</option>
                                    <option value="Special">Special</option>
                                 </select>
                              </td>
                           </tr>
                           <tr>
                              <td class="textbox">
                                 <table class="button-spacing">
                                    <tr>
                                       <!-- add two buttons, submit and reset -->
                                       <td>
                                          <input class="s-r-buttons" type="submit" name="submit_register" value="Register">
                                       </td>
                                       <td>
                                          <input class="s-r-buttons" type="reset" value="Reset">
                                       </td>
                                    </tr>
                                 </table>
                              </td>
                           </tr>
                        </table>
                     </div>
                  </fieldset>
               </form>
            </section>
            <!-- if you already have an account add anchor to login.php  -->
            <p>Already have an account? <a href="login.php">Login Here</a></p>
         </td>
      </tr>
   </table>
</body>

</html>
<?php
try {
   include('connection.php');
   $db = new mysqli($server_name, $username, $password, $database_name);

   if (isset($_POST["submit_register"])) {
      $num_zero = 0;
      $null_string = NULL;
      //users id
      $users_id = $db->prepare("SELECT MAX(student_id) FROM users_info;");
      $users_id->execute();
      $res = $users_id->get_result();
      $row = $res->fetch_assoc();
      $_SESSION['student_id'] = $row['MAX(student_id)'];

      //users info
      $users_info = $db->prepare("INSERT INTO kareem_elassad_syscbook.users_info(student_email, first_name, last_name, dob) VALUES (?, ?, ?, DATE(?));");
      $users_info->bind_param("ssss", $_POST['student_email'], $_POST['first_name'], $_POST['last_name'], $_POST['DOB']);
      $users_info->execute();

      //Users programs
      $users_program = $db->prepare("INSERT INTO kareem_elassad_syscbook.users_program(student_id, program) VALUES (?, ?);");
      $users_program->bind_param("is", $_SESSION['student_id'], $_POST['Program']);
      $users_program->execute();

      // users address
      $users_address = $db->prepare("INSERT INTO kareem_elassad_syscbook.users_address(student_id, street_number, street_name, city, provence, postal_code) VALUES (?, ?, ?, ? ,?,?);");
      $users_address->bind_param("isssss", $_SESSION['student_id'], $num_zero, $null_string, $null_string, $null_string, $null_string);
      $users_address->execute();

      //user avatar (1-5)
      $users_avatar = $db->prepare("INSERT INTO kareem_elassad_syscbook.users_avatar(student_id, avatar) VALUES (?, ?);");
      $users_avatar->bind_param("ii", $_SESSION['student_id'], $num_zero);
      $users_avatar->execute();

      //user login
      $p_hash = password_hash($_POST['password'], PASSWORD_BCRYPT);
      $users_login = $db->prepare("INSERT INTO kareem_elassad_syscbook.users_passwords(student_id, password_hash) VALUES (?, ?);");
      $users_login->bind_param("is", $_SESSION['student_id'], $p_hash);
      $users_login->execute();

      //account type
      $account_type = 1;
      $user_perms = $db->prepare('INSERT INTO users_permissions (student_id, account_type) VALUES (?, ?);');
      $user_perms->bind_param("is", $_SESSION['student_id'], $account_type);
      $user_perms->execute();
      $_SESSION['account_type'] = $account_type;

      //re-set
      $_SESSION['first_name'] = $_POST['first_name'];
      $_SESSION['last_name'] = $_POST['last_name'];
      $_SESSION['student_email'] = $_POST['student_email'];
      $_SESSION['program'] = $_POST['Program'];
      $_SESSION['DOB'] = $_POST['DOB'];
      header("Location: profile.php");
   } else {
      echo "Error: " . $db->error;
   }
   $db->close();
} catch (Exception $e) {
   echo "Error: " . $e->getMessage();
}

exit(); ?>