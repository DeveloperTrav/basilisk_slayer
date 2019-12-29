package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.NPC;

public class Attack extends TaskNode {

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean accept() {
        log("Attack: " + canAttack());
        return canAttack() || canReAttack();
    }

    @Override
    public int execute() {
        if (!canReAttack()) {
            NPC target = getNpcs().closest(npc -> npc != null && !npc.isInCombat() && npc.getName().equals("Basilisk") && Areas.basilisk.contains(npc));

        if (getMap().canReach(target.getTile())) {
            if (target != null) {
                API.status = "Attacking basilisk...";
                getCamera().rotateToEntity(target);
                target.interact("Attack");
                sleepUntil(() -> getLocalPlayer().isInCombat(), API.sleepUntil());
            }
        }
    } else {
        API.status = "Reattacking basilisk...";
        getLocalPlayer().getCharacterInteractingWithMe().interact("Attack");
        sleepUntil(() -> getLocalPlayer().isInCombat(), API.sleepUntil());
    }

        return API.sleep();
    }

    private boolean canAttack() {
        return !getLocalPlayer().isInCombat() && API.inBasiliskArea() && getInventory().contains("Lobster") && getLocalPlayer().getCharacterInteractingWithMe() == null;
    }

    private boolean canReAttack() {
        return !getLocalPlayer().isInCombat() && API.inDungeonArea() && getLocalPlayer().getCharacterInteractingWithMe() != null && Areas.basilisk.contains(getLocalPlayer().getCharacterInteractingWithMe())
                && getLocalPlayer().getCharacterInteractingWithMe().getName().equals("Basilisk");
    }
}
