<?php
    function door_lock($student_grades){
        $lock[] = 'X';

        foreach($student_grades as $grade){
            if($grade >= 90){
                $lock[] = 'A';
            } else if($grade >= 80){
                $lock[] = 'B';
            } else if($grade >= 70){
                $lock[] = 'C';
            } else if($grade >= 60){
                $lock[] = 'D';
            } else if($grade >= 40){
                $lock[] = 'E';
            } else {
                $lock[] = 'F';
            }
        }
        return $lock;
    }

    $lock_digits = door_lock([40,1000,63,80,100]);
    foreach($lock_digits as $digit){
        printf("%s",$digit);
    }