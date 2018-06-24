package nonUtilityGUI;

import xobot.script.methods.GameObjects;
import xobot.script.methods.NPCs;
import xobot.script.methods.Players;
import xobot.script.methods.tabs.Equipment;
import xobot.script.methods.tabs.Inventory;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public static int distanceToDrawForNPCS = 0;
    public static int distanceToDrawForGameObjects = 0;


    public static boolean atNPCTab = false;
    public static boolean atGameObjectsTab = false;
    public static boolean atAreaTab = false;

    public static boolean showArea = false;
    public static int areaStartX;
    public static int areaStartY;
    public static int areaEndX;
    public static int areaEndY;
    public static Area areaToDraw = new Area( 0,  0,  0, 0);

    public GUI() {
        initialize();
        x.setVisible(true);
        x.setAlwaysOnTop(true);
        isAlreadyVisible = true;
    }

    private void initialize(){


        jPanel1 = new javax.swing.JPanel();
        tabPane = new javax.swing.JTabbedPane();
        inventoryTab = new javax.swing.JPanel();
        getInventoryItems = new javax.swing.JButton();
        deleteInventory = new javax.swing.JButton();
        equipmentTab = new javax.swing.JPanel();
        getEquipment = new javax.swing.JButton();
        deleteEquipment = new javax.swing.JButton();
        npcTab = new javax.swing.JPanel();
        drawDistanceNPCS = new javax.swing.JSlider();
        label_MaxDrawDistanceNPCS = new javax.swing.JLabel();
        getNPC = new javax.swing.JButton();
        deleteNPCS = new javax.swing.JButton();
        tileTab = new javax.swing.JPanel();
        getTile = new javax.swing.JButton();
        deleteTile = new javax.swing.JButton();
        areaTab = new javax.swing.JPanel();
        getTileStart = new javax.swing.JButton();
        getTileEnd = new javax.swing.JButton();
        saveArea = new javax.swing.JButton();
        areaShow = new javax.swing.JButton();
        deleteArea = new javax.swing.JButton();
        gameObjectTab = new javax.swing.JPanel();
        getGameObjects = new javax.swing.JButton();
        deleteGameObjects = new javax.swing.JButton();
        label_MaxDrawDistanceGameObjects = new javax.swing.JLabel();
        drawDistanceGameObjects = new javax.swing.JSlider();
        shopTab = new javax.swing.JPanel();
        getShopItems = new javax.swing.JButton();
        deleteShop = new javax.swing.JButton();


        x = new JDialog();
        JPanel panel = new JPanel();
        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonUtility");

        /* Event Handlers */

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


        getEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                makeFile("Equipment");
                try {
                    writeEquipment();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteEquipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Clicked to delete file");
                try {
                    deleteEquipmentFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        getNPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                makeFile("NPCS");
                try {
                    writeNPCS();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        drawDistanceNPCS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                distanceToDrawForNPCS = drawDistanceNPCS.getValue();
            }
        });

        deleteNPCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Clicked to delete file");
                try {
                    deleteNPCSFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        getTile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Tile information.");
                makeFile("Tile");
                try {
                    writeTile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteTile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Clicked to delete file");
                try {
                    deleteTileFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        /* Area */

        saveArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                makeFile("Area");
                try {
                    writeArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });



        areaShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Drawing area!");
                areaToDraw = new Area(areaStartX, areaStartY, areaEndX, areaEndY);
                showArea = true;
            }
        });

        getTileStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                areaStartX = Players.getMyPlayer().getLocation().getX();
                areaStartY = Players.getMyPlayer().getLocation().getY();
                System.out.println("Start X " + areaStartX + " Start Y " + areaStartY);
            }
        });

        getTileEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                areaEndX = Players.getMyPlayer().getLocation().getX();
                areaEndY = Players.getMyPlayer().getLocation().getY();
                System.out.println("End X " + areaEndX + " End Y " + areaEndY);
            }
        });

        deleteArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Clicked to delete file");
                try {
                    deleteAreaFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        getGameObjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                makeFile("GameObjects");
                try {
                    writeGameObjects();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        drawDistanceGameObjects.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                System.out.println(drawDistanceGameObjects.getValue());
                distanceToDrawForGameObjects = drawDistanceGameObjects.getValue();
            }
        });

        deleteGameObjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Clicked to delete file");
                try {
                    deleteGameObjectsFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });





        /* Tab Events handlers */

        tabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {



                if (tabPane.getSelectedIndex() == 2){
                    atNPCTab = true;
                } else {
                    atNPCTab = false;
                }

                if (tabPane.getSelectedIndex() == 4){
                    atAreaTab = true;
                } else {
                    atAreaTab = false;
                }

                if (tabPane.getSelectedIndex() == 5){
                    atGameObjectsTab = true;
                } else {
                    atGameObjectsTab = false;
                }



            }
        });




        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        getInventoryItems.setText("Inventory");

        deleteInventory.setText("Delete inventory file");

        javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
        inventoryTab.setLayout(inventoryTabLayout);
        inventoryTabLayout.setHorizontalGroup(
                inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inventoryTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getInventoryItems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteInventory)
                                .addContainerGap(263, Short.MAX_VALUE))
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
                                .addContainerGap(257, Short.MAX_VALUE))
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

        drawDistanceNPCS.setMajorTickSpacing(5);
        drawDistanceNPCS.setMaximum(30);
        drawDistanceNPCS.setMinorTickSpacing(1);
        drawDistanceNPCS.setPaintLabels(true);
        drawDistanceNPCS.setPaintTicks(true);
        drawDistanceNPCS.setSnapToTicks(true);
        drawDistanceNPCS.setValue(10);

        label_MaxDrawDistanceNPCS.setText("Get from maxium distance");

        getNPC.setText("Get NPC");

        deleteNPCS.setText("Delete NPCS file");

        javax.swing.GroupLayout npcTabLayout = new javax.swing.GroupLayout(npcTab);
        npcTab.setLayout(npcTabLayout);
        npcTabLayout.setHorizontalGroup(
                npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(npcTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(getNPC)
                                        .addComponent(deleteNPCS))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                                .addGroup(npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, npcTabLayout.createSequentialGroup()
                                                .addComponent(drawDistanceNPCS, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, npcTabLayout.createSequentialGroup()
                                                .addComponent(label_MaxDrawDistanceNPCS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46))))
        );
        npcTabLayout.setVerticalGroup(
                npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(npcTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(npcTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(npcTabLayout.createSequentialGroup()
                                                .addComponent(drawDistanceNPCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(label_MaxDrawDistanceNPCS))
                                        .addGroup(npcTabLayout.createSequentialGroup()
                                                .addComponent(getNPC)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deleteNPCS)))
                                .addContainerGap(166, Short.MAX_VALUE))
        );

        deleteNPCS.getAccessibleContext().setAccessibleName("Delete NPCS file");

        tabPane.addTab("NPC", npcTab);

        getTile.setText("Get Tile");

        deleteTile.setText("Delete Tile file");

        javax.swing.GroupLayout tileTabLayout = new javax.swing.GroupLayout(tileTab);
        tileTab.setLayout(tileTabLayout);
        tileTabLayout.setHorizontalGroup(
                tileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tileTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(getTile)
                                        .addComponent(deleteTile))
                                .addContainerGap(380, Short.MAX_VALUE))
        );
        tileTabLayout.setVerticalGroup(
                tileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tileTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getTile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteTile)
                                .addContainerGap(184, Short.MAX_VALUE))
        );

        tabPane.addTab("Tile", tileTab);

        getTileStart.setText("Get Tile Start");

        getTileEnd.setText("Get Tile End");

        saveArea.setText("Save Area");

        areaShow.setText("Show Area");

        deleteArea.setText("Delete Area file");

        javax.swing.GroupLayout areaTabLayout = new javax.swing.GroupLayout(areaTab);
        areaTab.setLayout(areaTabLayout);
        areaTabLayout.setHorizontalGroup(
                areaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(areaTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(areaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(saveArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(getTileStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(areaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(getTileEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(deleteArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(areaShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(180, 180, 180))
        );
        areaTabLayout.setVerticalGroup(
                areaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(areaTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(areaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getTileStart)
                                        .addComponent(getTileEnd)
                                        .addComponent(areaShow))
                                .addGap(18, 18, 18)
                                .addGroup(areaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveArea)
                                        .addComponent(deleteArea))
                                .addContainerGap(172, Short.MAX_VALUE))
        );

        tabPane.addTab("Area", areaTab);

        getGameObjects.setText("Get GameObjects");

        deleteGameObjects.setText("Delete GameObjects file");

        label_MaxDrawDistanceGameObjects.setText("Get from maxium distance");

        drawDistanceGameObjects.setMajorTickSpacing(5);
        drawDistanceGameObjects.setMaximum(30);
        drawDistanceGameObjects.setMinorTickSpacing(1);
        drawDistanceGameObjects.setPaintLabels(true);
        drawDistanceGameObjects.setPaintTicks(true);
        drawDistanceGameObjects.setSnapToTicks(true);
        drawDistanceGameObjects.setValue(10);

        javax.swing.GroupLayout gameObjectTabLayout = new javax.swing.GroupLayout(gameObjectTab);
        gameObjectTab.setLayout(gameObjectTabLayout);
        gameObjectTabLayout.setHorizontalGroup(
                gameObjectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(gameObjectTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gameObjectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(getGameObjects)
                                        .addComponent(deleteGameObjects))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(gameObjectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameObjectTabLayout.createSequentialGroup()
                                                .addComponent(drawDistanceGameObjects, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameObjectTabLayout.createSequentialGroup()
                                                .addComponent(label_MaxDrawDistanceGameObjects, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46))))
        );
        gameObjectTabLayout.setVerticalGroup(
                gameObjectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(gameObjectTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gameObjectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(gameObjectTabLayout.createSequentialGroup()
                                                .addComponent(drawDistanceGameObjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(label_MaxDrawDistanceGameObjects))
                                        .addGroup(gameObjectTabLayout.createSequentialGroup()
                                                .addComponent(getGameObjects)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deleteGameObjects)))
                                .addContainerGap(166, Short.MAX_VALUE))
        );

        tabPane.addTab("Game Object", gameObjectTab);

        getShopItems.setText("Shop items");

        deleteShop.setText("Delete shop file");

        javax.swing.GroupLayout shopTabLayout = new javax.swing.GroupLayout(shopTab);
        shopTab.setLayout(shopTabLayout);
        shopTabLayout.setHorizontalGroup(
                shopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(shopTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getShopItems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteShop)
                                .addContainerGap(281, Short.MAX_VALUE))
        );
        shopTabLayout.setVerticalGroup(
                shopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(shopTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(shopTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getShopItems)
                                        .addComponent(deleteShop))
                                .addContainerGap(213, Short.MAX_VALUE))
        );

        tabPane.addTab("Shop", shopTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(x.getContentPane());
        x.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabPane)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabPane)
        );

        tabPane.getAccessibleContext().setAccessibleName("tabPane");



        x.pack();

    }

    private javax.swing.JButton areaShow;
    private javax.swing.JPanel areaTab;
    private javax.swing.JButton deleteArea;
    private javax.swing.JButton deleteEquipment;
    private javax.swing.JButton deleteGameObjects;
    private javax.swing.JButton deleteInventory;
    private javax.swing.JButton deleteNPCS;
    private javax.swing.JButton deleteShop;
    private javax.swing.JButton deleteTile;
    public static javax.swing.JSlider drawDistanceGameObjects;
    public static javax.swing.JSlider drawDistanceNPCS;
    private javax.swing.JPanel equipmentTab;
    private javax.swing.JPanel gameObjectTab;
    private javax.swing.JButton getEquipment;
    private javax.swing.JButton getGameObjects;
    private javax.swing.JButton getInventoryItems;
    private javax.swing.JButton getNPC;
    private javax.swing.JButton getShopItems;
    private javax.swing.JButton getTile;
    private javax.swing.JButton getTileEnd;
    private javax.swing.JButton getTileStart;
    private javax.swing.JPanel inventoryTab;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_MaxDrawDistanceGameObjects;
    private javax.swing.JLabel label_MaxDrawDistanceNPCS;
    private javax.swing.JPanel npcTab;
    private javax.swing.JButton saveArea;
    private javax.swing.JPanel shopTab;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JPanel tileTab;





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

    public void writeNPCS() throws IOException {
        List<Object> NPCIDS = new ArrayList<>();
        FileWriter fw = new FileWriter(XoBotFolder + "/NPCS.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/NPCS.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (NPC npc : NPCs.getAll()){
            if (npc != null && npc.getDistance() <= drawDistanceNPCS.getValue() && npc.getId() != 1 && !NPCIDS.contains(npc.getId())){
                System.out.println("Adding " + npc.npc.getDef().getName() + " - " + npc.getId());
                bw.write("public static int " + npc.npc.getDef().getName().toUpperCase().replace(" ", "_") + " = " + npc.getId() + ";");
                bw.newLine();
            }
        }

       bw.close();

    }

    public static void deleteNPCSFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/NPCS.txt");
        Files.delete(fileToDeletePath);
    }

    public void writeTile() throws IOException {

        int X = Players.getMyPlayer().getLocation().getX();
        int Y = Players.getMyPlayer().getLocation().getY();
        int Plane = Players.getMyPlayer().getLocation().getPlane();

        FileWriter fw = new FileWriter(XoBotFolder + "/Tile.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/Tile.txt");
        BufferedWriter bw = new BufferedWriter(fw);
       //    public static Tile tile = new Tile(1, 1, 0);
        bw.write("public static Tile tileName = new Tile(" + X + ", " + Y + ", " + Plane + ");");
        bw.newLine();
        bw.close();

    }

    public static void deleteTileFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/Tile.txt");
        Files.delete(fileToDeletePath);
    }

    public void writeArea() throws IOException {

        FileWriter fw = new FileWriter(XoBotFolder + "/Area.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/Area.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("public static Area areaName = new Area("+ areaStartX + ", " + areaStartY + ", " + areaEndX + ", " + areaEndY + ");");
        bw.newLine();
        bw.close();

    }

    public static void deleteAreaFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/Area.txt");
        Files.delete(fileToDeletePath);
    }

    public void writeGameObjects() throws IOException {
        FileWriter fw = new FileWriter(XoBotFolder + "/GameObjects.txt", true);
        System.out.println("Writing in " + XoBotFolder + "/GameObjects.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (GameObject object : GameObjects.getAll()){
            if (object != null && object.getDistance() <= drawDistanceGameObjects.getValue()){
                bw.write("public static int " + object.object.getClass().getTypeName().toUpperCase().replace(" ", "_") + " = " + object.getId() + ";");
                bw.newLine();
            }
        }

        bw.close();

    }

    public static void deleteGameObjectsFile() throws IOException {
        Path fileToDeletePath = Paths.get(XoBotFolder + "/GameObjects.txt");
        Files.delete(fileToDeletePath);
    }
}
