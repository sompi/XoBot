import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;


import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.GameObjects;
import xobot.script.methods.NPCs;
import xobot.script.methods.Packets;
import xobot.script.methods.Players;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;
import xobot.script.wrappers.interactive.Character;

import javax.swing.*;

@Manifest(authors = { "NonCombat" }, name = "nonAIOHunter", version = 1.0, description = "AIO Hunter")

public final class nonAIOHunter extends ActiveScript implements PaintListener, MessageListener {

    int IMP_JAR = 11260;
    int IMP_TO_CATCH_ID = 0;
    String imp = "Loading...";
    String impName = "";
    boolean teleported = false;
    String status = "";
    private Timer t;
    int startXP = 0;
    int startLVL = 0;

    static Image image = Toolkit.getDefaultToolkit().getImage("images/bulb.gif");

    static TrayIcon trayIcon = new TrayIcon(image, "Tester2");





    @Override
    public void MessageRecieved(String arg0, int arg1, String arg2) {

    }

    public boolean onStart() {
        this.t = new Timer(System.currentTimeMillis());
        startXP = Skills.getCurrentExp(22);
        startLVL = Skills.getCurrentLevel(22);

        JDialog frame = new JDialog();
        frame.setPreferredSize(new Dimension(350,90));
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(5);
        layout.setVgap(5);
        frame.setLayout(layout);

        JComboBox<String> combo = new JComboBox<String>();
        combo.setPreferredSize(new Dimension(150,30));
        combo.setFocusable(false);
        combo.addItem("Baby Impling");
        combo.addItem("Young Imp");
        combo.addItem("Gourmet Imp");
        combo.addItem("Earth Imp");
        combo.addItem("Essence Imp");
        combo.addItem("Eclectic Imp");
        //To add
        combo.addItem("Nature Imp");
        combo.addItem("Magpie Imp");
        combo.addItem("Ninja Imp");
        combo.addItem("Dragon Imp");
        combo.addItem("Kingly Imp");

        JLabel toCatch = new JLabel();
        toCatch.setPreferredSize(new Dimension(70, 32));
        if (startLVL < 22){
            toCatch.setText("Baby Impling");
        } else if (startLVL < 28){
            toCatch.setText("Young Impling");
        } else if (startLVL < 36){
            toCatch.setText("Gourmet Impling");
        } else if (startLVL < 42){
            toCatch.setText("Earth Impling");
        } else if (startLVL < 50){
            toCatch.setText("Essence Impling");
        } else if (startLVL < 58){
            toCatch.setText("Eclectic Imp");
        } else if (startLVL < 65){
            toCatch.setText("Nature Imp");
        } else if (startLVL < 74) {
            toCatch.setText("Magpie Imp");
        } else if (startLVL < 83){
            toCatch.setText("Ninja Imp");
        } else if (startLVL < 91){
            toCatch.setText("Dragon Imp");
        } else {
            toCatch.setText("Kingly Imp");
        }


        JButton button = new JButton("Start");
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(60,32));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                imp = (String)combo.getSelectedItem();
                switch(imp) {
                    case "Baby Impling":
                        impName = "Baby Impling";
                        IMP_TO_CATCH_ID = 6055;
                        break;
                    case "Young Imp":
                        impName = "Young Imp";
                        IMP_TO_CATCH_ID = 6056;
                        break;
                    case "Gourmet Imp":
                        impName = "Gourmet Imp";
                        IMP_TO_CATCH_ID = 6057;
                        break;
                    case "Earth Imp":
                        impName = "Earth Imp";
                        IMP_TO_CATCH_ID = 6058;
                        break;
                    case "Essence Imp":
                        impName = "Essence Imp";
                        IMP_TO_CATCH_ID = 6059;
                        break;
                    case "Eclectic Imp":
                        impName = "Eclectic Imp";
                        IMP_TO_CATCH_ID = 6060;
                        break;

                    case "Nature Imp":
                        impName = "Nature Imp";
                        IMP_TO_CATCH_ID = 6061;
                        break;

                    case "Magpie Imp":
                        impName = "Magpie Imp";
                        IMP_TO_CATCH_ID = 6062;
                        break;

                    case "Ninja Imp":
                        impName = "Ninja Imp";
                        IMP_TO_CATCH_ID = 6063;
                        break;

                    case "Dragon Imp":
                        impName = "Dragon Imp";
                        IMP_TO_CATCH_ID = 6064;
                        break;

                    case "Kingly Imp":
                        impName = "Kingly Imp";
                        IMP_TO_CATCH_ID = 6065;
                        break;
                }
                frame.dispose();
            }

        });

        frame.add(combo);
        frame.add(toCatch);
        frame.add(button);
        frame.setTitle("nonAIOHunter");


        frame.pack();
        frame.setVisible(true);
        while(frame.isVisible()) {
            Time.sleep(500);
        }
        return IMP_TO_CATCH_ID != 0;
    }

    @Override
    public void repaint(Graphics g1) {

        int xp = Skills.getCurrentExp(22) - this.startXP;
        // TODO Auto-generated method stubHbjjwRvUS2s483Uw
        int xph = (int) (xp * 3600000.0D / this.t.getElapsed());
        int currentLevel = Skills.getCurrentLevel(22);
        g1.drawString("Time running " + this.t.toElapsedString(), 5, 85);
        g1.drawString("Experience gained " + NumberFormat.getNumberInstance(Locale.US).format(xp), 5, 100);
        g1.drawString("Experience hour: " + NumberFormat.getNumberInstance(Locale.US).format(xph), 5, 115);
        g1.drawString("Current level " + currentLevel, 5, 130);
        g1.drawString("Status " + status, 5, 145);

        Character interactingCharacter = Players.getMyPlayer().getInteractingCharacter();
        if(interactingCharacter != null){
            Color c = new Color(0, 255, 0);
            interactingCharacter.getLocation().draw(g1, c);
        } else {
            NPC impNPC = NPCs.getNearest(IMP_TO_CATCH_ID);
            if (impNPC != null) {
                Color c = new Color(255, 0, 21);
                impNPC.getLocation().draw(g1, c);
            }
        }
    }



    @Override
    public int loop() {

        Item impJAR = Inventory.getItem(IMP_JAR);
        NPC impNPC = NPCs.getNearest(IMP_TO_CATCH_ID);

        if (impJAR != null && impNPC != null){
            if (impNPC.getDistance() < 12){
                status = "Attempting to catch " + impName;
                impNPC.interact(0);
                Time.sleep(400);
            }
        } else {
            status = "Waiting for " + impName + " to spawn";
            Time.sleep(400);
            if (!Inventory.Contains(IMP_JAR) && teleported == false){
                status = "Teleporting";
                Packets.sendAction(315, 0, 0, 28454, 0, 0);
                Time.sleep(5000);
                teleported = true;
            }
        }
        return 1000;
    }
}
