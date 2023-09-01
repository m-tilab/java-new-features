package java18_to_21.scoped_values;

public class ScopedValuesExample {

    private final static ScopedValue<String> USER_ID = ScopedValue.newInstance();

    public void processWithUser(String sessionUserId) {
        // sessionUserId is bound to the ScopedValue USER_ID for the execution of the
        // runWhere method, the runWhere method invokes the processRequest method.
        ScopedValue.runWhere(USER_ID, sessionUserId, () -> processRequest());
    }

    private void processRequest() {
        System.out.println(USER_ID.get());
    }

    public static void main(String[] args) {
        ScopedValuesExample example = new ScopedValuesExample();
        example.processWithUser("123");
    }


}
