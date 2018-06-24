package nonAIOSummoner.Utils;

import xobot.script.util.Time;

public class Methodes {
    public static boolean conditionalSleep(SleepCondition conn, int timeout) {
        long start = System.currentTimeMillis();
        while (!conn.isValid()) {
            if (start + timeout < System.currentTimeMillis()) {
                return false;
            }
            Time.sleep(50);
        }
        return true;
    }
}
