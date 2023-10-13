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
                                 <input type="text" id="fname" name="first_name">
                              </td>
                              <td class="textbox">
                                 <label for="lname">Last Name:</label>
                                 <input type="text" id="lname" name="last_name">
                              </td>
                              <td class="textbox">
                                 <label for="birthday">DOB:</label>
                                 <input type="date" id="birthday" name="DOB">
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
                                 <input type="number" id="Street-Number" name="street_number"
                                    placeholder="Street Number">
                              </td>
                              <td class="Street-Name">
                                 <label for="postal">Street Name:</label>
                                 <input type="text" id="Street-Name" name="street_name" placeholder="Street Name">
                              </td>

                           </tr>
                        </table>
                        <table>
                           <tr>
                              <td class="textbox">
                                 <label for="city">City:</label>
                                 <input type="text" id="city" name="city" placeholder="City">
                              <td class="textbox">
                                 <label for="province">Province:</label>
                                 <input type="text" id="province" name="provence" placeholder="Province">
                              </td>
                              <td class="textbox">
                                 <label for="postal">Postal Code:</label>
                                 <input type="text" id="postal" name="postal_code" placeholder="Postal Code">
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
                                 <input type="email" id="email" name="student_email" placeholder="Email">
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
                                 <label>Choose your Avatar</label>
                                 <br>
                                 <table>
                                    <tr>
                                       <td>
                                          <input type="radio" name="avatar">
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar1.png" alt="avatar1">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar">
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar2.png" alt="avatar2">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar">
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar3.png" alt="avatar3">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar">
                                       </td>
                                       <td>
                                          <img class="avatars" src="images/img_avatar4.png" alt="avatar4">
                                       </td>
                                       <td>
                                          <input type="radio" name="avatar">
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
                                          <input class="s-r-buttons" type="submit" value="Submit">
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
      include('connection.php');
      $db = new mysqli($server_name, $username, $password, $database_name);
      if($_SERVER['REQUEST_METHOD'] == 'POST') {
         $student_email = $_POST['student_email'];
         $first_name = $_POST['first_name'];
         $last_name = $_POST['last_name'];
         $dob = $_POST['DOB'];
         $street_number = $_POST['street_number'];
         $street_name = $_POST['street_name'];
         $city = $_POST['city'];
         $province = $_POST['provence'];
         $postal_code = $_POST['postal_code'];
         $avatar = $_POST['avatar']; 

         $inst = "INSERT INTO kareem_elassad_syscbook.users_info (student_id, student_email, first_name, last_name, dob) VALUES ( 'NULL','$student_email', '$first_name', '$last_name', '$dob')";
         $inst = $db->query($inst);
         $student_id = $db->insert_id;
         
         $program = $_POST['Program'];
         $prog_inst = "INSERT INTO kareem_elassad_syscbook.users_program (student_id, program) VALUES ( '$student_id', '$program')";
         $prog_inst = $db->query($prog_inst);

         $inst_users_avatar= "INSERT INTO kareem_elassad_syscbook.users_avatar (student_id, avatar) VALUES ( '$student_id', '$avatar')";
         $inst_users_avatar = $db->query($inst_users_avatar);

         $inst_user_address= "INSERT INTO kareem_elassad_syscbook.users_address (student_id, street_number, street_name, city, provence, postal_code) VALUES ( '$student_id', '$street_number', '$street_name', '$city', '$province', '$postal_code')";
         $inst_user_address = $db->query($inst_user_address);
      }

   ?>

</body>

</html>