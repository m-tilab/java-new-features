package java9_to_17.new_switch;

import org.junit.jupiter.api.Test;

public class EnhanceSwitchStatement {

    @Test
    public void SwitchExample() {
        //String player = "Lewandowski";
        String player = "Messi";

        runSwitch(player);
    }

    private void runSwitch(String player) {

        switch (player) {
            case "Muller", "Neuer" -> System.out.println("Bayern");
            case "Messi" -> System.out.println("Miami");
            case "Ronaldo" -> System.out.println("Al Nasr");
            default -> System.out.println("i dont know this player.");
        }
    }
}
