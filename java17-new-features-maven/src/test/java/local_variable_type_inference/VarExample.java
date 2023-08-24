package local_variable_type_inference;

import org.junit.jupiter.api.Test;

public class VarExample {


    // var name = "Mehdi";

    @Test
    public void varExample() {
        // local variable type inference - Java 10

        var i = 10;
        var name = "Lewandowski";

        var anotherName = "Neuer";

        // Backward compatibility
        var var = "Messi";
    }
}
