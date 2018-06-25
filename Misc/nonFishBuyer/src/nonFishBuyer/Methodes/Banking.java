package nonFishBuyer.Methodes;

import nonFishBuyer.Data.Data;
import nonFishBuyer.Utils.Methodes;
import nonFishBuyer.Utils.SleepCondition;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.NPCs;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.NPC;

public class Banking {

    public static boolean canBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_BOOTH);
        return bank != null && bank.isReachable();
    }

    public static void doBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_BOOTH);
        if (bank != null && !Bank.isOpen()) {
            bank.interact("use-quickly");
            Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 15000);
        }

        if (Bank.isOpen()) {
            Bank.deposit(Data.ITEM_ID_TO_BUY, 28);
        }
    }
}
