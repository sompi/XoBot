import xobot.script.methods.*;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.NPC;

public class ImpMethods {

    private static Tile[] pathToImps = {new Tile(2542, 2845), new Tile(2528,2838), new Tile(2514, 2836)};


    private static Area shopArea = new Area(new Tile (2517,2831), new Tile(2567,2850));

    public static boolean impNear(){
        NPC n = NPCs.getNearest(dridiaAutoHunter.selectedImp.getId());
        if(n != null) {
            Time.sleep(200);
            return true;
        }else{
            Time.sleep(200);
            return false;
        }
    }

    public static void catchImp(){
        NPC n = NPCs.getNearest(dridiaAutoHunter.selectedImp.getId());
        if(n != null){
            int startCount = Inventory.getCount(11260);
            n.interact(0);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() != -1;
                }
            }, 10000);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() == -1;
                }
            }, 10000);
            if(startCount > Inventory.getCount(11260)){
                switchSpot();
                return;
            }
            Time.sleep(4900);//Change in order to increase waiting time to start new catch.
        }
    }

    public static void switchSpot(){
        Tile t = dridiaAutoHunter.selectedImp.getNextTile();
        Walking.walkTo(t);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Calculations.distanceTo(t) <= 4;
            }
        }, 20000);
    }

    public static Tile SpotsReachable(){
        Tile[] tArray = dridiaAutoHunter.selectedImp.getTiles();
        for(Tile t : tArray){
            if(t.isReachable()){
                return t;
            }
        }
        return null;
    }

    public static void walkToImpsFromShop(){
        for(int i = 0; i < pathToImps.length-1; i++){
            if(pathToImps[pathToImps.length-i-1].isReachable()){
                Walking.walkTo(pathToImps[pathToImps.length-i-1]);
                Methods.conditionalSleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return !(Players.getMyPlayer().isMoving());
                    }
                }, 5000);
                return;
            }
        }

    }

    public static void BuySupplies(){
        System.out.println("Buying supplies...");
        NPC huntingExpert = NPCs.getNearest(5112);
        Walking.walkTo(new Tile(2548, 2839));
        Time.sleep(250);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !(Players.getMyPlayer().isMoving());
            }
        },5000);
        Walking.walkTo(huntingExpert.getLocation());
        Time.sleep(250);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !(Players.getMyPlayer().isMoving());
            }
        },5000);
        huntingExpert.interact(0);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Widgets.getOpenInterface() == 3824;
            }
        },5000);
        Packets.sendAction(431, 11260, 2, 3900, 0, 0);
        Time.sleep(200,500);
        Packets.sendAction(431, 11260, 2, 3900, 0, 0);
        Time.sleep(200,500);
        Packets.sendAction(431, 11260, 2, 3900, 0, 0);
        Time.sleep(200,500);
    }

    public static void TeleportToBank(){
        Packets.sendAction(315, 449, 3, 1195, 0, 1);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() != -1;
            }
        }, 2000);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() == -1;
            }
        }, 4000);
    }

    public static void TeleportToShop(){
        System.out.println("Teleport to shop...");
        Packets.sendAction(315, 0, 482, 1540, 0, 0);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Widgets.getBackDialogId() == 2459;
            }
        }, 2000);
        Packets.sendAction(315, 42, 501, 2462, 409, 0);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Widgets.getBackDialogId() == 2492;
            }
        }, 2000);
        Packets.sendAction(315, 261, 487, 2498, 411, 0);
        Time.sleep(800, 1200);
        Packets.sendAction(315, 261, 487, 2498, 411, 0);
        Time.sleep(810, 1300);
        Packets.sendAction(315, 261, 487, 2497, 411, 0);
        Time.sleep(1000, 1200);
        Methods.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() == -1 && Players.getMyPlayer().getLocation() == new Tile(2556, 2845);
            }
        }, 5000);
    }

    public static boolean shouldWalkToStartLocation() {
        if (shopArea.contains(Players.getMyPlayer().getLocation())){
            return true;
        }
        return false;
    }
}
