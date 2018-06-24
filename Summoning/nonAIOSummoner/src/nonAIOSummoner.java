import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JDialog;

import nonAIOSummoner.Data.Data;
import nonAIOSummoner.Functions.Banking;
import nonAIOSummoner.Functions.Buy;
import nonAIOSummoner.Functions.Summoning;
import nonAIOSummoner.Functions.Teleport;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.tabs.Inventory;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;

@Manifest(authors = { "xBear" }, name = "nonAIOSummoner", version = 1.0, description = "Does summoning, chose what to do.")

public final class nonAIOSummoner extends ActiveScript implements PaintListener, MessageListener {

    int startXP = 0;
    Timer timer;
    String toSummon;
    private javax.swing.JButton start;
    private javax.swing.JComboBox<String> combo;

    String[] Summon = {"Spirit wolf", "Dreadfowl", "Thorny snail", "Granite crab", "Honey bagder", "Beaver", "Macaw",
            "Spirit cockatrice", "Spirit pengatrice", "Spirit coraxatrice", "Spirit vulatrice", "Iron minotaur",
            "Pyrelord", "Magpie", "Bloated bleech", "Spirit terrobird", "Ibis", "Spirit graahk", "Spirit kyatt",
            "Spirit larupia", "Stranger plant", "Barker toad", "War tortoise", "Fruit bat", "Granite lobster",
            "Ice titan", "Hydra", "Geyser titan", "Wolpertinger"

    };
    @Override
    public boolean onStart(){
        timer = new Timer(System.currentTimeMillis());
        startXP = Skills.getCurrentExp(Skills.SUMMONING);
        JDialog x = new JDialog();

        combo = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();

        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonAIOSummoner");
        x.setAlwaysOnTop(true);

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Spirit wolf", "Dreadfowl", "Spirit spider", "Thorny snail", "Granite crab", "Honey bagder", "Beaver", "Macaw",
                "Spirit cockatrice", "Spirit pengatrice", "Spirit coraxatrice", "Spirit vulatrice", "Iron minotaur",
                "Magpie", "Bloated bleech", "Spirit terrobird", "Ibis", "Spirit graahk", "Spirit kyatt",
                "Spirit larupia", "Stranger plant", "Barker toad", "War tortoise", "Fruit bat", "Granite lobster",
                "Ice titan", "Hydra", "Geyser titan", "Wolpertinger" }));

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
                toSummon = (String)combo.getSelectedItem();
                switch (toSummon){
                    case "Spirit wolf":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.WOLF_BONES;
                        Data.REQUIRED_PACKET = Data.WOLF_PACKET;
                        //Data.REQUIRED_POUCH_TO_BANK = Data.POUCH_WOLF;
                        break;

                    case "Dreadfowl":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.RAW_CHICKEN;
                        Data.REQUIRED_PACKET = Data.DREADFOWL_PACKET;
                        break;

                    case "Spirit spider":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.SPIDER_CARCASS;
                        Data.REQUIRED_PACKET = Data.SPIRIT_SPIDER_PACKET;
                        break;


                    case "Thorny snail":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.THIN_SNAIL;
                        Data.REQUIRED_PACKET = Data.THORNY_SNAIL_PACKET;
                        break;

                    case "Granite crab":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.IRON_ORE;
                        Data.REQUIRED_PACKET = Data.GRANITE_CRAB_PACKET;
                        break;

                    case "Honey bagder":
                        Data.REQUIRED_CHARM = Data.CRIMSON_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.HONEYCOMB;
                        Data.REQUIRED_PACKET = Data.HONEY_BADGER_PACKET;
                        break;

                    case "Beaver":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.WILLOW_LOGS;
                        Data.REQUIRED_PACKET = Data.BEAVER_PACKET;
                        break;

                    case "Macaw":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.CLEAN_GUAM;
                        Data.REQUIRED_PACKET = Data.MACAW_PACKET;
                        break;

                    case "Spirit cockatrice":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.COCKATRICE_EGG;
                        Data.REQUIRED_PACKET = Data.SPIRIT_COCKATRICE_PACKET;
                        break;

                    case "Spirit pengatrice":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.PENGATRICE_EGG;
                        Data.REQUIRED_PACKET = Data.SPIRIT_PENGATRICE_PACKET;
                        break;

                    case "Spirit coraxatrice":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.CORAXATRICE_EGG;
                        Data.REQUIRED_PACKET = Data.SPIRIT_CORAXATRICE_PACKET;
                        break;

                    case "Spirit vulatrice":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.VULATRICE_EGG;
                        Data.REQUIRED_PACKET = Data.SPIRIT_VULATRICE_PACKET;
                        break;

                    case "Iron minotaur":
                        Data.REQUIRED_CHARM = Data.BLUE_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.IRON_BAR;
                        Data.REQUIRED_PACKET = Data.IRON_MINOTAUR_PACKET;
                        break;

                    case "Magpie":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.GOLD_RING;
                        Data.REQUIRED_PACKET = Data.MAGPIE_PACKET;
                        break;

                    case "Bloated bleech":
                        Data.REQUIRED_CHARM = Data.CRIMSON_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.RAW_BEEF;
                        Data.REQUIRED_PACKET = Data.BLOATED_BLEECH_PACKET;
                        break;

                    case "Spirit terrobird":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.RAW_BIRD_MEAT;
                        Data.REQUIRED_PACKET = Data.SPIRIT_TERRORBIRD_PACKET;
                        break;

                    case "Ibis":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.HARPOON;
                        Data.REQUIRED_PACKET = Data.IBIS_PACKET;
                        break;

                    case "Spirit graahk":
                        Data.REQUIRED_CHARM = Data.BLUE_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.GRAAHK_FUR;
                        Data.REQUIRED_PACKET = Data.SPIRIT_GRAAHK_PACKET;
                        break;

                    case "Spirit kyatt":
                        Data.REQUIRED_CHARM = Data.BLUE_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.KYATT_FUR;
                        Data.REQUIRED_PACKET = Data.SPIRIT_KYATT_PACKET;
                        break;

                    case "Spirit larupia":
                        Data.REQUIRED_CHARM = Data.BLUE_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.GRAAHK_FUR; //Needs Graahk fur instead of Larupia fur, since SP is bad.
                        Data.REQUIRED_PACKET = Data.SPIRIT_LARUPIA_PACKET;
                        break;

                    case "Stranger plant":
                        Data.REQUIRED_CHARM = Data.CRIMSON_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.BAGGED_PLANT_1;
                        Data.REQUIRED_PACKET = Data.STRANGER_PLANT_POUCH_PACKET;
                        break;

                    case "Barker toad":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.SWAMP_TOAD;
                        Data.REQUIRED_PACKET = Data.BARKER_TOAD_PACKET;
                        break;

                    case "War tortoise":
                        Data.REQUIRED_CHARM = Data.GOLD_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.TORTOISE_SHELL;
                        Data.REQUIRED_PACKET = Data.WAR_TORTOISE_PACKET;
                        break;

                    case "Fruit bat":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.BANANA;
                        Data.REQUIRED_PACKET = Data.FRUIT_BAT_PACKET;
                        break;

                    case "Granite lobster":
                        Data.REQUIRED_CHARM = Data.CRIMSON_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.GRANITE_500G;
                        Data.REQUIRED_PACKET = Data.GRANITE_LOBSTER_PACKET;
                        break;

                    case "Ice titan":
                        Data.REQUIRED_CHARM = Data.BLUE_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.WATER_TALISMAN;
                        Data.REQUIRED_PACKET = Data.ICE_TITAN_PACKET;
                        break;

                    case "Hydra":
                        Data.REQUIRED_CHARM = Data.GREEN_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.WATER_ORB;
                        Data.REQUIRED_PACKET = Data.HYDRA_PACKET;
                        break;

                    case "Geyser titan":
                        Data.REQUIRED_CHARM = Data.BLUE_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.WATER_TALISMAN;
                        Data.REQUIRED_PACKET = Data.GEYSER_TITAN_PACKET;
                        break;

                    case "Wolpertinger":
                        Data.REQUIRED_CHARM = Data.CRIMSON_CHARM_ID;
                        Data.REQUIRED_INGREDIENT = Data.RAW_RABBIT;
                        Data.REQUIRED_PACKET = Data.GEYSER_TITAN_PACKET;
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

        return Data.REQUIRED_CHARM != 0 && Data.REQUIRED_INGREDIENT != 0 && Data.REQUIRED_PACKET != 0;
    }


    @Override
    public void MessageRecieved(String msg, int i, String s1) {

    }

    @Override
    public void repaint(Graphics g1) {
        int xpGained = Skills.getCurrentExp(Skills.SUMMONING) - this.startXP;
        int xpGainedhour = (int) (xpGained * 3600000.0D / timer.getElapsed());
        int currentLevel = Skills.getCurrentLevel(Skills.SUMMONING);
        g1.drawString("Time running " + this.timer.toElapsedString(), 50, 85);
        g1.drawString("Exp gained : " + NumberFormat.getNumberInstance(Locale.US).format(xpGained), 50, 100);
        g1.drawString("Exp gained (hr): " + NumberFormat.getNumberInstance(Locale.US).format(xpGainedhour), 50, 115);
        g1.drawString("Current level : " + currentLevel, 50, 130);
        g1.drawString("Status - " + Data.status, 50, 145);
    }



    @Override
    public int loop() {
        //System.out.println(startXP);
        //System.out.println("Charm using " + Data.REQUIRED_CHARM + " Ingredient using " + Data.REQUIRED_INGREDIENT + " packet using " + Data.REQUIRED_PACKET);
        //We have the required charm, ingredient, shards, pouches and are at the Summoning area.
        if (Inventory.Contains(Data.REQUIRED_CHARM) && Inventory.Contains(Data.REQUIRED_INGREDIENT) && Summoning.inSummoning()){
            System.out.println("Got everything!");
            if (Summoning.canSummon()){
                System.out.println("Can summon");
                Summoning.doSummon();
                Teleport.teleportHome();
            }
            //We have the required charm and we are the summoning, but we do NOT have the required item, need to buy.
        } else if (Inventory.Contains(Data.REQUIRED_CHARM)){
            System.out.println("Missing the required item.");
            if (Buy.canBuy() && !Inventory.isFull() && Inventory.Contains(Data.REQUIRED_CHARM)){
                Buy.doBuy();
            }
        }

        if (Banking.atHome()){
            System.out.println("At home and got items to bank.");
            if (Banking.canBank()){
                System.out.println("can Bank");
                Banking.doBank();
                Teleport.teleportSummoning();
            }
        } else if (!Inventory.Contains(Data.REQUIRED_CHARM)){
            Teleport.teleportHome();
            return -1;
        }

        return 1000;
    }
}