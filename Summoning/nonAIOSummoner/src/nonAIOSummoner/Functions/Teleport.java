package nonAIOSummoner.Functions;

import nonAIOSummoner.Data.Data;
import nonAIOSummoner.Utils.Methodes;
import nonAIOSummoner.Utils.SleepCondition;

import xobot.script.methods.Packets;
import xobot.script.methods.Players;
import xobot.script.util.Time;

public class Teleport {

    public static void teleportSummoning() {
        Data.status = "Teleporting to Summoning";
        Packets.sendAction(315, 0, 0, 29154, 0, 0);
        Methodes.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() != -1;
            }
        }, 3000);
        Methodes.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() == -1;
            }
        }, 5000);
    }

    public static void teleportHome() {
        Data.status = "Teleporting to Home";
        Packets.sendAction(315, 0, 0, 25654, 0, 0);
        Methodes.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() != -1;
            }
        }, 3000);
        Methodes.conditionalSleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getAnimation() == -1;
            }
        }, 5000);
    }
}

