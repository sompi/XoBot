import xobot.bot.Context;
import xobot.client.callback.listeners.MessageListener;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.NPCs;
import xobot.script.methods.Players;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Tile;
import xobot.script.wrappers.interactive.NPC;

import java.awt.*;

import nonUtilityGUI.*;


@Manifest(authors = { "xBear" }, name = "nonUtility", version = 1.0, description = "Gets easily some valueable information for you.")

public final class nonUtility extends ActiveScript implements PaintListener, MessageListener {

    public String fileName;








    @Override
    public boolean onStart(){
        GUI.makeFolder();
        Context.antibot = -1;


        return true;
    }


    @Override
    public void MessageRecieved(String msg, int i, String s1) {

    }



    @Override
    public void repaint(Graphics g1) {
        //Only draw NPC's when NPC Tab is selected.
        if (GUI.atNPCTab) {
            for (NPC npc : NPCs.getAll()) {
                if (npc != null && npc.getDistance() <= GUI.distanceToDrawForNPCS) {
                    npc.getLocation().draw(g1, Color.CYAN);
                }
            }
        }

        if (GUI.atAreaTab){
            //Draw Area if Tiles are set
            if (GUI.showArea == true && Players.getMyPlayer().getLocation().getX() != 0 && Players.getMyPlayer().getLocation().getY() != 0){
                for (Tile tile : GUI.areaToDraw.getTileArray()){
                    tile.draw(g1, Color.RED);
              }
            }
        }

        if (GUI.atGameObjectsTab){
            //Draw Game Objects
        }


    }



    @Override
    public int loop() {
        if (GUI.isAlreadyVisible == false){
            GUI x = new GUI();
            System.out.println("GUI");
        }




        return 1000;
    }
}
