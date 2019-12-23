import core.API;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;

@ScriptManifest(category = Category.COMBAT, name = "Basilisk Slayer", description = "Kills the things", author = "NotJohn", version = 1.0)
public class Main extends TaskScript {

    private final API api = new API(this);
     public void onStart() {

     }
}
