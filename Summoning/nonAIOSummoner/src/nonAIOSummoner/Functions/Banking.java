package nonAIOSummoner.Functions;

import nonAIOSummoner.Data.Data;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

public class Banking {

    public static boolean atHome(){
        return Data.HOME_AREA.contains(Players.getMyPlayer().getLocation());
    }

    public static boolean canBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_ID);
        return bank != null;
    }

    public static void doBank(){
        Data.status = "Banking";
        GameObject bank = GameObjects.getNearest(Data.BANK_ID);
        bank.interact("use-quickly");
        if (Time.sleep(()-> Bank.isOpen(), 12000)){
            Bank.depositAllExcept(Data.DONT_BANK_ITEMS);
        }
    }
}
