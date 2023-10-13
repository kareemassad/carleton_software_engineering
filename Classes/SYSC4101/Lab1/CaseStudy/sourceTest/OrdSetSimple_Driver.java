
public class OrdSetSimple_Driver {

  public static void main(String argv[]) {

    TestCase1();
    TestCase2();
    TestCase3();
    TestCase4();
    TestCase5();
    TestCase6();
    TestCase7();
    TestCase8();
    TestCase9();
    

    // add other calls to test case methods here

  }

  public static void TestCase1() {
    OrdSetSimple s1, s2, s3;

    s1 = new OrdSetSimple(3); // Creating a set of maximum size 3.
    System.out.println("s1 = "+s1);

    s2 = new OrdSetSimple(2);
    s2.addElement(1);
    s2.addElement(4);
    System.out.println("s2 = "+s2);

    s3 = s1.difference(s2);
    System.out.println("s3 = s1 - s2 = "+s3);
  }

  // Add other methods defining tests here.
  public static void TestCase2(){
    // testing union(OrdSetSimple)
    OrdSetSimple s1, s2, s3;
    // two sets to test union
    s1 = new OrdSetSimple(3);
    s2 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);
    // add elements to s2
    s2.addElement(2);
    s2.addElement(3);
    s2.addElement(4);
    // test union
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);

    // union edge cases
    // if s2 2nd element is < S1 1st element
    s2 = new OrdSetSimple(3);
    s2.addElement(2);
    s2.addElement(3);
    s2.addElement(4);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);
    // if s1 1st element is == s2 2nd element
    s2 = new OrdSetSimple(3);
    s2.addElement(2);
    s2.addElement(5);
    s2.addElement(6);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);

    // if s1 2nd element is == s2 1st element
    s1 = new OrdSetSimple(3);
    s1.addElement(4);
    s1.addElement(5);
    s1.addElement(6);
    s2 = new OrdSetSimple(3);
    s2.addElement(5);
    s2.addElement(7);
    s2.addElement(8);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);

    // if s1 2nd element is > s2 1st element
    s1 = new OrdSetSimple(3);
    s1.addElement(5);
    s1.addElement(6);
    s1.addElement(7);
    s2 = new OrdSetSimple(3);
    s2.addElement(3);
    s2.addElement(4);
    s2.addElement(5);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);

    //test if lb1 !< size1 && lb2 !< size2
    s1 = new OrdSetSimple(3);
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);
    s2 = new OrdSetSimple(3);
    s2.addElement(4);
    s2.addElement(5);
    s2.addElement(6);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);

    // test if sets different sizes
    s1 = new OrdSetSimple(3);
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    s2 = new OrdSetSimple(4);
    s2.addElement(4);
    s2.addElement(5);
    s2.addElement(6);
    s2.addElement(7);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);

    //test comparing with empty set
    s1 = new OrdSetSimple(3);
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);
    s2 = new OrdSetSimple(3);
    s3 = s1.union(s2);
    System.out.println("s3 = s1 union s2 = "+s3);


  }

  public static void TestCase3(){
    //testing equals(OrdSetSimple)
    OrdSetSimple s1, s2, s3;
    // two sets to test equals
    s1 = new OrdSetSimple(3);
    s2 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);
    // add elements to s2
    s2.addElement(1);
    s2.addElement(2);
    s2.addElement(3);
    // test equals
    System.out.println("s1 equals s2 = "+s1.equals(s2));
    
    // test if sets different sizes
    s1 = new OrdSetSimple(3);
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    s2 = new OrdSetSimple(4);
    s2.addElement(1);
    s2.addElement(2);
    s2.addElement(3);
    s2.addElement(4);
    System.out.println("s1 equals s2 = "+s1.equals(s2));

  }
  public static void TestCase4(){
    // testing remove(int)
    //removes element from set
    OrdSetSimple s1;
    // set to test remove
    s1 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    // test remove
    s1.remove(2);
    System.out.println("s1 = "+s1);

    //test if where !>=0
    s1.remove(-1);
    System.out.println("s1 = "+s1);

    //test if where !< size
    s1.remove(3);
    System.out.println("s1 = "+s1);

    //test remove on non-existent element
    s1.remove(4);
    System.out.println("s1 = "+s1);

  }
  public static void TestCase5(){
    //testing getCapacity()
    OrdSetSimple s1;
    // set to test getCapacity
    s1 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    // test getCapacity
    System.out.println("s1 capacity = "+s1.getCapacity());
    
  }
  public static void TestCase6(){
    //testing addElement(int)
    OrdSetSimple s1;
    // set to test addElement
    s1 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    // test addElement
    s1.addElement(4);
    System.out.println("s1 = "+s1);

    //test addElement edge cases
    // if element is already in set
    s1.addElement(1);
    System.out.println("s1 = "+s1);

    //if element <0
    s1.addElement(-1);
    System.out.println("s1 = "+s1);

    //if element > capacity
    s1.addElement(5);
    System.out.println("s1 = "+s1);

  }

  public static void TestCase7(){
    // testing difference(OrdSetSimple)
    OrdSetSimple s1, s2, s3;
    // two sets to test difference
    s1 = new OrdSetSimple(3);
    s2 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    // add elements to s2
    s2.addElement(2);
    s2.addElement(3);
    s2.addElement(4);

    // test difference
    s3 = s1.difference(s2);
    System.out.println("s3 = s1 - s2 = "+s3);

  }
  public static void TestCase8(){
    // testing getElementAt(int)
    OrdSetSimple s1;
    // set to test getElementAt
    s1 = new OrdSetSimple(3);
    // add elements to s1
    s1.addElement(1);
    s1.addElement(2);
    s1.addElement(3);

    // test getElementAt
    System.out.println("s1 element at 1 = "+s1.getElementAt(1));

    //test if where !>=0
    System.out.println("s1 element at -1 = "+s1.getElementAt(-1));

    //test if where !< size
    System.out.println("s1 element at 3 = "+s1.getElementAt(3));

  }
  public static void TestCase9(){
    
  }

}





 


