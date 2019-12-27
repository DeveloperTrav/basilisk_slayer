package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;

public class Bank extends TaskNode {

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public boolean accept() {
        log("Bank: " + canBank());
        return canBank();
    }

    @Override
    public int execute() {
        if (API.inBankArea()) {
            if (!getBank().isOpen()) {
                API.status = "Opening bank...";
                getBank().openClosest();
                sleepUntil(() -> getBank().isOpen(), API.sleepUntil());
            }

            if (getBank().isOpen()) {
                if (!getInventory().isEmpty() && !getInventory().contains("Camelot teleport")) {
                    API.status = "Depositing items...";
                    getBank().depositAllItems();
                    sleepUntil(() -> getInventory().isEmpty(), API.sleepUntil());
                }

                if (!getEquipment().contains("Mirror shield")) {
                    API.status = "Getting mirror shield...";
                    getBank().withdraw("Mirror shield", 1);
                    sleepUntil(() -> getInventory().contains("Mirror shield"), API.sleepUntil());
                }

                if (!getInventory().contains("Camelot teleport")) {
                    API.status = "Getting teleport...";
                    getBank().withdraw("Camelot teleport", 1);
                    sleepUntil(() -> getInventory().contains("Camelot teleport"), API.sleepUntil());
                }

                if (!getInventory().contains("Lobster")) {
                    API.status = "Getting food...";
                    getBank().withdraw("Lobster", 20);
                    sleepUntil(() -> getInventory().contains("Lobster"), API.sleepUntil());
                }
            }
        }

        if (API.inDungeonArea()) {
            if (getTabs().isOpen(Tab.INVENTORY)) {
                if (getInventory().contains("Camelot teleport")) {
                    API.status = "Using teleport...";
                    getInventory().get("Camelot teleport").interact("Break");
                    sleepUntil(() -> !API.inBasiliskArea(), API.sleepUntil());
                }
            } else {
                getTabs().openWithMouse(Tab.INVENTORY);
            }
        }

        if (!API.inBankArea() && !API.inDungeonArea()) {
            API.status = "Walking to bank...";
            if (getWalking().shouldWalk(Calculations.random(4, 7)) && !getLocalPlayer().isAnimating()) {
                getWalking().walk(Areas.bank.getCenter().getRandomizedTile(3));
                sleep(2000, 2500);
            }
        }

        return API.sleep();
    }

    private boolean canBank() {
        return !getInventory().contains("Lobster") || !getInventory().contains("Camelot teleport");
    }
}
