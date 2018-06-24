import xobot.bot.Context;
import xobot.script.methods.GameObjects;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.*;
import xobot.script.methods.input.KeyBoard;
import xobot.script.util.Time;
import xobot.script.wrappers.Path;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.GameObject;

import java.awt.*;

@Manifest(authors = { "xBear" }, name = "monster", version = 1.0, description = "Cuts gems into Bolt Tips.")

public final class monster extends ActiveScript implements PaintListener, MessageListener {

    //data Data = new data();

    public Tile entrance = new Tile(3170, 3170);

    public Tile[] dungeonEntrancePath = {
            new Tile(3088, 3490),
            new Tile(3090, 3487),
            new Tile(3095, 3486),
            new Tile(3098, 3480),
            new Tile(3098, 3469),
            new Tile(3092, 3465),
            new Tile(3088, 3458),
            new Tile(3089, 3450), //////
            new Tile(3088, 3439),
            new Tile(3088, 3429)};








    public void walkToMonster() {
        Path p = new Path(dungeonEntrancePath);
        System.out.println("Walking to tile destination " + Context.client.getBaseX() + " " + Context.client.getBaseY());
        while (Calculations.distanceTo(entrance) > 5 && !Players.getMyPlayer().isMoving()) {
            Tile a = p.getNextTile();
            Walking.walkTo(a);
            if (Players.getMyPlayer().isMoving()) {
                System.out.println("Walking to tile " + a.getLocation().getX() + " " + a.getLocation().getY());
            }
            Time.sleep(3500);
        }
    }





    public void getTile(){
        int x = Players.getMyPlayer().getLocation().getX();
        int y = Players.getMyPlayer().getLocation().getY();
        System.out.println("new Tile(" + x + ", " + y + "),");
    }



    @Override
    public void MessageRecieved(int i, String s, String s1, String s2) {

    }

    @Override
    public void repaint(Graphics graphics) {

    }

    @Override
    public int loop() {
        walkToMonster();
        return 3000;
    }
}
