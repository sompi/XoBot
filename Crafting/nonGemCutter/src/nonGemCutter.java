import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JDialog;

import nonGemCutter.Data.Data;
import nonGemCutter.Methodes.Banking;
import nonGemCutter.Methodes.Cut;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.client.events.MessageEvent;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Bank;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;

@Manifest(authors = { "xBear" }, name = "nonGemCutter", version = 1.0, description = "Cuts gems. Really, it does.")

public final class nonGemCutter extends ActiveScript implements PaintListener, MessageListener {

	
	String status = "";
	String gem = "";
	String gemName;
	private int startXP = 0;
	private Timer t;
	
    private javax.swing.JButton start;
    private javax.swing.JComboBox<String> combo;


    @Override
    public void MessageRecieved(MessageEvent message) {
        if (message.getMessage().contains(Data.MSG_SAPPHIRE) || message.getMessage().contains(Data.MSG_EMERALD) || message.getMessage().contains(Data.MSG_RUBY) || message.getMessage().contains(Data.MSG_DIAMOND)){
            Data.GEMS_CUTTED += 1;
        }
    }
	
	public boolean onStart() {
		this.t = new Timer(System.currentTimeMillis());
		this.startXP = Skills.CRAFTING.getCurrentExp();
		JDialog x = new JDialog();

        combo = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();

        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonGemCutter");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Uncut sapphire", "Uncut emerald", "Uncut ruby", "Uncut diamond" }));

        start.setText("Start");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(x.getContentPane());
        x.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(start, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(combo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                .addGap(18, 18, 18)
                .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        )));
        
        start.addActionListener(new ActionListener() {
        	 
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	gem = (String)combo.getSelectedItem();
                switch (gem){
                    case "Uncut sapphire":
                        Data.TO_CUT = Data.UNCUT_SAPPHIRE;
                        Data.CUTTED = Data.CUT_SAPPHIRE;
                        gemName = Data.MSG_SAPPHIRE;
                    break;

                    case "Uncut emerald":
                        Data.TO_CUT = Data.UNCUT_EMERALD;
                        Data.CUTTED = Data.CUT_EMERALD;
                        gemName = Data.MSG_EMERALD;
                        break;

                    case "Uncut ruby":
                        Data.TO_CUT = Data.UNCUT_RUBY;
                        Data.CUTTED = Data.CUT_RUBY;
                        gemName = Data.MSG_RUBY;
                        break;

                    case "Uncut diamond":
                        Data.TO_CUT = Data.UNCUT_DIAMOND;
                        Data.CUTTED = Data.CUT_DIAMOND;
                        gemName = Data.MSG_DIAMOND;
                        break;

                }
                x.dispose();
            }
           
        });
        
        x.pack();
        x.setVisible(true);
       
        while(x.isVisible()) {
            Time.sleep(1000);
        }
        return Data.TO_CUT != 0 && Data.CUTTED != 0;
    }
	
	

	@Override
	public void repaint(Graphics g1) {
		// TODO Auto-generated method stub
		int cutPerHour = (int) (Data.GEMS_CUTTED * 3600000.0D / this.t.getElapsed());
		int xpGained = Skills.CRAFTING.getCurrentExp() - this.startXP;
		int xpGainedhour = (int) (xpGained * 3600000.0D / t.getElapsed());
		int currentLevel = Skills.CRAFTING.getCurrentLevel();
		g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
		g1.drawString("Gems cut " + NumberFormat.getNumberInstance(Locale.US).format(Data.GEMS_CUTTED), 50, 100);
		g1.drawString("Gem cut (hr): " + NumberFormat.getNumberInstance(Locale.US).format(cutPerHour), 50, 115);
        g1.drawString("Exp gained : " + NumberFormat.getNumberInstance(Locale.US).format(xpGained), 50, 130);
        g1.drawString("Exp gained (hr): " + NumberFormat.getNumberInstance(Locale.US).format(xpGainedhour), 50, 145);
        g1.drawString("Current level : " + currentLevel, 50, 160);
		g1.drawString("Status - " + status, 50, 175);
		
	}

	public int outOfGems() {
        if (Bank.getItem(Data.TO_CUT) != null) {
            return (Bank.getItem(Data.TO_CUT).getStack());
        }

        return 0;
    }

	@Override
	public int loop() {

			if (Inventory.Contains(Data.TO_CUT)) {
				if (Cut.canCut()) {
					status = "Cutting";
					Cut.doCut();
				}
			}

			if (!Inventory.Contains(Data.TO_CUT) && Inventory.Contains(Data.CUTTED) || !Inventory.Contains(Data.TO_CUT) && !Inventory.Contains(Data.CUTTED)) {
				if (Banking.canBank()) {
					status = "Banking.";
					Banking.doBank();
					Time.sleep(300);

				}
			}

        if (outOfGems() < 1 && !Inventory.Contains(Data.TO_CUT)){
            return -1;
        }



		return 200;
	}


}
