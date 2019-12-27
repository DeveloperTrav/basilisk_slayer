package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.Objects;

public class Traverse extends TaskNode {

    @Override
    public boolean accept() {
        log("Traverse: " + canTraverse());
        return canTraverse();
    }

    @Override
    public int execute() {
        if (getBank().isOpen()) {
            getBank().close();
            sleepUntil(() -> !getBank().isOpen(), API.sleepUntil());
        }

        if (API.inDungeonArea()) {
            if (getWalking().shouldWalk(Calculations.random(4, 7))) {
                API.status = "Walking to basilisk...";
                log(Areas.basilisk.getCenter().getRandomizedTile(4).toString());
                getWalking().walk(Areas.basilisk.getCenter().getRandomizedTile(4));
            }
        } else if (API.inDungeonEntranceArea()) {
            GameObject cave = getGameObjects().closest(obj -> (Objects.nonNull(obj) && obj.getName().contains("Cave")));

            if (Objects.nonNull(cave)) {
                API.status = "Entering cave...";
                if (getMap().canReach(cave.getTile())) {
                    getCamera().rotateToEntity(cave);
                    cave.interact("Enter");
                    sleepUntil(API::inDungeonArea, API.sleepUntil());
                }
            }
        } else {
            if (getWalking().shouldWalk(Calculations.random(4, 7))) {
                API.status = "Walking to cave...";
                getWalking().walk(Areas.dungeonEntrance.getCenter().getRandomizedTile(3));
            }
        }

        return API.sleep();
    }

    private boolean canTraverse() {
        return !API.inBasiliskArea() && getInventory().contains("Camelot teleport") && getInventory().contains("Superantipoison(4)") && getInventory().contains("Lobster");
    }
}
