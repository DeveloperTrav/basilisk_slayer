package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.TaskNode;

public class Eat extends TaskNode {

    @Override
    public boolean accept() {
        log("Eat: " + canEat());
        return canEat();
    }

    @Override
    public int execute() {
         if (getInventory().contains("Lobster")) {
             API.status = "Eating...";
             getInventory().get("Lobster").interact("Eat");
             sleep(2000, 3000);
         }

        return API.sleep();
    }

    private boolean canEat() {
        return getInventory().contains("Lobster") && getSkills().getBoostedLevels(Skill.HITPOINTS) < Calculations.random(40, 60);
    }
}
