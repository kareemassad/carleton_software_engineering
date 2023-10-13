<?php
include_once("connection.php");
session_start();

if (!isset($_SESSION['student_id'])) {
   header("Location: login.php");
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <title>SYSCBOOK - Main</title>
   <link rel="stylesheet" href="assets/css/reset.css">
   <link rel="stylesheet" href="assets/css/style.css">
</head>

<body>
   <div class="main-content">
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
                  <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a class="active" href="index.php">Home</a></li>
                  <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a href="profile.php">Profile</a></li>
                  <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a href="register.php">Register</a></li>
                  <li style=<?php echo (isset($_SESSION["student_id"]) ? "" : "display:none") ?>><a>Login</a></li>
                  <li style=<?php echo (isset($_SESSION["account_type"]) ? (($_SESSION["account_type"] == 0) ? "display:none" : "") : "display:none") ?>><a href="users_list.php">User Lists</a></li>
                  <li onclick="location.href='login.php';" style="<?php echo (isset($_SESSION['student_id']) ? "" : "display:none") ?>"></li>
               </ul>
            </td>
         </tr>
      </table>
      <main>
         <?php if (isset($_SESSION['student_id'])) {
            echo '
            <section>
               <form method="post" action="">
                  <legend><p>New Post</p></legend>
                  <textarea id= "new_post" name="new_post" cols="50" rows="5" maxlength="300"
                     placeholder="What\'s on your mind? Max 300 char!"></textarea>
                  <div>
                     <button name="submit" type="submit">Post</button>
                     <button type="reset">Reset</button>
                  </div>
               </form>
            </section>
            ';
         } ?>
         <?php
         include_once('connection.php');
         try {
            $db = new mysqli($server_name, $username, $password, $database_name);

            if ($db->connect_error) {
               die("Connection failed: " . $db->connect_error);
            }

            // Get user info and avatar
            // $user_query = $db->prepare("SELECT users_info.student_id, users_info.first_name, users_info.last_name, users_avatar.avatar FROM kareem_elassad_syscbook.users_info INNER JOIN kareem_elassad_syscbook.users_avatar ON users_info.student_id = users_avatar.student_id WHERE users_info.student_id = ?;");
            // $user_query->bind_param('i', $student_id);
            // $user_query->execute();
            // $user_result = $user_query->get_result();
            // $user_row = $user_result->fetch_assoc();


            // Get 10 posts from the database
            $post_query = $db->prepare("SELECT users_posts.post_ID, users_posts.new_post, users_posts.post_date, users_info.first_name, users_info.last_name 
            FROM kareem_elassad_syscbook.users_posts 
            INNER JOIN kareem_elassad_syscbook.users_info ON users_posts.student_id = users_info.student_id
            ORDER BY post_ID DESC 
            LIMIT 10;
            ");
            // $post_query->bind_param('i', $student_id);
            $post_query->execute();
            $post_result = $post_query->get_result();

            $posts = "";

            // Loop through the posts and display them
            while ($post_row = $post_result->fetch_assoc()) {
               // Commented out this line since it is not being used
               // $user_name = ($user_row !== null) ? $user_row['first_name'] . ' ' . $user_row['last_name'] : '';
               $posts .= '
             <details open class="post">
                <summary> <b>' . $post_row['first_name'] . ' ' . $post_row['last_name'] . '</b></summary>
                <p class="p-post">' . $post_row['new_post'] . '</p>
                <p class="p-post">' . $post_row['post_date'] . '</p>
             </details>
             ';
            }

            echo $posts;
            $db->close();
         } catch (Exception $e) {
            // echo "Error: " . $e->getMessage();
         }
         ?>

      </main>
   </div>
</body>

</html>
<!-- <td class="align-body">
               <section>
                  <form method="post" action="index.php">
                     <fieldset>
                        <legend>
                           <h1>New Post</h1>
                        </legend>
                        <div class="input-post">
                           <table>
                              <tr>
                                 <td class="textbox">
                                    <textarea name="new_post" cols="50" rows="5" maxlength="300"
                                       placeholder="What's on your mind? Max 300 char!"></textarea>
                                 </td>
                              </tr>
                           </table>
                           <table>
                              <tr>
                                 <td class="align-table"><button type="submit">Post</button></td>
                                 <td class="align-table2"><button type="reset">Reset</button></td>
                              </tr>
                           </table>
                        </div>
                     </fieldset>
                  </form>
               </section>

               <table>
                  <tr>
                     <td>
                        <details open class="post">
                           <summary> <b>Post 1</b></summary>
                           <p class="p-post">Lorem ipsum dolor sit amet consectetur, adipisicing elit. At, inventore
                              dolorum!Totam adipisci nostrum vel tempora aliquam quidem? Nulla sit nobis voluptatem
                              consequatur obcaecati similique explicabo
                              velit debitis, quaerat quod? Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                              Ullam,
                              vero! Vero possimus veritatis mollitia, id minima perspiciatis enim perferendis sunt
                              corporis
                              fuga ratione repudiandae, nulla aspernatur quibusdam modi tempora inventore. Lorem ipsum
                              dolor sit amet consectetur, adipisicing elit. Fuga laborum cupiditate asperiores itaque
                              veritatis voluptatem provident quae et amet, nam nisi magni. Explicabo distinctio odit
                              officiis modi quis! Unde, eaque? Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                              Voluptatem enim nihil facere odit, quidem quia voluptate iure corrupti asperiores
                              obcaecati soluta vitae sunt aliquid? Dicta earum rerum ad quas repellendus. Lorem ipsum
                              dolor sit amet consectetur adipisicing elit. Quas facere est, labore similique officia
                              molestiae necessitatibus voluptatum, nulla harum ipsum fugit qui maiores autem
                              voluptatibus omnis atque perferendis ut ratione?
                           </p>
                        </details>
                     </td>
                  </tr>
                  <tr>
                     <td>
                        <details open class="post">
                           <summary><b>Post 2</b></summary>
                           <p class="p-post">Lorem ipsum dolor sit amet consectetur, adipisicing elit. At, inventore
                              Lorem ipsum dolor sit amet consectetur, adipisicing elit. At, inventore
                              dolorum!Totam adipisci nostrum vel tempora aliquam quidem? Nulla sit nobis voluptatem
                              consequatur obcaecati similique explicabo
                              velit debitis, quaerat quod? Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                              Ullam,
                              vero! Vero possimus veritatis mollitia, id minima perspiciatis enim perferendis sunt
                              corporis
                              fuga ratione repudiandae, nulla aspernatur quibusdam modi tempora inventore. Lorem ipsum
                              dolor sit amet consectetur, adipisicing elit. Fuga laborum cupiditate asperiores itaque
                              veritatis voluptatem provident quae et amet, nam nisi magni. Explicabo distinctio odit
                              officiis modi quis! Unde, eaque? Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                              Voluptatem enim nihil facere odit, quidem quia voluptate iure corrupti asperiores
                              obcaecati soluta vitae sunt aliquid? Dicta earum rerum ad quas repellendus. Lorem ipsum
                              dolor sit amet consectetur adipisicing elit. Quas facere est, labore similique officia
                              molestiae necessitatibus voluptatum, nulla harum ipsum fugit qui maiores autem
                              voluptatibus omnis atque perferendis ut ratione?</p>

                        </details>
                     </td>
                  </tr>
                  <tr>
                     <td>
                        <details open class="post">
                           <summary><b>Post 3</b></summary>
                           <p class="p-post">Lorem ipsum dolor sit amet consectetur, adipisicing elit. At, inventore
                              dolorum!
                              Totam
                              adipisci
                              nostrum vel
                              tempora aliquam quidem? Nulla sit nobis voluptatem consequatur obcaecati similique
                              explicabo
                              velit
                              debitis,
                              quaerat quod? Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ullam, vero! Vero
                              possimus veritatis mollitia, id minima perspiciatis enim perferendis sunt corporis fuga
                              ratione repudiandae, nulla aspernatur quibusdam modi tempora inventore. Lorem ipsum dolor
                              sit amet consectetur, adipisicing elit. Fuga laborum cupiditate asperiores itaque
                              veritatis voluptatem provident quae et amet, nam nisi magni. Explicabo distinctio odit
                              officiis modi quis! Unde, eaque? Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                              Voluptatem enim nihil facere odit, quidem quia voluptate iure corrupti asperiores
                              obcaecati soluta vitae sunt aliquid? Dicta earum rerum ad quas repellendus. Lorem ipsum
                              dolor sit amet consectetur adipisicing elit. Quas facere est, labore similique officia
                              molestiae necessitatibus voluptatum, nulla harum ipsum fugit qui maiores autem
                              voluptatibus omnis atque perferendis ut ratione?</p>

                        </details>
                     </td>
                  </tr>
               </table>
            </td> -->
<?php
include_once('connection.php');
try {
   $db = new mysqli($server_name, $username, $password, $database_name);

   if (isset($_POST['submit'])) {
      $sql = 'INSERT INTO kareem_elassad_syscbook.users_posts (student_id, new_post, post_date) VALUES (' . $_SESSION["student_id"] . ', "' . $_POST["new_post"] . '", ' . "CAST(CURRENT_TIMESTAMP AS DATE)" . ');';
      $db->query($sql);
   } else {
      die("");
   }
   $db->close();

   // $student_id = $_SESSION['student_id'];
   // if($_SERVER['REQUEST_METHOD'] == 'POST') {
   //    $new_post = $_POST['new_post'];
   //    $post_date = date("Y-m-d");


   //    $inst = "INSERT INTO kareem_elassad_syscbook.users_posts (post_id, student_id, new_post, post_date) VALUES ( NULL, `$student_id`, '$new_post', '$post_date')";
   //    $inst = $db->query($inst);
   // }
} catch (Exception $e) {
   echo "Error: " . $e->getMessage();
}

header("Location: index.php");
exit();
?>