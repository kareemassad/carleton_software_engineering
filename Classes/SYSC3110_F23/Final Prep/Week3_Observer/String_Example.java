public class String_Example {
    public static void main(String[] args) {
        //"Hello World"
        String string = new String("Hello");
        String string2 = string.concat("_World");
        System.out.println(string2);
        String string3 = string2.replace("_", " ");
        System.out.println(string3);

        StringBuffer buffer = new StringBuffer("Hello");
        buffer.append("_World");
        System.out.println(buffer);
        buffer.setCharAt(5, ' ');
        System.out.println(buffer);
    }
}
