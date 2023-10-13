package org.example;

import java.math.BigDecimal;

public class WaitTimeGenerator {
    BigDecimal inspector1Time, inspector2TimeC2, inspector2TimeC3, workstation1Time, workstation2Time, workstation3Time;

    static float seed = 27;
    static int a = 9515;
    static int c = 0;
    static int m = 32749;

    public WaitTimeGenerator(){
        inspector1Time = new BigDecimal("1");
        inspector2TimeC2 = new BigDecimal("2");
        inspector2TimeC3 = new BigDecimal("2");
        workstation1Time = new BigDecimal("5");
        workstation2Time = new BigDecimal("5");
        workstation3Time = new BigDecimal("5");
    }

    public float generateValue(){
        float x = (a* seed + c) % m;
        seed = x;
        return x/m;
    }
    public BigDecimal getInspector1Time(){
        return inspector1Time;
    }

    public BigDecimal getInspector2TimeC2() {
        return inspector2TimeC2;
    }

    public BigDecimal getInspector2TimeC3() {
        return inspector2TimeC3;
    }

    public BigDecimal getWorkstation1Time() {
        return workstation1Time;
    }

    public BigDecimal getWorkstation2Time() {
        return workstation2Time;
    }

    public BigDecimal getWorkstation3Time() {
        return workstation3Time;
    }

    public static void main(String[] args) {
        int run = 0;
        WaitTimeGenerator waitTimeGenerator = new WaitTimeGenerator();
        while(run < 100){
            System.out.println(waitTimeGenerator.generateValue());
            run++;
        }
    }
}
