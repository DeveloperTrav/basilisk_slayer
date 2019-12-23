package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;

public class API {
    private static MethodContext context;

    public API(MethodContext context) { API.context = context; }

    public static int sleepUntil() { return (int)Calculations.nextGaussianRandom(2500, 7000); }

    public static boolean inBankArea() { return Areas.bank.contains(context.getLocalPlayer()); }
    public static boolean inBasiliskArea() { return Areas.basilisk.contains(context.getLocalPlayer()); }
}
