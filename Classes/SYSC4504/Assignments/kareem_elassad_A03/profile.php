<?php
session_start();
include_once("connection.php");

if (!isset($_SESSION["student_id"])) {
   header("Location: login.php");
} else {
   try {
      $db = new mysqli($server_name, $username, $password, $database_name);

      if (isset($_POST["submit_register"])) {
         unset($_POST["submit_profile"]);


         //users info
         $users_info = $db->prepare('SELECT * FROM kareem_elassad_syscbook.users_info WHERE student_id = ' . $_SESSION["student_id"] . ';');
         $users_info->execute();
         $res = $users_info->get_result();
         $row = $res->fetch_assoc();
         $_SESSION['student_email'] = $row['student_email'];
         $_SESSION['first_name'] = $row['first_name'];
         $_SESSION['last_name'] = $row['last_name'];
         $_SESSION['DOB'] = $row['DOB'];

         //users address
         $users_address = $db->prepare('SELECT * FROM kareem_elassad_syscbook.users_address WHERE student_id = ' . $_SESSION["student_id"] . ';');
         $users_address->execute();
         $res = $users_address->get_result();
         $row = $res->fetch_assoc();
         $_SESSION['street_number'] = $row['street_number'];
         $_SESSION['street_name'] = $row['street_name'];
         $_SESSION['city'] = $row['city'];
         $_SESSION['provence'] = $row['provence'];
         $_SESSION['postal_code'] = $row['postal_code'];

         //users program
         $users_program = $db->prepare('SELECT * FROM kareem_elassad_syscbook.users_program WHERE student_id = ' . $_SESSION["student_id"] . ';');
         $users_program->execute();
         $res = $users_program->get_result();
         $row = $res->fetch_assoc();
         $_SESSION['Program'] = $row['Program'];

         //users avatar
         $users_avatar = $db->prepare('SELECT * FROM kareem_elassad_syscbook.users_avatar WHERE student_id = ' . $_SESSION["student_id"] . ';');
         $users_avatar->execute();
         $res = $users_avatar->get_result();
         $row = $res->fetch_assoc();
         $_SESSION['avatar'] = $row['avatar'];
      } else if (isset($_POST["submit_profile"])) {
         unset($_POST["submit_register"]);

         //users info
         $_SESSION['first_name'] = $_POST['first_name'];
         $_SESSION['last_name'] = $_POST['last_name'];
         $_SESSION['DOB'] = $_POST['DOB'];
         $_SESSION['student_email'] = $_POST['student_email'];

         //users address
         $_SESSION['street_number'] = $_POST['street_number'];
         $_SESSION['street_name'] = $_POST['street_name'];
         $_SESSION['city'] = $_POST['city'];
         $_SESSION['provence'] = $_POST['provence'];
         $_SESSION['postal_code'] = $_POST['postal_code'];

         //users program
         $_SESSION['Program'] = $_POST['Program'];

         //users avatar
         $_SESSION['avatar'] = $_POST['avatar'];

         //user permissions
         $_SESSION['account_type'] = $_POST['account_type'];
      } else {
         $student_id = $_SESSION["student_id"];
         // Prepare statement to select student info

         $sql_student_info = $db->prepare("SELECT first_name, last_name, DOB, student_email FROM kareem_elassad_syscbook.users_info WHERE student_id = ?");
         // echo "im here <br>";
         $sql_student_info->bind_param("i", $student_id);
         $sql_student_info->execute();
         $res_student_info = $sql_student_info->get_result();
         $row_student_info = $res_student_info->fetch_assoc();

         //avatar
         $sql_student_avatar = $db->prepare("SELECT avatar FROM kareem_elassad_syscbook.users_avatar WHERE student_id = ?");
         $sql_student_avatar->bind_param("i", $student_id);
         $sql_student_avatar->execute();
         $res_student_avatar = $sql_student_avatar->get_result();
         $row_student_avatar = $res_student_avatar->fetch_assoc();

         // Prepare statement to select student address
         $sql_student_address = $db->prepare("SELECT street_number, street_name, city, provence, postal_code FROM kareem_elassad_syscbook.users_address WHERE student_id = ?");
         $sql_student_address->bind_param("i", $student_id);
         $sql_student_address->execute();
         $res_student_address = $sql_student_address->get_result();
         $row_student_address = $res_student_address->fetch_assoc();

         // Prepare statement to select student program and account type
         $sql_student_program = $db->prepare("SELECT Program, account_type FROM kareem_elassad_syscbook.users_program INNER JOIN kareem_elassad_syscbook.users_permissions ON users_program.student_id = users_permissions.student_id WHERE users_program.student_id = ?");
         $sql_student_program->bind_param("i", $student_id);
         $sql_student_program->execute();
         $res_student_program = $sql_student_program->get_result();
         $row_student_program = $res_student_program->fetch_assoc();

         // Store retrieved data in session variables
         $_SESSION['first_name'] = $row_student_info['first_name'];
         $_SESSION['last_name'] = $row_student_info['last_name'];
         $_SESSION['DOB'] = $row_student_info['DOB'];
         $_SESSION['student_email'] = $row_student_info['student_email'];
         $_SESSION['street_number'] = $row_student_address['street_number'];
         $_SESSION['street_name'] = $row_student_address['street_name'];
         $_SESSION['city'] = $row_student_address['city'];
         $_SESSION['provence'] = $row_student_address['provence'];
         $_SESSION['postal_code'] = $row_student_address['postal_code'];
         $_SESSION['Program'] = $row_student_program['Program'];
         $_SESSION['account_type'] = $row_student_program['account_type'];
         $_SESSION['avatar'] = $row_student_avatar['avatar'];
      }
      $db->close();
   } catch (mysqli_sql_exception $e) {
      //throw $th;
      echo $e->getMessage();
   }
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <title>Update SYSCBOOK profile</title>
   <link rel="stylesheet" href="assets/css/reset.css">
   <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
   <header>
      <h1>SYSCBOOK</h1>
      <p>Social media for SYSC students in Carleton University</p>
      <?php
      if (isset($_SESSION['student_id'])) {
         // checker
         echo "<p>Logged in as: " . $_SESSION['first_name'] . " " . $_SESSION["last_name"] . "</p>";
      }
      ?>
   </header>

   <table class="main-table">
      <tr>
         <td class="navbar">
            <ul>
               <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a href="index.php">Home</a></li>
               <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a class="active" href="profile.php">Profile</a></li>
               <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a href="register.php">Register</a></li>
               <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a>Login</a></li>
               <li style=<?php echo (isset($_SESSION["account_type"]) ? (($_SESSION["account_type"] == 0) ? "display:none" : "") : "display:none") ?>><a href="users_list.php">User Lists</a></li>
               <li onclick="location.href='login.php';" style="<?php echo (isset($_SESSION['student_id']) ? "" : "display:none") ?>"><a>Log Out</a></li>
            </ul>
         </td>
         <td class="align-body">
            <section>
               <h2>Update Profile information</h2>
               <form method="post" action="profile.php">
                  <fieldset>
                     <legend>
                        <h1>Personal information</h1>
                     </legend>
                     <div class="section-content">
                        <table>
                           <tr>
                              <td class="textbox">
                                 <label for="fname">First Name:</label>
                                 <input type="text" id="fname" name="first_name" value=<?php echo '"' . (isset($_SESSION["first_name"])) ? $_SESSION["first_name"] : "" . '"' ?>>
                              </td>
                              <td class="textbox">
                                 <label for="lname">Last Name:</label>
                                 <input type="text" id="lname" name="last_name" value=<?php echo '"' . (isset($_SESSION["last_name"])) ? $_SESSION["last_name"] : "" . '"' ?>>
                              </td>
                              <td class="textbox">
                                 <label for="birthday">DOB:</label>
                                 <input type="date" id="birthday" name="DOB" value=<?php echo '"' . (isset($_SESSION["DOB"])) ? $_SESSION["DOB"] : "" . '"' ?>>
                              </td>
                           </tr>
                        </table>
                        <fieldset>
                           <legend>
                              <h1>Address</h1>
                           </legend>
                        </fieldset>
                        <table>
                           <tr>
                              <td class="textbox">
                                 <label for="Street-Number">Street Number:</label>
                                 <input type="number" id="Street-Number" name="street_number" placeholder="Street Number" value=<?php echo '"' . (isset($_SESSION["street_number"])) ? $_SESSION["street_number"] : "" . '"' ?>>
                              </td>
                              <td class="Street-Name">
                                 <label for="postal">Street Name:</label>
                                 <input type="text" id="Street-Name" name="street_name" placeholder="Street Name" value=<?php echo '"' . (isset($_SESSION["street_name"])) ? $_SESSION["street_name"] : "" . '"' ?>>
                              </td>
                           </tr>
                        </table>
                        <table>
                           <tr>
                              <td class="textbox">
                                 <label for="city">City:</label>
                                 <input type="text" id="city" name="city" placeholder="City" value=<?php echo '"' . (isset($_SESSION["city"])) ? $_SESSION["city"] : "" . '"' ?>>
                              <td class="textbox">
                                 <label for="province">Province:</label>
                                 <input type="text" id="province" name="provence" placeholder="Province" value=<?php echo '"' . (isset($_SESSION["provence"])) ? $_SESSION["provence"] : "" . '"' ?>>
                              </td>
                              <td class="textbox">
                                 <label for="postal">Postal Code:</label>
                                 <input type="text" id="postal" name="postal_code" placeholder="Postal Code" value=<?php echo '"' . (isset($_SESSION["postal_code"])) ? $_SESSION["postal_code"] : "" . '"' ?>>
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
                                 <input type="email" id="email" name="student_email" placeholder="Email" value=<?php echo '"' . (isset($_SESSION["student_email"])) ? $_SESSION["student_email"] : "" . '"' ?>>
                              </td>
                           </tr>
                           <tr>
                              <td class="textbox">
                                 <label>Program:</label>
                                 <select name="Program">
                                    <option value="BLANK" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "BLANK")) ? "selected" : "") : "" . '"' ?>>Choose Program</option>
                                    <option value="Computer Systems Engineering" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "Computer Systems Engineering")) ? "selected" : "") : "" . '"' ?>>Computer Systems Engineering</option>
                                    <option value="Software Engineering" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "Software Engineering")) ? "selected" : "") : "" . '"' ?>>Software Engineering</option>
                                    <option value="Communications Engineering" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "Communications Engineering")) ? "selected" : "") : "" . '"' ?>>Communications Engineering</option>
                                    <option value="Biomedical and Electrical" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "Biomedical and Electrical")) ? "selected" : "") : "" . '"' ?>>Biomedical and Electrical</option>
                                    <option value="Electrical Engineering" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "Electrical Engineering")) ? "selected" : "") : "" . '"' ?>>Electrical Engineering</option>
                                    <option value="Special" <?php echo '"' . (isset($_SESSION["Program"])) ? ((($_SESSION["Program"] == "Special")) ? "selected" : "") : "" . '"' ?>>Special</option>
                                 </select>
                              </td>
                           </tr>
                           <tr>
                              <td class="textbox">
                                 <label>Choose your Avatar</label>
                                 <br>
                                 <table>
                                    <tr>
                                       <td>
                                          <input type="radio" name="avatar" value=1 <?php echo '"' . (isset($_SESSION["avatar"])) ? ((($_SESSION["avatar"] == 1)) ? "checked" : "") : "" . '"' ?>>
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar1.png" alt="avatar1">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar" value=2 <?php echo '"' . (isset($_SESSION["avatar"])) ? ((($_SESSION["avatar"] == 2)) ? "checked" : "") : "" . '"' ?>>
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar2.png" alt="avatar2">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar" value=3 <?php echo '"' . (isset($_SESSION["avatar"])) ? ((($_SESSION["avatar"] == 3)) ? "checked" : "") : "" . '"' ?>>
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar3.png" alt="avatar3">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar" value=4 <?php echo '"' . (isset($_SESSION["avatar"])) ? ((($_SESSION["avatar"] == 4)) ? "checked" : "") : "" . '"' ?>>
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar4.png" alt="avatar4">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar" value=5 <?php echo '"' . (isset($_SESSION["avatar"])) ? ((($_SESSION["avatar"] == 5)) ? "checked" : "") : "" . '"' ?>>
                                       </td>
                                       <td>
                                          <img class=" avatars" src="images/img_avatar5.png" alt="avatar5">
                                       </td>
                                    </tr>
                                 </table>
                                 <table class="button-spacing">
                                    <tr>
                                       <!-- add two buttons, submit and reset -->
                                       <td>
                                          <input class="s-r-buttons" type="submit" name="submit_profile" value="Submit">
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
         </td>
      </tr>
   </table>
   <?php
   try {

      include_once('connection.php');
      $db = new mysqli($server_name, $username, $password, $database_name);

      if ($db->connect_error) {
         die("Connection failed: " . $db->connect_error);
      }

      if (isset($_POST["submit_profile"])) {
         $student_email = $_POST["student_email"];
         $first_name = $_POST["first_name"];
         $last_name = $_POST["last_name"];
         $dob = $_POST["DOB"];
         $student_id = $_SESSION["student_id"];
         $program = $_POST["Program"];
         $avatar = $_POST["avatar"];
         $street_number = $_POST["street_number"];
         $street_name = $_POST["street_name"];
         $city = $_POST["city"];
         $provence = $_POST["provence"];
         $postal_code = $_POST["postal_code"];

         $sql_update_userinfo = $db->prepare('UPDATE kareem_elassad_syscbook.users_info SET student_email = ?, first_name = ?, last_name = ?, DOB = DATE(?) WHERE student_id = ?;');
         $sql_update_userinfo->bind_param('ssssi', $student_email, $first_name, $last_name, $dob, $student_id);
         $sql_update_userinfo->execute();

         $sql_update_userprogram = $db->prepare('UPDATE kareem_elassad_syscbook.users_program SET Program = ? WHERE student_id = ?;');
         $sql_update_userprogram->bind_param('si', $program, $student_id);
         $sql_update_userprogram->execute();

         $sql_update_useraddress = $db->prepare('UPDATE kareem_elassad_syscbook.users_address SET street_number = ?, street_name = ?, city = ?, provence = ?, postal_code = ? WHERE student_id = ?;');
         $sql_update_useraddress->bind_param('issssi', $street_number, $street_name, $city, $provence, $postal_code, $student_id);
         $sql_update_useraddress->execute();

         $sql_update_useravatar = $db->prepare('UPDATE kareem_elassad_syscbook.users_avatar SET avatar = ? WHERE student_id = ?;');
         $sql_update_useravatar->bind_param('ii', $avatar, $student_id);
         $sql_update_useravatar->execute();

         //session set
         $_SESSION['student_email'] = $_POST['student_email'];
         $_SESSION['first_name'] = $_POST['first_name'];
         $_SESSION['last_name'] = $_POST['last_name'];
         $_SESSION['DOB'] = $_POST['DOB'];
         $_SESSION['Program'] = $_POST['Program'];
         $_SESSION['avatar'] = $_POST['avatar'];
         $_SESSION['street_number'] = $_POST['street_number'];
         $_SESSION['street_name'] = $_POST['street_name'];
         $_SESSION['city'] = $_POST['city'];
         $_SESSION['provence'] = $_POST['provence'];
         $_SESSION['postal_code'] = $_POST['postal_code'];
         // $_SESSION["account_type"] = $_POST["account_type"];
      }

      $db->close();
   } catch (mysqli_sql_exception $e) {
      echo $e->getMessage();
   }
   ?>

</body>

</html>