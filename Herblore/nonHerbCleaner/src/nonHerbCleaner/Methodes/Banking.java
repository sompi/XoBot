package nonHerbCleaner.Methodes;

import nonHerbCleaner.Data.Data;
import nonHerbCleaner.Utils.Methodes;
import nonHerbCleaner.Utils.SleepCondition;
import nonHerbCleaner.Data.Data;
import nonHerbCleaner.Utils.Methodes;
import nonHerbCleaner.Utils.SleepCondition;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

public class Banking {

    public static boolean canBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_ID);
        return bank != null && bank.isReachable();
    }

    public static void doBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_ID);
        if (bank != null && !Bank.isOpen()) {
            bank.interact("use-quickly");
            Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 8000);
        }

        if (Bank.isOpen()) {
            Bank.depositAll();
            Time.sleep(100);
            Packets.sendAction(431, Data.TO_CLEAN, Bank.getItem(Data.TO_CLEAN).getSlot(), 0);
            Time.sleep(100);
            Bank.withdraw(Data.TO_CLEAN, 28);
            Time.sleep(100);
            Packets.sendAction(200, 0, 5384, 0); //Close bank
        }
    }
}
