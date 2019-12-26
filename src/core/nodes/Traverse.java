package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

public class Traverse extends TaskNode {

    @Override
    public boolean accept() {
//        log("Traverse: " + canTraverse());
        return canTraverse();
    }

    @Override
    public int execute() {
        if (getWalking().shouldWalk(Calculations.random(4, 7))) {
            API.status = "Walking to basilisk...";
            getWalking().walk(Areas.basilisk.getCenter().getRandomizedTile(4));
        }

        return API.sleep();
    }

    private boolean canTraverse() {
        return !API.inBasiliskArea() && getInventory().contains("Camelot teleport") && getInventory().contains("Superantipoison(4)") && getInventory().contains("Lobster");
    }
}
