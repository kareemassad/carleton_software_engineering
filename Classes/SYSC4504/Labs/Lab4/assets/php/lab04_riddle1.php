
<?php
    /* The CHSBL VM G PZ LSLCLU */
    $drawer_lock = 0;
    $z = 11;
    for ($x = 0; $x < $z; $x++) {
        $y = 1;
        while ($y <= $x) {
            $drawer_lock = $drawer_lock + ($x * $y);
            $y++;
        }
    }
    echo $drawer_lock;
?>