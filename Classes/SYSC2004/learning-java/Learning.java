
public class Learning {

    private static int x;
    public static int y;
    // constructor
    public Learning(){
        x = 0;
        y = 0;
    }
    // accessor
    public int getX() {
        return x;
    }
    // mutator
    public static void moreYOne(){
        y = y + 1;
    }
    // mutator
    public static void moreXOne(){
        x = x + 1;
    }


    public static void main(String[] args) {
        //create learning object called myObj
        Learning myObj = new Learning();
        System.out.println("y = " + y);
        moreYOne();
        System.out.println("y = " + y);

        System.out.println("x = " + myObj.x);
        moreXOne();
        System.out.println("x = " + myObj.x);
        
        // int + int + string in java
        // System.out.println(5 + 5 + " wtf int + int + string");
    }

}