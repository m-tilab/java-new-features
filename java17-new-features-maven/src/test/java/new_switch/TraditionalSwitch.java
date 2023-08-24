package new_switch;

import org.junit.jupiter.api.Test;

public class TraditionalSwitch {

    @Test
    public void SwitchExample() {

        String player = "Messi";

        runSwitch(player);

    }

    private void runSwitch(String player) {

        switch (player) {

            case "Lewandowski":
            case "Muller":
            case "Neuer":
                System.out.println("Bayern");
                break;
            case "Messi":
                System.out.println("PSG");
                break;
            case "Ronaldo":
                System.out.println("Manchester United");
                break;
            default:
                System.out.println("i dont know this player.");
        }
    }
}
