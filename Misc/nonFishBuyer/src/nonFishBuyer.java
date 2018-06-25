import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JDialog;

import nonFishBuyer.Data.Data;
import nonFishBuyer.Methodes.Banking;
import nonFishBuyer.Methodes.Buy;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.util.Timer;

@Manifest(authors = { "xBear" }, name = "nonFishBuyer", version = 1.0, description = "Buys chosen item from the Fishing shop in the Fishing Guild.")

public final class nonFishBuyer extends ActiveScript implements PaintListener, MessageListener {


    String status = "";
    String buyingItem = "";
    boolean outOfCoins = false;
    private Timer t;

    private javax.swing.JButton start;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField itemID;

    @Override
    public void MessageRecieved(String message, int Level, String arg2) {
        if (message.contains("You don't have enough coins.") && Level == 0){
            outOfCoins = true;
        }
    }

    public boolean onStart() {
        this.t = new Timer(System.currentTimeMillis());
        JDialog x = new JDialog();

        combo = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();
        itemID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        x.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        x.setTitle("nonFishBuyer");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                "Raw shrimps - 317", "Raw anchovies - 321", "Raw sardine - 327",
                "Raw salmon - 331", "Raw trout - 335", "Raw cod - 341",
                "Raw herring - 345", "Raw pike - 349", "Raw mackerel - 353",
                "Raw tuna - 359", "Raw bass - 363", "Raw swordfish - 371", "Raw lobster - 377"
        }));

        start.setText("Start");
        jLabel1.setText("Custom ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(x.getContentPane());
        x.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(itemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                Data.ITEM_ID_TO_BUY = Integer.valueOf(combo.getSelectedItem().toString().split(" - ")[1]);
                System.out.println(Data.ITEM_ID_TO_BUY);
                x.dispose();
            }

        });

        x.pack();
        x.setVisible(true);

        while(x.isVisible()) {
            Time.sleep(1000);
        }

        if (itemID.getText() == null || itemID.getText().isEmpty()) {
            System.out.println("chose item through combo list");
            buyingItem = combo.getSelectedItem().toString().split(" - ")[0];
            return Data.ITEM_ID_TO_BUY != 0;
        } else {
            System.out.println("Manually chose item ID " + itemID);
            Data.ITEM_ID_TO_BUY = Integer.valueOf(itemID.getText());
            buyingItem = "Buying item with ID - " + Data.ITEM_ID_TO_BUY;
            return Data.ITEM_ID_TO_BUY != 0;
        }

    }



    @Override
    public void repaint(Graphics g1) {
        // TODO Auto-generated method stub
        int boughtPerHour = (int) (Data.ITEMS_BOUGHT * 3600000.0D / this.t.getElapsed());

        g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
        g1.drawString("Items bought " + NumberFormat.getNumberInstance(Locale.US).format(Data.ITEMS_BOUGHT), 50, 100);
        g1.drawString("Items(hr): " + NumberFormat.getNumberInstance(Locale.US).format(boughtPerHour), 50, 115);
        g1.drawString("Status - " + status, 50, 130);

    }

    @Override
    public int loop() {

        status = "At shop.";
        if (!Inventory.isFull()) {
            if (Buy.canBuy()) {
                status = "Buying " + buyingItem;
                Buy.doBuy();
                if (outOfCoins == true){
                    return -1;
                }
                Data.ITEMS_BOUGHT += 28;
            }
        }

        if (Inventory.isFull()) {
            if (Banking.canBank()) {
                status = "Banking.";
                Banking.doBank();
            }
        }
        return 200;
    }

}
