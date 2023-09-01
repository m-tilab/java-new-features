package java18_to_21.parttern_matching.record_type;

//@formatter:off
record PersonRecord(String name, int age) {}

//@formatter:on

public class RecordPatternMatching {

    public static void main(String[] args) {
        PersonRecord personRecord = new PersonRecord("John", 30);
        System.out.println(personRecord);

        Object personRecordAsObj = personRecord;

        // traditional way

        if (personRecordAsObj instanceof PersonRecord p) {

            String name = p.name();
            int age = p.age();
            System.out.println("name: " + name + ", age: " + age);
        }

        // record pattern matching with new way

        if (personRecordAsObj instanceof PersonRecord(String name, int age)) {

            System.out.println("name: " + name + ", age: " + age);
        }
    }
}
