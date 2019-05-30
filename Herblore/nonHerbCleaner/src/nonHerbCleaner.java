import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JDialog;

import com.sun.glass.ui.Screen;
import nonHerbCleaner.Data.Data;
import nonHerbCleaner.Methodes.Banking;
import nonHerbCleaner.Methodes.Clean;
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

@Manifest(authors = { "xBear" }, name = "nonHerbCleaner", version = 1.0, description = "Cleans Herbs.")

public final class nonHerbCleaner extends ActiveScript implements PaintListener, MessageListener {


    String status = "";
    String herb = "";
    private int startXP = 0;
    private Timer t;

    private javax.swing.JButton start;
    private javax.swing.JComboBox<String> combo;


    @Override
    public void MessageRecieved(MessageEvent message) {
        if (message.getMessage().contains("You clean the Grimy")){
            Data.CLEANED++;
        }
    }

    public boolean onStart() {
        this.t = new Timer(System.currentTimeMillis());
        this.startXP = Skills.HERBLORE.getCurrentExp();
        JDialog x = new JDialog();

        combo = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();

        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonHerbCleaner");
        x.setLocation(613, 308);

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                "Guam", "Marrentill", "Tarromin", "Harralander", "Ranarr",
                "Irit", "Avantoe", "Kwuarm", "Cadantine", "Dwarf weed",
                "Torstol", "Lantadyme", "Toadflax", "Snapdragon",
                "Spirit weed"}));

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
                herb = (String)combo.getSelectedItem();
                switch (herb){
                    case "Guam":
                        Data.TO_CLEAN = Data.GUAM;
                        break;
                    case "Marrentill":
                        Data.TO_CLEAN = Data.MARRENTILL;
                        break;
                    case "Tarromin":
                        Data.TO_CLEAN = Data.TARROMIN;
                        break;
                    case "Harralander":
                        Data.TO_CLEAN = Data.HARRALANDER;
                        break;
                    case "Ranarr":
                        Data.TO_CLEAN = Data.RANARR;
                        break;
                    case "Irit":
                        Data.TO_CLEAN = Data.IRIT;
                        break;
                    case "Avantoe":
                        Data.TO_CLEAN = Data.AVANTOE;
                        break;
                    case "Kwuarm":
                        Data.TO_CLEAN = Data.KWUARM;
                        break;
                    case "Cadantine":
                        Data.TO_CLEAN = Data.CADANTINE;
                        break;
                    case "Dwarf weed":
                        Data.TO_CLEAN = Data.DWARF_WEED;
                        break;
                    case "Torstol":
                        Data.TO_CLEAN = Data.TORSTOL;
                        break;
                    case "Lantadyme":
                        Data.TO_CLEAN = Data.LANTADYME;
                        break;
                    case "Toadflax":
                        Data.TO_CLEAN = Data.TOADFLAX;
                        break;
                    case "Snapdragon":
                        Data.TO_CLEAN = Data.SNAPDRAGON;
                        break;
                    case "Spirit weed":
                        Data.TO_CLEAN = Data.SPIRIT_WEED;
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
        return Data.TO_CLEAN != 0;
    }



    @Override
    public void repaint(Graphics g1) {
        // TODO Auto-generated method stub
        int cleanedPerHour = (int) (Data.CLEANED * 3600000.0D / this.t.getElapsed());
        int xpGained = Skills.HERBLORE.getCurrentExp() - this.startXP;
        int xpGainedhour = (int) (xpGained * 3600000.0D / t.getElapsed());
        int currentLevel = Skills.HERBLORE.getCurrentLevel();
        g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
        g1.drawString("Herbs cleaned " + NumberFormat.getNumberInstance(Locale.US).format(Data.CLEANED), 50, 100);
        g1.drawString("Herbs cleaned (hr): " + NumberFormat.getNumberInstance(Locale.US).format(cleanedPerHour), 50, 115);
        g1.drawString("Exp gained : " + NumberFormat.getNumberInstance(Locale.US).format(xpGained), 50, 130);
        g1.drawString("Exp gained (hr): " + NumberFormat.getNumberInstance(Locale.US).format(xpGainedhour), 50, 145);
        g1.drawString("Current level : " + currentLevel, 50, 160);
        g1.drawString("Status - " + status, 50, 175);

    }

    public int outOfHerbs() {
        if (Bank.getItem(Data.TO_CLEAN) != null) {
            return (Bank.getItem(Data.TO_CLEAN).getStack());
        }
        return 0;
    }

    @Override
    public int loop() {

        System.out.println("Herb to clean " + Data.TO_CLEAN);
        if (Inventory.Contains(Data.TO_CLEAN)) {
            System.out.println("inventory contains herb to clean " + Data.TO_CLEAN);
            if (Clean.canClean()) {
                status = "Cleaning";
                Clean.doClean();
            }
        }

        if (!Inventory.Contains(Data.TO_CLEAN) || !Inventory.Contains(Data.TO_CLEAN)) {
            System.out.println("inventory does not contain herb to clean " + Data.TO_CLEAN);
            if (Banking.canBank()) {
                status = "Banking.";
                Banking.doBank();
                Time.sleep(300);

            }
        }

        if (outOfHerbs() < 1 && !Inventory.Contains(Data.TO_CLEAN)){
            return -1;
        }

        return 200;
    }


}
