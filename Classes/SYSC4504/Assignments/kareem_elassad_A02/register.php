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
               <li><a class="active" href="index.php">Home</a></li>
               <li><a href="profile.php">Profile</a></li>
               <li><a href="register.php">Register</a></li>
               <li><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">Log Out</a></li>
            </ul>
         </td>
         <td class="align-body">
            <section>
               <h2>Register a new profile</h2>
               <!-- action="register.php" -->
               <form method="post" action="">
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
                                          <input class="s-r-buttons" type="submit" value="Register">
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
               <?php 
                  include('connection.php');
                  $db = new mysqli($server_name, $username, $password, $database_name);
                  if($_SERVER['REQUEST_METHOD'] == 'POST') {
                     $student_email = $_POST['student_email'];
                     $first_name = $_POST['first_name'];
                     $last_name = $_POST['last_name'];
                     $dob = $_POST['DOB'];
                     
     
                     $inst = "INSERT INTO kareem_elassad_syscbook.users_info (student_id, student_email, first_name, last_name, dob) VALUES ( 'NULL','$student_email', '$first_name', '$last_name', '$dob')";
                     $inst = $db->query($inst);
                     $student_id = $db->insert_id;
                     
                     $program = $_POST['Program'];
                     $prog_inst = "INSERT INTO kareem_elassad_syscbook.users_program (student_id, program) VALUES ( '$student_id', '$program')";
                     $prog_inst = $db->query($prog_inst);

                     $inst_empty1= "INSERT INTO kareem_elassad_syscbook.users_avatar (student_id, avatar) VALUES ( '$student_id', 'NULL')";
                     $inst_empty1 = $db->query($inst_empty1);

                     $inst_empty2= "INSERT INTO kareem_elassad_syscbook.users_address (student_id, street_number, street_name, city, provence, postal_code) VALUES ( '$student_id', 'NULL','NULL','NULL','NULL','NULL')";
                     $inst_empty2 = $db->query($inst_empty2);
                  }
               
               ?>
            </section>
         </td>
      </tr>
   </table>
</body>
<?php
   include('connection.php');
   $user_id = $_SESSION['user_id'];
   $user_info = "SELECT * FROM users WHERE user_id='$user_id'";
   $result = $db->query($user_info);
   $row = $result->fetch_assoc();
?>

</html>