import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JDialog;

import nonBoltTips.Data.Data;
import nonBoltTips.Methodes.Banking;
import nonBoltTips.Methodes.Cut;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Bank;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;

@Manifest(authors = { "xBear" }, name = "nonBoltTips", version = 1.0, description = "Cuts gems into Bolt Tips.")

public final class nonBoltTips extends ActiveScript implements PaintListener, MessageListener {

	
	String status = "";
	String gem = "";
	String gemName;
	private int startXP = 0;
	private Timer t;
	
    private javax.swing.JButton start;
    private javax.swing.JComboBox<String> combo;
	
	@Override
	public void MessageRecieved(String message, int arg1, String arg2) {

		if (message.contains(Data.MSG_BOLT)){
			Data.BOLTTIPS_MADE += 1;
		}
	}
	
	public boolean onStart() {
		this.t = new Timer(System.currentTimeMillis());
		this.startXP = Skills.getCurrentExp(9);
		JDialog x = new JDialog();

        combo = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();

        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonBoltTips");
        x.setAlwaysOnTop(true);

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sapphire", "Emerald", "Ruby", "Diamond" }));

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
                    case "Sapphire":
                        Data.TO_CUT = Data.CUT_SAPPHIRE;
                        Data.CUTTED = Data.SAPPHIRE_BOLT_TIPS;
                    break;

                    case "Emerald":
                        Data.TO_CUT = Data.CUT_EMERALD;
                        Data.CUTTED = Data.EMERALD_BOLT_TIPS;
                        break;

                    case "Ruby":
                        Data.TO_CUT = Data.CUT_RUBY;
                        Data.CUTTED = Data.RUBY_BOLT_TIPS;
                        break;

                    case "Diamond":
                        Data.TO_CUT = Data.CUT_DIAMOND;
                        Data.CUTTED = Data.DIAMOND_BOLT_TIPS;
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
		int cutPerHour = (int) (Data.BOLTTIPS_MADE * 3600000.0D / this.t.getElapsed());
		int xpGained = Skills.getCurrentExp(9) - this.startXP;
		int xpGainedhour = (int) (xpGained * 3600000.0D / t.getElapsed());
		int currentLevel = Skills.getCurrentLevel(9);
		g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
		g1.drawString("Bolts cut " + NumberFormat.getNumberInstance(Locale.US).format(Data.BOLTTIPS_MADE) + " (" +  NumberFormat.getNumberInstance(Locale.US).format(Data.BOLTTIPS_MADE * 12) + ")", 50, 100);
		g1.drawString("Bolts cut (hr): " + NumberFormat.getNumberInstance(Locale.US).format(cutPerHour), 50, 115);
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
