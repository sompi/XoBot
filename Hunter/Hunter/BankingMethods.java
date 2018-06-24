

import java.util.List;

import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.tabs.Inventory;
import xobot.script.wrappers.interactive.GameObject;


public class BankingMethods {
	public static boolean canBank() {
		GameObject bank = GameObjects.getNearest(26972);
		return bank != null && bank.isReachable();
	}
	
	public static boolean needBank() {
		return !Inventory.Contains(11260) && Inventory.Contains(dridiaAutoHunter.selectedImp.getJarId());
	}
	
	public static void doBank() {
		final GameObject bank = GameObjects.getNearest(26972);

		if (bank != null && !Bank.isOpen()) {
			bank.interact("Bank");
			Methods.conditionalSleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Bank.isOpen();
				}
			}, 5000);
		}
		if (Bank.isOpen()) {
			Bank.deposit(dridiaAutoHunter.selectedImp.getJarId(), 28);
			Methods.conditionalSleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return !(Inventory.Contains(dridiaAutoHunter.selectedImp.getJarId()));
				}
			}, 5000);
		}
	}
}
