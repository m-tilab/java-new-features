package java18_to_21.scoped_values;

import java.math.BigInteger;

public class Multiplier {

    // Creates a new ScopedValue
    ScopedValue<BigInteger> MULTIPLIER = ScopedValue.newInstance();

    public BigInteger multiply(BigInteger number) throws Exception {
        // invokes the squared method and saves the result in variable multiplied
        return ScopedValue.callWhere(MULTIPLIER, number, () -> squared());
    }

    public BigInteger squared() {
        BigInteger number = MULTIPLIER.get();
        return number.multiply(number);
    }

    public static void main(String[] args) throws Exception {

        var multiplier = new Multiplier();

        System.out.println(multiplier.multiply(BigInteger.valueOf(10)));

        System.out.println(multiplier.multiply(BigInteger.valueOf(11)));
    }

}