package java9_to_17.new_switch;

import org.junit.jupiter.api.Test;

public class TraditionalSwitch {

    @Test
    public void SwitchExample() {

        String player = "Messi";

        runSwitch(player);

    }

    private void runSwitch(String player) {

        switch (player) {

            case "Muller":
            case "Neuer":
                System.out.println("Bayern");
                break;
            case "Messi":
                System.out.println("Miami");
                break;
            case "Ronaldo":
                System.out.println("Al Nassr");
                break;
            default:
                System.out.println("i dont know this player.");
        }
    }
}
