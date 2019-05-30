package nonHerbCleaner.Methodes;

import nonHerbCleaner.Data.Data;
import xobot.script.methods.Packets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Item;

public class Clean {

	public static boolean canClean() {
		Item herbToClean = Inventory.getItem(Data.TO_CLEAN);
		return herbToClean != null;
	}
	
	public static void doClean() {
			for (Item toClean : Inventory.getAll(Data.TO_CLEAN)) {
				System.out.println("Item found at " + toClean.getSlot() + " " + toClean.getDefinition().getName());
				Packets.sendAction(74, Data.TO_CLEAN, toClean.getSlot(), 3214);
				Time.sleep(10);
			}
	}
}
