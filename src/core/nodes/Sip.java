package core.nodes;

import core.API;
import org.dreambot.api.script.TaskNode;

public class Sip extends TaskNode {

    @Override
    public boolean accept() {
//        log("Sip: " + canSip());
        return canSip();
    }

    @Override
    public int execute() {
        if (getInventory().contains("Superantipoison")) {
            API.status = "Sipping antipoison...";
            getInventory().get("Superantipoison").interact("Drink");
            sleep(2000, 3500);
        }

        return API.sleep();
    }

    private boolean canSip() {
        return getCombat().isPoisoned();
    }
}
