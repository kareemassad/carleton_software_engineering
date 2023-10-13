package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Workstation {
    int workstationNumber;
    BigDecimal currentTime;
    Map<BigDecimal, String> eventList;
    WaitTimeGenerator timeGenerator;
    Random random;
    ArrayList<Buffer> bufferList;

    int P1Produced, P2Produced, P3Produced;

    ArrayList<ArrayList> eventTimes;

    /**
     * Constructor for Workstation 1
     *
     * @param workstationNumber
     * @param timeGenerator
     * @param c1Buffer
     */
    public Workstation (int workstationNumber, WaitTimeGenerator timeGenerator, Buffer c1Buffer){
        this.workstationNumber = workstationNumber;
        this.timeGenerator = timeGenerator;

        bufferList = new ArrayList<Buffer>();
        bufferList.add(c1Buffer);

        currentTime = new BigDecimal("0");
        eventList = new Hashtable<>();
        random = new Random();

        P1Produced = 0;
        P2Produced = 0;
        P3Produced = 0;

        eventTimes = new ArrayList<>();
    }

    /**
     * Constructor for Workstation 2 or 3
     *
     * @param workstationNumber
     * @param timeGenerator
     * @param c1Buffer
     * @param otherBuffer
     */
    public Workstation (int workstationNumber, WaitTimeGenerator timeGenerator, Buffer c1Buffer, Buffer otherBuffer){
        this.workstationNumber = workstationNumber;
        this.timeGenerator = timeGenerator;

        bufferList = new ArrayList<Buffer>();
        bufferList.add(c1Buffer);
        bufferList.add(otherBuffer);

        currentTime = new BigDecimal("0");
        eventList = new Hashtable<>();
        random = new Random();

        P1Produced = 0;
        P2Produced = 0;
        P3Produced = 0;

        eventTimes = new ArrayList<>();
    }

    /**
     * Increment state of the workstation
     *
     * @param time the current time of the model
     */
    public void updateTime(BigDecimal time){
        currentTime = time;
        for(BigDecimal key: eventList.keySet()){
            if(currentTime.compareTo(key) >= 0){
                String event = eventList.get(key);
                processEvent(key, event);
            }
        }
        if(eventList.isEmpty()){
            getNewEvent();
        }
    }

    /**
     * When the inspector is not busy assembling, try to get components from buffers
     */
    public void getNewEvent(){
        //Workstation1 requires C1 from buffer C1W1
        if(workstationNumber == 1){
            if(!(bufferList.get(0).isEmpty())){ //If C1 is available
                //Add arrival time of an event to eventTimes list
                ArrayList<BigDecimal> event = new ArrayList<>();
                event.add(currentTime);
                eventTimes.add(event);

                bufferList.get(0).removeComponent();
                System.out.println("Workstation1  started  assembly of P1   @ " + currentTime);
                eventList.put((currentTime.add(timeGenerator.getWorkstation1Time())), "FinishAssemblyP1");
            }
        }
        //Workstation2 requires C1 and C2 from C1W2 and C2W2 respectively
        else if(workstationNumber == 2){
            if(!(bufferList.get(0).isEmpty()) && !(bufferList.get(1).isEmpty())){ //If C1 and C2 is available
                //Add arrival time of an event to eventTimes list
                ArrayList<BigDecimal> event = new ArrayList<>();
                event.add(currentTime);
                eventTimes.add(event);

                bufferList.get(0).removeComponent();
                bufferList.get(1).removeComponent();
                System.out.println("Workstation2  started  assembly of P2   @ " + currentTime);
                eventList.put((currentTime.add(timeGenerator.getWorkstation2Time())), "FinishAssemblyP2");
            }
        }
        //Workstation2 requires C1 and C3 from C1W3 and C3W# respectively
        else if(workstationNumber == 3){
            if(!(bufferList.get(0).isEmpty()) && !(bufferList.get(1).isEmpty())){ //If C1 and C3 is available
                //Add arrival time of an event to eventTimes list
                ArrayList<BigDecimal> event = new ArrayList<>();
                event.add(currentTime);
                eventTimes.add(event);

                bufferList.get(0).removeComponent();
                bufferList.get(1).removeComponent();
                System.out.println("Workstation3  started  assembly of P3   @ " + currentTime);
                eventList.put((currentTime.add(timeGenerator.getWorkstation3Time())), "FinishAssemblyP3");
            }
        }
    }

    /**
     * If event from event list is done, process the event (finished assembly of product)
     * @param key
     * @param event
     */
    public void processEvent(BigDecimal key, String event){
        switch (event) {
            case "FinishAssemblyP1" -> {
                eventList.remove(key);
                System.out.println("Workstation1  finished assembly of P1   @ " + currentTime);
                recordEndEvent();
                P1Produced++;
            }
            case "FinishAssemblyP2" -> {
                eventList.remove(key);
                System.out.println("Workstation2  finished assembly of P2   @ " + currentTime);
                recordEndEvent();
                P2Produced++;
            }
            case "FinishAssemblyP3" -> {
                eventList.remove(key);
                System.out.println("Workstation3  finished assembly of P3   @ " + currentTime);
                recordEndEvent();
                P3Produced++;
            }
        }
    }

    public void recordEndEvent(){
        ArrayList<BigDecimal> event = eventTimes.get(P1Produced+P2Produced+P3Produced);;
        event.add(currentTime);
        eventTimes.set(P1Produced+P2Produced+P3Produced, event);
    }

    public void printProductsProduced(){
        System.out.println("Workstation " +workstationNumber+ " produced " + P1Produced + " P1, " + P2Produced + " P2, and " + P3Produced +" P3.");
    }
}
