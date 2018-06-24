package nonUtilityGUI;

import xobot.script.methods.NPCs;
import xobot.script.methods.tabs.Equipment;
import xobot.script.methods.tabs.Inventory;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    public static String XoBotFolder = getXobotPath() + "nonUtility/";
    private JDialog x;
    public static boolean isAlreadyVisible = false;
    public static int drawDistanceRange = 0;

    public GUI() {
        initialize();
        x.setVisible(true);
        x.setAlwaysOnTop(true);
        isAlreadyVisible = true;
    }

    private void initialize(){


        tabPane = new javax.swing.JTabbedPane();
        inventoryTab = new javax.swing.JPanel();
        getInventoryItems = new javax.swing.JButton();
        deleteInventory = new javax.swing.JButton();
        equipmentTab = new javax.swing.JPanel();
        getEquipment = new javax.swing.JButton();
        deleteEquipment = new javax.swing.JButton();
        npcTab = new javax.swing.JPanel();
        drawDistance = new javax.swing.JSlider();
        label_MaxDrawDistance = new javax.swing.JLabel();
        getNPC = new javax.swing.JButton();
        getNPCFormat = new javax.swing.JTextField();
        drawNPCS = new javax.swing.JButton();


        x = new JDialog();
        JPanel panel = new JPanel();
        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonUtility");
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
        inventoryTab.setLayout(inventoryTabLayout);
        inventoryTabLayout.setHorizontalGroup(
                inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inventoryTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getInventoryItems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteInventory)
                                .addContainerGap(255, Short.MAX_VALUE))
        );
        inventoryTabLayout.setVerticalGroup(
                inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inventoryTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getInventoryItems)
                                        .addComponent(deleteInventory))
                                .addContainerGap(213, Short.MAX_VALUE))
        );

        tabPane.addTab("Inventory", inventoryTab);

        getEquipment.setText("Equipment");


        deleteEquipment.setText("Delete equipment file");


        javax.swing.GroupLayout equipmentTabLayout = new javax.swing.GroupLayout(equipmentTab);
        equipmentTab.setLayout(equipmentTabLayout);
        equipmentTabLayout.setHorizontalGroup(
                equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(equipmentTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getEquipment)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteEquipment)
                                .addContainerGap(249, Short.MAX_VALUE))
        );
        equipmentTabLayout.setVerticalGroup(
                equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(equipmentTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getEquipment)
                                        .addComponent(deleteEquipment))
                                .addContainerGap(213, Short.MAX_VALUE))
        );

        tabPane.addTab("Equipment", equipmentTab);

        drawDistance.setMajorTickSpacing(5);
        drawDistance.setMaximum(30);
        drawDistance.setMinorTickSpacing(1);
        drawDistance.setPaintLabels(true);
        drawDistance.setPaintTicks(true);
        drawDistance.setSnapToTicks(true);
        drawDistance.setValue(10);

        label_MaxDrawDistance.setText("Get from maxium distance");

        getNPC.setText("Get NPC");
        getNPCFormat.setText("public static int");


        getNPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Gettinh NPC data");
                makeFile("NPCS");
                try {
                    writeNPCS();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        drawDistance.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                System.out.println(drawDistance.getValue());
                drawDistanceRange = drawDistance.getValue();
            }
        });



        javax.swing.GroupLayout npcTabLayout = new javax.swing.GroupLayout(npcTab);
        npcTab.setLayout(npcTabLayout);
        npcTabLayout.setHorizontalGroup(
                npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(npcTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(npcTabLayout.createSequentialGroup()
                                                .addComponent(getNPCFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(npcTabLayout.createSequentialGroup()
                                                .addComponent(getNPC)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                                .addGroup(npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, npcTabLayout.createSequentialGroup()
                                                                .addComponent(drawDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, npcTabLayout.createSequentialGroup()
                                                                .addComponent(label_MaxDrawDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(46, 46, 46))))))
        );
        npcTabLayout.setVerticalGroup(
                npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(npcTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(npcTabLayout.createSequentialGroup()
                                                .addComponent(drawDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(label_MaxDrawDistance))
                                        .addComponent(getNPC))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(getNPCFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))
        );

        tabPane.addTab("NPC", npcTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(x.getContentPane());
        x.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabPane)
        );

        tabPane.getAccessibleContext().setAccessibleName("tabPane");



        x.pack();

    }

    private javax.swing.JButton deleteEquipment;
    private javax.swing.JButton deleteInventory;
    private static javax.swing.JSlider drawDistance;
    private javax.swing.JButton drawNPCS;
    private javax.swing.JPanel equipmentTab;
    private javax.swing.JButton getEquipment;
    private javax.swing.JButton getInventoryItems;
    private javax.swing.JButton getNPC;
    private javax.swing.JPanel inventoryTab;
    private javax.swing.JLabel label_MaxDrawDistance;
    private javax.swing.JPanel npcTab;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTextField getNPCFormat;





    /* Functions */


    public static String getXobotPath()
    {
        final StringBuilder builder = new StringBuilder();
        final String separator = System.getProperty("file.separator");
        builder.append(System.getProperty("user.home")).append(separator).append("Documents").append(separator).append("XoBot").append(separator);
        return builder.toString();
    }

    public static void makeFolder(){
        final File folder = new File(XoBotFolder);

        if (!folder.exists())
        {
            System.out.println("Folder does not exist, creating.");
            folder.mkdir();
        } else {
            System.out.println("Folder already exists.");
        }
    }

    public static void makeFile(String fileName){
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

    public static void deleteInventoryFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/Inventory.txt");
        Files.delete(fileToDeletePath);
    }

    public void writeNPCS() throws IOException {
        List<Object> NPCIDS = new ArrayList<>();
        FileWriter fw = new FileWriter(XoBotFolder + "/NPCS.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/NPCS.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (NPC npc : NPCs.getAll()){
            if (npc != null && npc.getDistance() <= drawDistance.getValue() && npc.getId() != 1 && !NPCIDS.contains(npc.getId())){
                System.out.println("Adding " + npc.npc.getDef().getName() + " - " + npc.getId());
                bw.write(getNPCFormat.getText() + " " + npc.npc.getDef().getName().toUpperCase().replace(" ", "_") + " = " + npc.getId() + ";");
                bw.newLine();
            }
        }

       bw.close();

    }

    public static void deleteNPCSFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/NPCS.txt");
        Files.delete(fileToDeletePath);
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

    public static void deleteEquipmentFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/Equipment.txt");
        Files.delete(fileToDeletePath);
    }



}
