package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;

import java.util.Arrays;
import java.util.List;

public class API {
    public static String status = "Script starting...";
    private static MethodContext context;

    public API(MethodContext context) { API.context = context; }

    public static int sleepUntil() { return (int)Calculations.nextGaussianRandom(4000, 2000); }
    public static int sleep() { return (int)Calculations.nextGaussianRandom(400, 150); }

    public static boolean inBankArea() { return Areas.bank.contains(context.getLocalPlayer()); }
    public static boolean inBasiliskArea() { return Areas.basilisk.contains(context.getLocalPlayer()); }
    public static boolean inDungeonArea() { return Areas.dungeon.contains(context.getLocalPlayer()); }
    public static boolean inDungeonEntranceArea() { return Areas.dungeonEntrance.contains(context.getLocalPlayer()); }

    public static List<String> getPickupItems() {
        return Arrays.asList("Adamant full helm", "Mithril kiteshield", "Rune dagger", "Mystic hat (light)", "Nature rune", "Grimy ranarr weed", "Grimy avantoe",
                "Grimy kwuarm", "Grimy cadantine", "Grimy lantadyme", "coins", "Adamantite ore", "Uncut ruby", "Uncut diamond", "Loop half of key",
                "Tooth half of key", "Rune javelin", "Rune spear", "Shield left half", "Dragon spear");
    }
}
