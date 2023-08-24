package records;

import org.junit.jupiter.api.Test;

public class Records {

    @Test
    public void recordsExample() {

        Person person1 = new Person("Mehdi Tilab", 34);
        PersonRecord person2 = new PersonRecord("Mehdi Tilab", 34);

        System.out.println(person1.getAge());
        System.out.println(person2.age());
    }
}
