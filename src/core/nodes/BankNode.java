package core.nodes;

import core.API;
import org.dreambot.api.script.TaskNode;

public class BankNode extends TaskNode {

    @Override
    public boolean accept() {
        return canBank();
    }

    @Override
    public int execute() {
        if (API.inBankArea()) {
            if (!getBank().isOpen()) {

            }

        } else if (API.inBasiliskArea() && getInventory().contains("Camelot teleport")) {
            getInventory().get("Camelot teleport").interact("Break");
            sleepUntil(() -> !API.inBasiliskArea(), API.sleepUntil());
        } else {

        }

        if (API.inBasiliskArea()) {
            getInventory().get("Camelot teleport").interact("Break");
            sleepUntil(() -> !API.inBasiliskArea(), API.sleepUntil());
        }

        if (API.inBankArea()) {
            if (!getBank().isOpen()) {
                getBank().openClosest();
                sleepUntil(() -> getInventory().isEmpty(), API.sleepUntil());
            } else {
                getBank().depositAllItems();
                getBank().withdraw("Camelot teleport", 1);
                sleepUntil(() -> getInventory().contains("Camelot teleport"), API.sleepUntil());
            }
        }

        return 0;
    }

    private boolean canBank() {
        return getInventory().isFull();
    }
}
