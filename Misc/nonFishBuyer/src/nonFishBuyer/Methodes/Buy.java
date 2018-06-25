package nonFishBuyer.Methodes;

import nonFishBuyer.Data.Data;
import nonFishBuyer.Utils.Methodes;
import nonFishBuyer.Utils.SleepCondition;
import xobot.script.methods.NPCs;
import xobot.script.methods.Shop;
import xobot.script.wrappers.interactive.NPC;

public class Buy {

    public static boolean canBuy() {
        NPC shop = NPCs.getNearest(Data.HARRY);
        return shop != null;
    }

    public static void doBuy() {
        NPC shop = NPCs.getNearest(Data.HARRY);
        if (shop != null && !Shop.isOpen()) {
            shop.interact("talk-to");
            Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Shop.isOpen();
                }
            }, 15000);
        }

        if (Shop.isOpen()) {
            System.out.println("Item ID - " + Data.ITEM_ID_TO_BUY);
            Shop.buy(Data.ITEM_ID_TO_BUY, 28);
        }
    }
}
