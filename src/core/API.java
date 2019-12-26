package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;

public class API {
    public static String status = "Script starting...";
    private static MethodContext context;

    public API(MethodContext context) { API.context = context; }

    public static int sleepUntil() { return (int)Calculations.nextGaussianRandom(5000, 2500); }
    public static int sleep() { return (int)Calculations.nextGaussianRandom(400, 150); }

    public static boolean inBankArea() { return Areas.bank.contains(context.getLocalPlayer()); }
    public static boolean inBasiliskArea() { return Areas.basilisk.contains(context.getLocalPlayer()); }
    public static boolean inDungeonArea() { return Areas.dungeon.contains(context.getLocalPlayer()); }
}
