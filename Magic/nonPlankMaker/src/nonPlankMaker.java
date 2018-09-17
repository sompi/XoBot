import java.awt.Graphics;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.client.events.MessageEvent;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;




@Manifest(authors = { "NonCombat" }, name = "nonPlankMaker", version = 1.2, description = "Makes planks using lunar spell, start at home.")

public final class nonPlankMaker extends ActiveScript implements PaintListener, MessageListener {

	private Timer t;
	String version = "1.2";
	int startxp;
	int oakLog = 1521;
	int mahoganyLog = 6332;
	int earthrune = 557;
	int naturerune = 561;
	int astralrune = 9075;
	int logs[] = {oakLog, mahoganyLog};
	int planks[] = {8778, 8782};
	int bankID = 26972;
	String status = "";
	boolean doneMakingPlanks = false;
	boolean outOfRunes = false;
	//8778 Oak Plank
	//8782 Mahogany Plank
	
	public boolean onStart() {
		this.t = new Timer(System.currentTimeMillis());
        startxp = Skills.MAGIC.getCurrentExp();
		return true;
	}
	
	public void onExit() {

	}
	
	public boolean hasLogs() {
		return Inventory.Contains(logs);
	}
	
	public boolean hasNatureRunes() {
		return Inventory.Contains(naturerune);
	}
	
	public boolean hasAstralRunes() {
		return Inventory.Contains(astralrune);
	}
	
	public void makePlanks() {
	    status = "Making planks.";
		Packets.sendAction(315, 0, 0, 30029);
		//Spelbook open
		/*Mouse.HopMouse(745, 187);
		Time.sleep(200);
		Mouse.click(745, 187, true);
		Time.sleep(200);

		//Lunar spell plank make
		Mouse.HopMouse(720, 333);
		Time.sleep(200);
		Mouse.click(720, 333, true);
		Time.sleep(200);*/
		while (doneMakingPlanks == false) {
			//System.out.println("Making planks...");
			Time.sleep(400);
			if (outOfRunes == true){
			    return;
            }
		}
	}

	public int oakLogAmountBank() {
		if (Bank.getItem(oakLog) != null) {
			return Bank.getItem(oakLog).getStack();
		} else {
			return 0;
		}
	}

	public int mahoganyLogAmountBank() {
		if (Bank.getItem(mahoganyLog) != null) {
			return Bank.getItem(mahoganyLog).getStack();
		} else {
			return 0;
		}
	}

	public int natureRunesAmount() {
		int bank = 0;
		int inventory = 0;
		if (Bank.getItem(naturerune) != null) {
			bank = Bank.getItem(naturerune).getStack();
		}

		if (Inventory.getItem(naturerune) != null) {
			inventory = Inventory.getItem(naturerune).getStack();
		}

		return bank + inventory;
	}
	
	public void bank() {
		GameObject bank = GameObjects.getNearest(bankID);
		Item Logs = Inventory.getItem(planks);
		status = "Banking.";
		if (bank != null) {
			bank.interact("use-quickly");
			System.out.println("Clicked use to open bank, waiting for it to open.");
		}
		
		if (Time.sleep(()-> Bank.isOpen(), 7000)) {
			System.out.println("Bank is open.");

			/*if (Inventory.Contains(planks)){
				System.out.println("Banking planks.");
				for (int i = 0; i < planks.length; i++){
					if (Inventory.getItem(planks[i]) != null){
						Bank.withdraw(planks[i], 28);
						Time.sleep(300);
					}
				}
			}*/

			if (Inventory.Contains(naturerune) && Inventory.Contains(astralrune)) {
				if (Logs != null){
					Packets.sendAction(431, Logs.getID(), Logs.getSlot(), 5064);
				}

			//Bank.depositAllExcept(naturerune, astralrune);
			} else {
				Bank.withdraw(naturerune, 100000);
				Bank.withdraw(astralrune, 100000);
			}
			Time.sleep(200);
			
			if (Bank.getItem(oakLog) != null) {
				System.out.println("We have " + oakLogAmountBank() + " oak logs");
				if (oakLogAmountBank() > 1) {
				Bank.withdraw(oakLog, 26);	
				Time.sleep(200);
				}
				
			} else if (Bank.getItem(mahoganyLog) != null) {
				System.out.println("We have " + mahoganyLogAmountBank() + " mahogany logs");
				if (mahoganyLogAmountBank() > 1) {
				Bank.withdraw(mahoganyLog, 26);
				Time.sleep(200);
				}
			}
		}

		Packets.sendAction(200, 0, 0, 5384); // Packet to close bank
		/*Mouse.HopMouse(488, 27);
		Time.sleep(200);
		Mouse.click(488, 27, true);
		Time.sleep(200);*/
        Time.sleep(1000);
	}

	public int loop() {
		System.out.println("We have an total of " + natureRunesAmount() + " nature runes");
		if (hasLogs() && hasNatureRunes() && hasAstralRunes()) {
			System.out.println("We have logs and all the runes and earthstaff equipped");
			doneMakingPlanks = false;
			makePlanks();
		} else {
			System.out.println("Missing an item, going to open bank");
			bank();
		}
		
		return 1000;
	}

	public void repaint(Graphics g1) {
		
		int xp = Skills.MAGIC.getCurrentExp() - this.startxp;
		int xph = (int) (xp * 3600000.0D / this.t.getElapsed());
		
		g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
        g1.drawString("XP Gained: " + NumberFormat.getInstance(Locale.US).format(xp), 50, 100);
        g1.drawString("XP(h): " +  NumberFormat.getInstance(Locale.US).format(xph), 50, 115);
        g1.drawString("Status " + status, 50, 130);
        g1.drawString("Version " + version, 50, 145);
		
	}

	@Override
	public void MessageRecieved(MessageEvent messageEvent) {
		if (messageEvent.getType() == 0 && messageEvent.getMessage().contains("You don't have anymore logs")){
			doneMakingPlanks = true;
		}
	}
}
