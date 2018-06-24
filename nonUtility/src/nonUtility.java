import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Item;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Manifest(authors = { "xBear" }, name = "nonUtility", version = 1.0, description = "Gets easily some valueable information for you.")

public final class nonUtility extends ActiveScript implements PaintListener, MessageListener {

    public String fileName;
    public String XoBotFolder = getXobotPath() + "nonUtility/";

    private javax.swing.JButton getInventoryItems;
    private javax.swing.JButton deleteInventory;

    public static String getXobotPath()
    {
        final StringBuilder builder = new StringBuilder();
        final String separator = System.getProperty("file.separator");
        builder.append(System.getProperty("user.home")).append(separator).append("Documents").append(separator).append("XoBot").append(separator);
        return builder.toString();
    }

    public void makeFolder(){
        final File folder = new File(XoBotFolder);

        if (!folder.exists())
        {
            System.out.println("Folder does not exist, creating.");
            folder.mkdir();
        } else {
            System.out.println("Folder already exists.");
        }
    }

    public void makeFile(String fileName){
        final File file = new File(getXobotPath() + "nonUtility/"+fileName+".txt");
        fileName = getXobotPath() + "nonUtility/"+fileName+".txt";
        if (!file.exists())
        {
            System.out.println("File does not exist, creating.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists.");
        }
    }

    public void writeInventory() throws IOException {

        FileWriter fw = new FileWriter(XoBotFolder + "/Inventory.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/Inventory.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (Item item : Inventory.getItems()){
            if (item != null){
                bw.write("public static int " + item.getDefinition().getName().toUpperCase().replace(" ", "_") + " = " + item.getID() + ";");
                bw.newLine();
            }
        }
        bw.close();

    }
	
	public void writeEquipment() throws IOException{
		 FileWriter fw = new FileWriter(XoBotFolder + "/Equipment.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/Equipment.txt");
        BufferedWriter bw = new BufferedWriter(fw);
                for (Item item: Equipment.getEquipment()) {
				bw.write("public static int " + item.getDefinition().getName().toUpperCase().replace(" ", "_") + " = " + item.getID() + ";");
				bw.newLine();
				}
        bw.close();
	}
	
	    public void getEquipment(){

        Item helmet = Equipment.getItem(Equipment.HELMET);
        Item necklace = Equipment.getItem(Equipment.NECK);
        Item cape = Equipment.getItem(Equipment.CAPE);
        Item ammo = Equipment.getItem(Equipment.AMMO);
        Item weapon = Equipment.getItem(Equipment.WEAPON);
        Item shield = Equipment.getItem(Equipment.SHIELD);
        Item body = Equipment.getItem(Equipment.BODY);
        Item legs = Equipment.getItem(Equipment.LEGS);
        Item boots = Equipment.getItem(Equipment.FEET);
        Item ring = Equipment.getItem(Equipment.RING);

        if (helmet != null){
            System.out.println(helmet.getDefinition().getName() + " - " + helmet.getID());
        }

        if (necklace != null){
            System.out.println(necklace.getDefinition().getName() + " - " + necklace.getID());
        }

        if (cape != null){
            System.out.println(cape.getDefinition().getName() + " - " + cape.getID());
        }

        if (ammo != null){
            System.out.println(ammo.getDefinition().getName() + " - " + ammo.getID());
        }
        if (weapon != null){
            System.out.println(weapon.getDefinition().getName() + " - " + weapon.getID());
        }
        if (shield != null){
            System.out.println(shield.getDefinition().getName() + " - " + shield.getID());
        }
        if (body != null){
            System.out.println(body.getDefinition().getName() + " - " + body.getID());
        }
        if (legs != null){
            System.out.println(legs.getDefinition().getName() + " - " + legs.getID());
        }
        if (boots != null){
            System.out.println(boots.getDefinition().getName() + " - " + boots.getID());
        }
        if (ring != null){
            System.out.println(ring.getDefinition().getName() + " - " + ring.getID());
        }
    }

    public void deleteInventoryFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/Inventory.txt");
        Files.delete(fileToDeletePath);
    }
    @Override
    public boolean onStart(){
        makeFolder();
        getInventoryItems = new javax.swing.JButton();
        deleteInventory = new javax.swing.JButton();
        JDialog x = new JDialog();
        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonUtility");
        x.setAlwaysOnTop(true);

        getInventoryItems.setText("Inventory items");
        getInventoryItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Getting inventory data");
                makeFile("Inventory");
                try {
                    writeInventory();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        deleteInventory.setText("Delete inventory");
        deleteInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Clicked to delete file");
                try {
                    deleteInventoryFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(x.getContentPane());
        x.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getInventoryItems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteInventory)
                                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getInventoryItems)
                                        .addComponent(deleteInventory))
                                .addContainerGap(169, Short.MAX_VALUE))
        );


        x.pack();
        x.setVisible(true);
        while (x.isVisible()){
            Time.sleep(500);
        }

        return true;
    }


    @Override
    public void MessageRecieved(String msg, int i, String s1) {

    }

    @Override
    public void repaint(Graphics g1) {

    }



    @Override
    public int loop() {


        return 1000;
    }
}
