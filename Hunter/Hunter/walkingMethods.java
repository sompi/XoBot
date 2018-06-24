/**
 * Created by mgrimberg on 2017-10-03.
 */


import xobot.script.methods.*;
import xobot.script.wrappers.Tile;

public class walkingMethods {

    public static Tile startLocation = new Tile(2511, 2837);

    public static void walkToImp(Imp imp){
        Walking.walkTo(startLocation);
        Tile[] impTile = imp.getTiles();
        for(int i = 0; i < impTile.length; i++) {
            if (impTile[i].isReachable()) {
                Walking.walkTo(impTile[i]);
                final int finalI = i;
                Methods.conditionalSleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Calculations.distanceBetween(impTile[finalI], Players.getMyPlayer().getLocation()) <= 2 || (NPCs.getNearest(imp.getId()) != null) ;
                    }
                }, 10000);
            }
        }
    }

    public static Tile getCloserTile(Tile t){
        return Walking.getClosestTileOnMap(t);
    }

    public static void walkToStartLocation() {
        while(!startLocation.isReachable()){
            Tile walkToCloserTile = walkingMethods.getCloserTile(startLocation);
            Walking.walkTo(walkToCloserTile);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Calculations.distanceBetween(walkToCloserTile, Players.getMyPlayer().getLocation()) <= 3 /** Eller om spelaren inte längre rör sig.*/;
                }
            },25000);
        }
        if(startLocation.isReachable()){
            Walking.walkTo(startLocation);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Calculations.distanceBetween(startLocation, Players.getMyPlayer().getLocation()) <= 2;
                }
            },25000);
        }
    }
}

