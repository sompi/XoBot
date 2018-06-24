import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import nonMiningShopBuyer.Data.Data;
import nonMiningShopBuyer.Methodes.Banking;
import nonMiningShopBuyer.Methodes.Buy;
import nonMiningShopBuyer.Methodes.Teleport;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Bank;
import xobot.script.methods.Shop;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.interactive.Item;

@Manifest(authors = { "NonCombat" }, name = "nonMiningShopBuyer", version = 1.0, description = "Buys chosen item from Mining.")

public final class nonMiningShopBuyer extends ActiveScript implements PaintListener, MessageListener {

	
	String status = "";
	String buyingItem = "";
	private Timer t;
	@Override
	public void MessageRecieved(String arg0, int arg1, String arg2) {
				
	}
	
	public boolean onStart() {
		this.t = new Timer(System.currentTimeMillis());
		JDialog x = new JDialog();
        x.setTitle("nonMiningShopBuyer");
        x.setPreferredSize(new Dimension(240,100));
        x.setLocationRelativeTo(null);
        x.setAlwaysOnTop(true);
        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JComboBox<String> combo = new JComboBox<String>();
        combo.addItem("Hammer - 2347");
        combo.addItem("Bronze pickaxe - 1265");
        combo.addItem("Iron pickaxe - 1267");
        combo.addItem("Steel pickaxe - 1269");
        combo.addItem("Mithril pickaxe - 1263");
        combo.addItem("Adamant pickaxe - 1272");
        combo.addItem("Rune pickaxe - 1275");
        combo.addItem("Copper ore - 436");
        combo.addItem("Tin ore - 438");
        combo.addItem("Iron ore - 440");
        combo.setFocusable(false);
        combo.setPreferredSize(new Dimension(150,100));
        x.add(combo, BorderLayout.WEST);
       
        JButton button = new JButton("Start");
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	buyingItem = combo.getSelectedItem().toString().split(" - ")[0];
            	Data.ITEM_ID_TO_BUY = Integer.valueOf(combo.getSelectedItem().toString().split(" - ")[1]);
                System.out.println(Data.ITEM_ID_TO_BUY);
                x.dispose();
            }
           
        });
        x.add(button, BorderLayout.EAST);
       
       
        x.pack();
        x.setVisible(true);
       
        while(x.isVisible()) {
            Time.sleep(1000);
        }
        return Data.ITEM_ID_TO_BUY != 0;
       
    }
	
	

	@Override
	public void repaint(Graphics g1) {
		// TODO Auto-generated method stub
		int boughtPerHour = (int) (Data.ORES_BOUGHT * 3600000.0D / this.t.getElapsed());
		
		g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
		g1.drawString("Ores bought " + Data.ORES_BOUGHT, 50, 100);
		g1.drawString("Ores(hr): " + NumberFormat.getNumberInstance(Locale.US).format(boughtPerHour), 50, 115);
		g1.drawString("Status - " + status, 50, 130);
		
	}

	@Override
	public int loop() {
		
		if (Inventory.Contains(Data.COINS_ID) && !Inventory.isFull() && !Buy.isAtShop()) {
			status = "Teleporting to Shop.";
			Teleport.teleportMining();
		}
		
		if (Buy.isAtShop()) {
			status = "At shop.";
			if (Buy.canBuy()) {
				status = "Buying " + buyingItem;
				Buy.doBuy();
				Data.ORES_BOUGHT += 27;
			}
		}
		
		if (Inventory.isFull() && !Banking.isAtHome()) {
			status = "Teleporting to Home.";
			Teleport.teleportHome();
		}
		
		if (Banking.isAtHome()) {
			status = "At home.";
			if (Banking.canBank()){
				status = "Banking.";
				Banking.doBank();
			}
		}
		return 200;
	}
	
}
