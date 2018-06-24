package nonDZSkillShop.Methodes;

import nonDZSkillShop.Data.Data;
import nonDZSkillShop.Utils.Methodes;
import nonDZSkillShop.Utils.SleepCondition;
import xobot.script.methods.Bank;
import xobot.script.methods.NPCs;
import xobot.script.wrappers.interactive.NPC;

public class Banking {

    public static boolean canBank(){
        NPC bank = NPCs.getNearest(Data.BANK_ID);
        return bank != null && bank.isReachable();
    }

    public static void doBank(){
        NPC bank = NPCs.getNearest(Data.BANK_ID);
        if (bank != null && !Bank.isOpen()) {
            bank.interact(1);
            Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 8000);
        }

        if (Bank.isOpen()) {
            Bank.deposit(Data.ITEM_ID_TO_BUY, 28);
        }
    }
}
