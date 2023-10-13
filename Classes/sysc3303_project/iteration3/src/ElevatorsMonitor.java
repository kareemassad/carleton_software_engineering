import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class ElevatorsMonitor {

    private int numberOfElevators;

    private  ArrayList<Elevator> elevators;

    public ElevatorsMonitor(ArrayList<Elevator> elevators) {
        this.elevators = elevators;
        numberOfElevators = elevators.size();
        System.out.println("Elevator monitor is active, number of elevators: " + elevators.size() );
    }

    public Elevator getOptimalElevator(Task task) {

        Elevator result = elevators.get(0);

        int min = abs(result.getCurrentFloor()-task.getDestination());

        for(int  i=1; i<elevators.size();i++){
            Elevator currentElevator = elevators.get(i);

            String currentElevatorDirection = currentElevator.getCurrentDirection();

            System.out.println("Elevator #: " + i + " " + currentElevatorDirection);

            int currentMin = abs(currentElevator.getCurrentFloor()-task.getDestination());

            if (currentElevatorDirection.equals(task.getDirection()) ||
                    currentElevatorDirection.equals("idle") || currentElevator.isIdle()){
                if (min > currentMin) {
                    result = currentElevator;
                    min = currentMin;
                }
            }
        }

        return result;
    }

}
