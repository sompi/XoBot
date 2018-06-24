import java.awt.Graphics;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Bank;
import xobot.script.methods.Game;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.Players;
import xobot.script.methods.input.KeyBoard;
import xobot.script.methods.tabs.Equipment;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;




@Manifest(authors = { "NonCombat" }, name = "nonPlankMaker", version = 1.0, description = "Makes planks using lunar spell, start at home.")

public final class nonPlankMaker extends ActiveScript implements PaintListener, MessageListener {

	private Timer t;
	String version = "1.1";
	private int startxp;
	private int startLvl;
	int oakLog = 1521;
	int mahoganyLog = 6332;
	int earthrune = 557;
	int naturerune = 561;
	int astralrune = 9075;
	int earthstaff = 1385;
	int plankMakeAnimation = 6298; //Stays active until none planks
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
        this.startxp = Skills.getCurrentExp(Skills.MAGIC);
        this.startLvl = Skills.getCurrentLevel(Skills.MAGIC);
		return true;
	}
	
	public void onExit() {
		System.out.println("Testing");
	}
	
	public boolean hasLogs() {
		return Inventory.Contains(logs);
	}
	
	public int logAmount() {
		int oakLogCount = Inventory.getCount(oakLog);
		int mahoganyLogCount = Inventory.getCount(mahoganyLog);		
		return oakLogCount + mahoganyLogCount;
	}
	
	public int logAmountBank() {

		return 1;
	}
	
	public boolean hasNatureRunes() {
		return Inventory.Contains(naturerune);
	}
	
	public boolean hasAstralRunes() {
		return Inventory.Contains(astralrune);
	}
	
	public boolean hasEarthRunes() {
		return Inventory.Contains(earthrune);
	}
	
	public void makePlanks() {
	    status = "Making planks.";
		Packets.sendAction(315, 0, 0, 30029, 0, 0);
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
	
	public int astralRunesAmount() {
		int bank = 0;
		int inventory = 0;
		if (Bank.getItem(astralrune) != null) {
			bank = Bank.getItem(astralrune).getStack();
		}
		
		if (Inventory.getItem(astralrune) != null) {
			inventory = Inventory.getItem(astralrune).getStack();
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
			if (Inventory.Contains(naturerune) && Inventory.Contains(astralrune)) {
				if (Logs != null){
					Packets.sendAction(431, Logs.getID(), Logs.getSlot(), 5064, 26972, 0);
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

		Packets.sendAction(200, 0, 0, 5384, 0); // Packet to close bank
        Time.sleep(1000);
        logAmount();
        System.out.println("We have now have " + logAmount() + " in our inventory");
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
		
		if (oakLogAmountBank() < 1 && mahoganyLogAmountBank() < 1 && logAmount() < 1) {
			System.out.println("Out of logs");
			return -1;
		}
		
		if (astralRunesAmount() <= 2 || natureRunesAmount() <= 1) {
			System.out.println("Out of runes");
			return -1;
		}

		if (outOfRunes == true){
		    return -1;
        }
		
		return 1000;
	}

	public void repaint(Graphics g1) {
		
		int xp = Skills.getCurrentExp(Skills.MAGIC) - this.startxp;
		int xph = (int) (xp * 3600000.0D / this.t.getElapsed());
		
		g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
        g1.drawString("XP Gained: " + NumberFormat.getInstance(Locale.US).format(xp), 50, 100);
        g1.drawString("XP(h): " +  NumberFormat.getInstance(Locale.US).format(xph), 50, 115);
        g1.drawString("Total logs in bank " + (oakLogAmountBank() + mahoganyLogAmountBank()), 50, 130);
        g1.drawString("Status " + status, 50, 145);
        g1.drawString("Version " + version, 50, 160);
		
	}
	
	public static float RoundItUp(float number, int decimalPlace) {
		if (number > 0) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		
		return bd.floatValue();
		} else {
			return 0;
		}
	}

	@Override
	public void MessageRecieved(String msg, int arg1, String arg2) {
		msg.toLowerCase();
		if (msg.contains("You don't have anymore logs to turn into planks.")) {
			doneMakingPlanks = true;
			System.out.println("Not making planks anymore");
		}

		if (msg.contains("You don't have the required runes to cast this spell.")){
		    outOfRunes = true;
        }
	}
}
