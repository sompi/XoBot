package nonMiningShopBuyer.Methodes;

import nonMiningShopBuyer.Data.Data;
import nonMiningShopBuyer.Utils.Methodes;
import nonMiningShopBuyer.Utils.SleepCondition;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;

public class Banking {
	
	public static int amountToBank() {
		Item item = Inventory.getItem(Data.IRON_ORE_ID);
		
		if (item != null) {
			return Inventory.getCount(Data.IRON_ORE_ID);
		}else {
			return 0;
		}
	}
	
    public static boolean canBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_ID);
        return bank != null && bank.isReachable();
    }

    public static void doBank(){
        GameObject bank = GameObjects.getNearest(Data.BANK_ID);
        if (bank != null && !Bank.isOpen()) {
            bank.interact("Bank");
            Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 8000);
        }

        if (Bank.isOpen()) {
            //Bank.depositAllExcept(Data.COINS_ID);
            Bank.deposit(Data.ITEM_ID_TO_BUY, 28);
            
        }
    }
    
    public static boolean isAtHome(){
        Area arr = Data.HOME_AREA;
        if (arr.contains(Players.getMyPlayer().getLocation())) {
        	return true;
        }
        return false;
    }
}
