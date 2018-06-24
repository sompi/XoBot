package nonAIOSummoner.Functions;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import nonAIOSummoner.Data.Data;
import nonAIOSummoner.Utils.Methodes;
import nonAIOSummoner.Utils.SleepCondition;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.Players;
import xobot.script.methods.Widgets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.Widget;
import xobot.script.wrappers.interactive.GameObject;

public class Summoning {
    // We can only summon when the Obelisk is not null and we are in the summoning area.
    public static boolean canSummon(){
        GameObject obelisk = GameObjects.getNearest(Data.OBELISK_ID);
        System.out.println("canSummon - We are in the Summoning Area and Obelisk is not null");
        return obelisk != null;
    }

    public static boolean inSummoning(){
        return Data.SUMMONING_AREA.contains(Players.getMyPlayer().getLocation());
    }

    public static void doSummon(){
        Data.status = "Summoning";
        GameObject OBLISK = GameObjects.getNearest(Data.OBELISK_ID);
        Widget SUMMONG_INTERFACE = Widgets.get(Data.SUMMONING_INTERFACE_ID);
        //Gotta check if Inventory contains the required items.

        OBLISK.interact("infuse-pouch");
        if (Time.sleep(()-> Widgets.getOpenInterface() == Data.SUMMONING_INTERFACE_ID, 7000)){
            System.out.println("Summoning interface is open");
            Packets.sendAction(646, 470482944, 0, Data.REQUIRED_PACKET, 28716, 0);
            System.out.println("Packet send for summoning");
            Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Inventory.Contains(Data.REQUIRED_INGREDIENT);
                }
            }, 60000);
        }
    }
}
