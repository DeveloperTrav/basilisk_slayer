package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.tabs.Tab;
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
            if (getInventory().isFull() && !groundItem.getName().equals("Coins") && !groundItem.getName().equals("Nature rune")) {
                if (getTabs().isOpen(Tab.INVENTORY)) {
                    getInventory().get("Lobster").interact("Eat");
                    sleepUntil(() -> !getInventory().isFull(), API.sleepUntil());
                } else {
                    getTabs().openWithMouse(Tab.INVENTORY);
                    sleepUntil(() -> getTabs().isOpen(Tab.INVENTORY), API.sleepUntil());
                }
            }

            API.status = "Picking up item...";
            groundItem.interact("take");
            sleepUntil(() -> false, API.sleepUntil());
        }

        return API.sleep();
    }

    private boolean canPickup() {
        GroundItem groundItem = getGroundItems().closest(item -> Objects.nonNull(item) && Arrays.asList(API.getPickupItems()).contains(item.getName()) && Areas.basilisk.contains(item));
        return Objects.nonNull(groundItem) && Areas.basilisk.contains(groundItem);
    }
}
