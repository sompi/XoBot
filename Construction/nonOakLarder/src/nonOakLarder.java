import java.awt.*;
import java.awt.event.KeyEvent;

import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.*;
import xobot.script.methods.input.KeyBoard;
import xobot.script.methods.tabs.Equipment;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.util.Timer;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

@Manifest(authors = { "xBear" }, name = "nonOakLarder", version = 1.0, description = "Cuts gems into Bolt Tips.")

public final class nonOakLarder extends ActiveScript implements PaintListener, MessageListener {


    private Timer t;
    int oakPlankID = 8778;
    int larderID = 15403;
    int oakLarderID = 13566;

    @Override
    public void MessageRecieved(String message, int arg1, String arg2) {

    }

    public boolean onStart() {
        this.t = new Timer(System.currentTimeMillis());
        return true;

    }

    public void createOakLarder(){

        GameObject larder = GameObjects.getNearest(larderID) ;
        if (larder != null){
            System.out.println("Making Oak Larder");
            Packets.sendAction(1062, larder.uid, larder.getX(), larder.getY(), 15403, 0);
            Time.sleep(1000);
            Packets.sendAction(632, 8234, 0, 39601, 15403, 0); //Packet for Oak Larder
        }
    }

    public void destroyOakLarder(){

        GameObject oakLarder = GameObjects.getNearest(oakLarderID);
        if (oakLarder != null){
            System.out.println("Destoying oak larder");
            Packets.sendAction(1062, oakLarder.uid, oakLarder.getX(), oakLarder.getY(), 13566, 0);
            Time.sleep(200);
            if (Time.sleep(()-> Widgets.getBackDialogId() == 2459, 5000)){
                System.out.println("Remove interface is open!");
                KeyBoard.pressKey(KeyEvent.VK_1);
                Time.sleep(200);
            }

        }
    }

    public void callServent(){
        Packets.sendAction(315, 0, 0, 46013, 0, 0);
    }

    public int oakPlankBankAmount(){
        if (Bank.getItem(oakPlankID) != null){
            return Bank.getItem(oakPlankID).getStack();
        } else {

            return 0;
        }
    }


    public void getPlanks(){
        NPC servent = NPCs.getNearest(4243);
        if (servent != null){
            System.out.println("Talkign to sevent for planks...");
            //Packets.sendAction(20, 149, servent.getLocation().getX(), servent.getLocation().getY(), 15405, 0);
            servent.interact(0);
            Time.sleep(1000);
            System.out.println("Interacting with servent.");
            servent.interact(1);
            //Packets.sendAction(225, 149, servent.getLocation().getX(), servent.getLocation().getY(), 15314, 0);
            Time.sleep(450, 550);
            if (Time.sleep(()-> Widgets.getBackDialogId() == 2492, 5000)){
                System.out.println("Fetch from bank is open!");
                KeyBoard.pressKey(KeyEvent.VK_2);
                Time.sleep(1000);
                if (Widgets.getBackDialogId() == 2492) {
                    KeyBoard.typeWord("26", true);
                }
                Time.sleep(1000);
                callServent();
            }
        } else {
            System.out.println("Sending packet to call sevent.");
            Packets.sendAction(315, 11, 211, 46013, 15405, 0); //Cal servent
            Time.sleep(2000);
        }
    }

    public void getEquipment(){

        for (Item item: Equipment.getEquipment()
             ) {
            System.out.println(item.getDefinition().getName() + " - " + item.getID());

        }


        /*Item helmet = Equipment.getItem(Equipment.HELMET);
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
        }*/
    }

    Area test = new Area(3084, 3491, 3089, 3496);






        @Override
        public void repaint(Graphics g1) {
            g1.drawString("Time running " + this.t.toElapsedString(), 50, 85);
            g1.drawString("Planks left " + oakPlankBankAmount(), 50, 100);

            for (NPC npc : NPCs.getAll()){
                if (npc != null && npc.getDistance() < 5){
                    npc.getLocation().draw(g1, Color.CYAN);
                }
            }

            for (Tile tile : test.getTileArray()){
                tile.draw(g1, Color.RED);
            }



        }

        @Override
        public int loop() {
        /*NPC servent = NPCs.getNearest(4243);


        if (Inventory.getCount(oakPlankID) >= 8) {
            createOakLarder();
        } else {
            //Get planks
            if (servent != null){
                System.out.println("Getting planks");
                getPlanks();
            }
        }
                destroyOakLarder();
*/
        //getEquipment();
            return 1000;
        }

    }
