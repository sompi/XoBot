package nonHerbCleaner.Methodes;

import nonHerbCleaner.Data.Data;
import nonHerbCleaner.Utils.Methodes;
import nonHerbCleaner.Utils.SleepCondition;
import nonHerbCleaner.Data.Data;
import nonHerbCleaner.Utils.Methodes;
import nonHerbCleaner.Utils.SleepCondition;
import xobot.script.methods.Packets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Item;

public class Clean {

	public static boolean canClean() {
		Item itemToCut = Inventory.getItem(Data.TO_CLEAN);
		return itemToCut != null;
	}
	
	public static void doClean() {
		Item herbToClean = Inventory.getItem(Data.TO_CLEAN);
		int herbsAmount = Inventory.getCount(Data.TO_CLEAN);
//		for (int i = 0; i < herbsAmount; i++){
//			System.out.println("Inventory contains herb to clean and amount of " + herbsAmount);
//			Packets.sendAction(74, Data.TO_CLEAN, herbToClean.getSlot(), 3214);
//			Time.sleep(5);
//		}

		for (Item toClean : Inventory.getAll(Data.TO_CLEAN)){
			System.out.println("Item found at " + toClean.getSlot() + " " + toClean.getDefinition().getName());
			Packets.sendAction(74, Data.TO_CLEAN,toClean.getSlot(), 3214);
			Time.sleep(5);
		}
	}
}
