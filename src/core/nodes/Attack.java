package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.NPC;

public class Attack extends TaskNode {

    @Override
    public boolean accept() {
        log("Attack: " + canAttack());
        return canAttack();
    }

    @Override
    public int execute() {
        NPC target = getNpcs().closest(npc -> (npc != null && !npc.isInCombat() && npc.getName().equals("Basilisk") && Areas.basilisk.contains(npc)));
        System.out.println(getSkills().getBoostedLevels(Skill.HITPOINTS));

        if (getMap().canReach(target.getTile())) {
            if (target != null) {
                API.status = "Attacking basilisk...";
                getCamera().rotateToEntity(target);
                target.interact("Attack");
                sleepUntil(() -> getLocalPlayer().isInCombat(), API.sleepUntil());
            }
        }

        return API.sleep();
    }

    private boolean canAttack() {
        return API.inBasiliskArea() && !getLocalPlayer().isInCombat() && getInventory().contains("Lobster");
    }
}
