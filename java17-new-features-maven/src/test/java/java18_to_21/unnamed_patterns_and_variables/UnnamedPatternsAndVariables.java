package java18_to_21.unnamed_patterns_and_variables;

public class UnnamedPatternsAndVariables {

    public static void main(String[] args) {

        String s = "Hello, World!";

        try {
            int i = Integer.parseInt(s);
            //use i
        } catch (NumberFormatException _) {
            System.out.println("Invalid number: " + s);
        }

        /*Object obj = s;

        switch (obj) {

            case Byte, Short, Integer, Long   _ -> System.out.println("Input is a number");
            case Float, Double  _ -> System.out.println("Input is a floating-point number");

            case String _ -> System.out.println("Input is a string");

            default -> System.out.println("Object type not expected");
        }*/
    }
}
