package core.nodes;

import core.API;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Objects;

public class Pickup extends TaskNode {

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean accept() {
        log("Pickup: " + canPickup());
        return canPickup();
    }

    @Override
    public int execute() {
        GroundItem groundItem = getGroundItems().closest(item -> (Objects.nonNull(item) && API.getPickupItems().contains(item.getName())));

        if (Objects.nonNull(groundItem)) {
            API.status = "Getting that bread...";
            groundItem.interact("take");
            sleepUntil(() -> getInventory().contains(groundItem.getName()), API.sleepUntil());
        }

        return API.sleep();
    }

    private boolean canPickup() {
        return getInventory().isEmpty() && Objects.nonNull(getGroundItems().closest(item -> (Objects.nonNull(item) && API.getPickupItems().contains(item.getName()))))
                && !getLocalPlayer().isInCombat();
    }
}
