package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Inspector {
    int inspectorNumber;
    BigDecimal currentTime;
    Map<BigDecimal, String> eventList;
    WaitTimeGenerator timeGenerator;
    Random random;
    ArrayList<Buffer> bufferList;
    int C1Inspected, C2Inspected, C3Inspected;
    ArrayList<ArrayList> eventTimes;

    /**
     * Constructor of Inspector 1
     *
     * @param inspectorNumber
     * @param timeGenerator
     * @param workstation1Buffer
     * @param workstation2Buffer
     * @param workstation3Buffer
     */
    public Inspector(int inspectorNumber, WaitTimeGenerator timeGenerator, Buffer workstation1Buffer, Buffer workstation2Buffer, Buffer workstation3Buffer){
        this.inspectorNumber = inspectorNumber;
        this.timeGenerator = timeGenerator;

        bufferList = new ArrayList<Buffer>();
        bufferList.add(workstation1Buffer);
        bufferList.add(workstation2Buffer);
        bufferList.add(workstation3Buffer);

        currentTime = new BigDecimal("0");
        eventList = new Hashtable<>();
        random = new Random();

        C1Inspected = 0;
        C2Inspected = 0;
        C3Inspected = 0;

        eventTimes = new ArrayList<>();
    }

    /**
     * Constructor of Inspector 2
     *
     * @param inspectorNumber
     * @param timeGenerator
     * @param workstation2Buffer
     * @param workstation3Buffer
     */
    public Inspector(int inspectorNumber, WaitTimeGenerator timeGenerator, Buffer workstation2Buffer, Buffer workstation3Buffer){
        this.inspectorNumber = inspectorNumber;
        this.timeGenerator = timeGenerator;

        bufferList = new ArrayList<Buffer>();
        bufferList.add(workstation2Buffer);
        bufferList.add(workstation3Buffer);

        currentTime = new BigDecimal("0");
        eventList = new Hashtable<>();
        random = new Random();

        C1Inspected = 0;
        C2Inspected = 0;
        C3Inspected = 0;

        eventTimes = new ArrayList<>();
    }

    /**
     * Increment state of the inspector
     *
     * @param time the current time of the model
     */
    public void updateTime(BigDecimal time){
        //Update current time
        currentTime = time;

        //Check if any events in event list are done (component ready to be placed in buffer)
        for(BigDecimal key: eventList.keySet()){
            if(currentTime.compareTo(key) >= 0){
                String event = eventList.get(key);
                //Try to put component in buffer
                processEvent(key, event);
            }
        }

        //If inspector is not busy or blocked
        if(eventList.isEmpty()){
            //Get new component to inspect
            getNewEvent();
        }
    }

    /**
     * When the inspector is not busy inspecting or being blocked, get new component from the inventory
     */
    public void getNewEvent(){
        //Add arrival time of an event to eventTimes list
        ArrayList<BigDecimal> event = new ArrayList<>();
        event.add(currentTime);
        eventTimes.add(event);

        //If inspector #1, get C1
        if(inspectorNumber == 1){
            eventList.put(currentTime.add(timeGenerator.getInspector1Time()), "FinishInspectionC1");
        }
        //If inspector #2, 50/50 chance of either getting C2 or C3
        else if(inspectorNumber == 2){
            if(random.nextBoolean()){
                eventList.put((currentTime.add(timeGenerator.getInspector2TimeC2())), "FinishInspectionC2");
            }else{
                eventList.put((currentTime.add(timeGenerator.getInspector2TimeC3())), "FinishInspectionC3");
            }
        }
    }

    public void recordEndEvent(){
        ArrayList<BigDecimal> event = eventTimes.get(C1Inspected+C2Inspected+C3Inspected);;
        event.add(currentTime);
        eventTimes.set(C1Inspected+C2Inspected+C3Inspected, event);
    }

    /**
     * If event from event list is done, process the event (try to put inspected component in a buffer)
     * @param key
     * @param event
     */
    public void processEvent(BigDecimal key, String event){
        switch (event) {
            case "FinishInspectionC1" -> {
                //Get buffer with the smallest number of components in waiting
                Buffer sendTo = bufferList.get(0);
                int size = sendTo.getSize();
                for (Buffer buffer : bufferList) {
                    if (size > buffer.getSize()) {
                        size = buffer.getSize();
                        sendTo = buffer;
                    }
                }
                //Try to send to buffer with smallest number of components in waiting
                if (!(sendTo.isFull())) { //if not full
                    sendTo.addComponent("C1");
                    eventList.remove(key);
                    System.out.println("Inspector1    sent C1 to buffer " + sendTo.getName() + "    @ " + currentTime);
                    recordEndEvent();
                    C1Inspected++;
                }
            }
            case "FinishInspectionC2" -> {
                if (!(bufferList.get(0).isFull())) { //if not full
                    bufferList.get(0).addComponent("C2"); //Add C2 to Workstation 2 C2 Buffer
                    eventList.remove(key);
                    System.out.println("Inspector2    sent C2 to buffer " + bufferList.get(0).getName() + "    @ " + currentTime);
                    recordEndEvent();
                    C2Inspected++;
                }
            }
            case "FinishInspectionC3" -> {
                if (!(bufferList.get(1).isFull())) { //if not full
                    bufferList.get(1).addComponent("C3"); //Add C3 to Workstation 3 C3 Buffer
                    eventList.remove(key);
                    System.out.println("Inspector2    sent C3 to buffer " + bufferList.get(1).getName() + "    @ " + currentTime);
                    recordEndEvent();
                    C3Inspected++;
                }
            }
        }
    }

    public void printProductsInspected(){
        System.out.println("Inspector " +inspectorNumber+ " inspected " + C1Inspected + " C1, " + C2Inspected + " C2, and " + C3Inspected +" C3.");
    }

}
