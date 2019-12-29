package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Arrays;
import java.util.Objects;

public class Pickup extends TaskNode {

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public boolean accept() {
        log("Pickup: " + canPickup());
        return canPickup();
    }

    @Override
    public int execute() {
        GroundItem groundItem = getGroundItems().closest(item -> Objects.nonNull(item) && Arrays.asList(API.getPickupItems()).contains(item.getName()) && Areas.basilisk.contains(item));

        if (Objects.nonNull(groundItem)) {
            API.status = "Getting that bread...";
            groundItem.interact("take");
            sleepUntil(() -> false, API.sleepUntil());
        }

        return API.sleep();
    }

    private boolean canPickup() {
        GroundItem groundItem = getGroundItems().closest(item -> Objects.nonNull(item) && Arrays.asList(API.getPickupItems()).contains(item.getName()) && Areas.basilisk.contains(item));
        return !getInventory().isFull() && Objects.nonNull(groundItem);
    }
}
