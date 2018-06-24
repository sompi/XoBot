package nonBoltTips.Methodes;

import nonBoltTips.Data.Data;
import nonBoltTips.Utils.Methodes;
import nonBoltTips.Utils.SleepCondition;
import xobot.script.methods.Packets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Item;

public class Cut {

	public static boolean canCut() {
		Item itemToCut = Inventory.getItem(Data.TO_CUT);
		return itemToCut != null;
	}
	
	public static void doCut() {
		Item itemToCut = Inventory.getItem(Data.TO_CUT);
		Item chisel = Inventory.getItem(Data.CHISEL_ID);
			if (chisel != null && itemToCut != null) {
				Packets.sendAction(447, Data.CHISEL_ID, chisel.getSlot(), 3214, 0, 0);
				Time.sleep(200);
				Packets.sendAction(870, Data.TO_CUT, itemToCut.getSlot(), 3214, 38150, 0);
				Methodes.conditionalSleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return !Inventory.Contains(Data.TO_CUT);
					}
				}, 20000);
			}
	}
}
