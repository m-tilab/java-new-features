package java9_to_17.local_variable_type_inference;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VarExample {


    // var name = "Mehdi";

    int i = 10;

    @Test
    public void varExample() {

        // Java 5
        List<String> names = new ArrayList<String>();

        // Java 7
        List<String> names2 = new ArrayList<>();

        // local variable type inference - Java 10
        var names3 = new ArrayList<String>();

        var i = 10;
        var name = "Lewandowski"; // String pool

        var anotherName = new String("Neuer");

        // Backward compatibility
        var var = "Messi";
    }
}
