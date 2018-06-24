package nonMiningShopBuyer.Methodes;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import nonMiningShopBuyer.Data.Data;
import nonMiningShopBuyer.Utils.Methodes;
import nonMiningShopBuyer.Utils.SleepCondition;
import xobot.script.methods.NPCs;
import xobot.script.methods.Players;
import xobot.script.methods.Shop;
import xobot.script.methods.tabs.Inventory;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

public class Buy {

	public static boolean canBuy() {
		NPC shop = NPCs.getNearest(Data.NURMOF_ID);
		return shop != null && shop.isReachable();
	}
	
	public static void doBuy() {
		NPC shop = NPCs.getNearest(Data.NURMOF_ID);
		if (shop != null && !Shop.isOpen()) {
			shop.interact("talk-to");
			Methodes.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Shop.isOpen();
                }
            }, 5000);
		}
		
		if (Shop.isOpen()) {
			System.out.println("Item ID - " + Data.ITEM_ID_TO_BUY);
			Shop.buy(Data.ITEM_ID_TO_BUY, 28);
		}
	}
	
    public static boolean isAtShop(){
        Area arr = Data.MINING_AREA;
        if (arr.contains(Players.getMyPlayer().getLocation())) {
        	return true;
        }
        return false;
    }
}
