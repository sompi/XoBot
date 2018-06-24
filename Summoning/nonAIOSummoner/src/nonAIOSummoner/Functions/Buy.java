package nonAIOSummoner.Functions;

import nonAIOSummoner.Data.Data;
import xobot.script.methods.NPCs;
import xobot.script.methods.Shop;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

public class Buy {

    public static boolean canBuy(){
        NPC summonShop = NPCs.getNearest(Data.PIKKUPSTIX);
        return summonShop != null;
    }

    public static int buyAmount(){
        Item charm = Inventory.getItem(Data.REQUIRED_CHARM);
        if (charm != null) {
            return Inventory.getItem(Data.REQUIRED_CHARM).getStack();
        } else {
            return 0;
        }
    }

    public static void doBuy(){
        Data.status = "Buying ingredients";
        NPC summonShop = NPCs.getNearest(Data.PIKKUPSTIX);

        if (summonShop != null){
            System.out.println("Summon shop is not null");
            summonShop.interact("trade");
            if (Time.sleep(()-> Shop.isOpen(), 4000)){
                System.out.println("Summoning shop is open");
                Shop.buy(Data.REQUIRED_INGREDIENT, buyAmount());
                Time.sleep(500);
            }
        } else {
            System.out.println("Summon shop is null");
        }

    }
}
