package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Main {

    Inspector inspector1, inspector2;
    Workstation workstation1, workstation2, workstation3;

    Buffer C1W1, C1W2, C2W2, C1W3, C3W3;

    WaitTimeGenerator waitTimeGenerator;
    BigDecimal currentTime, increment, max;

    public Main(){
        //Create waitTimeGenerator used by all Inspectors and Workstations
        waitTimeGenerator = new WaitTimeGenerator();

        //Create all five buffers, shared between assigned inspectors and workstations
        this.C1W1 = new Buffer("C1W1");
        this.C1W2 = new Buffer("C1W2");
        this.C2W2 = new Buffer("C2W2");
        this.C1W3 = new Buffer("C1W3");
        this.C3W3 = new Buffer("C3W3");

        //Create inspectors, passing the buffers they will pass components to
        this.inspector1 = new Inspector(1,waitTimeGenerator, C1W1, C1W2, C1W3);
        this.inspector2 = new Inspector(2, waitTimeGenerator, C2W2, C3W3);

        //Create workstations, passing the buffers they will retrieve components from
        this.workstation1 = new Workstation(1, waitTimeGenerator, C1W1);
        this.workstation2 = new Workstation(2, waitTimeGenerator, C1W2, C2W2);
        this.workstation3 = new Workstation(3, waitTimeGenerator, C1W3, C3W3);

        currentTime = new BigDecimal("0");//Start at time 0
        increment = new BigDecimal("0.1");//Update time in increments of 0.1 seconds, can be changed
        max = new BigDecimal("100");//Max number of cycles, when the simulation should end
    }

    public void runModel(){
        //While currentTime <= max
        while(currentTime.compareTo(max)<=0){
            //Update inspectors (attempt to place component in buffers)
            inspector1.updateTime(currentTime);
            inspector2.updateTime(currentTime);

            //Update workstations (attempt to get component from buffers)
            workstation1.updateTime(currentTime);
            workstation2.updateTime(currentTime);
            workstation3.updateTime(currentTime);

            //Update inspectors
            //Need to do this twice in case buffer now has room since workstation has taken component
            inspector1.updateTime(currentTime);
            inspector2.updateTime(currentTime);

            //Increment current time
            currentTime = currentTime.add(increment);
        }
    }

    public BigDecimal getAverageTurnaround(ArrayList<ArrayList> eventTimes){
        ArrayList<BigDecimal> difference = new ArrayList<BigDecimal>();

        for(ArrayList<BigDecimal> event : eventTimes){
            if(event.size() > 1){
                BigDecimal startTime = event.get(0);
                BigDecimal endTime = event.get(1);
                difference.add(endTime.subtract(startTime));
            }
        }

        BigDecimal total = new BigDecimal("0");

        for(BigDecimal duration : difference){
            total = total.add(duration);
        }

        BigDecimal average;
        try{
            average = total.divide(BigDecimal.valueOf(difference.size()));
        }catch (Exception e){
            average = total.divide(BigDecimal.valueOf(difference.size()), 20, RoundingMode.UP);
        }
        return average;
    }

    public void printFinalReadings(){
        System.out.println("\nInspector Info:");

        System.out.println(inspector1.eventTimes.toString());
        System.out.println("Average turnaround time for Inspector 1: " + getAverageTurnaround(inspector1.eventTimes));
        inspector1.printProductsInspected();

        System.out.println(inspector2.eventTimes.toString());
        System.out.println("Average turnaround time for Inspector 2: " + getAverageTurnaround(inspector2.eventTimes));
        inspector2.printProductsInspected();

        System.out.println("\nWorkstation Info:");

        System.out.println(workstation1.eventTimes.toString());
        System.out.println("Average turnaround time for Workstation 1: " + getAverageTurnaround(workstation1.eventTimes));
        workstation1.printProductsProduced();


        System.out.println(workstation2.eventTimes.toString());
        System.out.println("Average turnaround time for Workstation 2: " + getAverageTurnaround(workstation2.eventTimes));
        workstation2.printProductsProduced();

        System.out.println(workstation3.eventTimes.toString());
        System.out.println("Average turnaround time for Workstation 3: " + getAverageTurnaround(workstation3.eventTimes));
        workstation3.printProductsProduced();

        System.out.println("\nBuffer Info:");
        C1W1.printBufferInfo();
        C1W2.printBufferInfo();
        C2W2.printBufferInfo();
        C1W3.printBufferInfo();
        C3W3.printBufferInfo();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.runModel();
        System.out.println("Model finish running");
        main.printFinalReadings();
    }
}