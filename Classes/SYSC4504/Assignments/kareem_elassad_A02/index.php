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
      </header>

      <table class="main-table">
         <tr>
            <td class="navbar">
               <ul>
                  <li><a class="active" href="#home"><b>Home</b></a></li>
                  <li><a href="profile.php"><b>Profile</b></a></li>
                  <li><a href="register.php"><b>Register</b></a></li>
                  <li><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ"><b>Log Out</b></a></li>
               </ul>
            </td>
            <td class="align-body">
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
            </td>
         </tr>
      </table>
   </div>
</body>
<?php 
   include('connection.php');
   $db = new mysqli($server_name, $username, $password, $database_name);
   if($_SERVER['REQUEST_METHOD'] == 'POST') {
      $new_post = $_POST['new_post'];
      $post_date = date("Y-m-d");
      

      $inst = "INSERT INTO kareem_elassad_syscbook.users_posts (post_id, student_id, new_post, post_date) VALUES ( NULL,NULL, '$new_post', '$post_date')";
      $inst = $db->query($inst);
   }

?>

</html>