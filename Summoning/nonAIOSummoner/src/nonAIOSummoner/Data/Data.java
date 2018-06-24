package nonAIOSummoner.Data;

import xobot.script.methods.GameObjects;
import xobot.script.methods.NPCs;
import xobot.script.methods.Widgets;
import xobot.script.wrappers.Area;
import xobot.script.wrappers.Widget;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.NPC;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static String status = "";

    public static int GOLD_CHARM_ID = 12158;
    public static int GREEN_CHARM_ID = 12159;
    public static int CRIMSON_CHARM_ID = 12160;
    public static int BLUE_CHARM_ID = 12163;
    public static int SPIRIT_SHARD_ID = 18016;
    public static int POUCH = 12155;
    public static int[] DONT_BANK_ITEMS = { GOLD_CHARM_ID, GREEN_CHARM_ID, CRIMSON_CHARM_ID, BLUE_CHARM_ID, SPIRIT_SHARD_ID, POUCH };
    //Summoning shop NPC ID and Name
    public static int PIKKUPSTIX = 6970;
    public static NPC SUMMONING_SHOP = NPCs.getNearest(PIKKUPSTIX);

    /* Banking ID (Banking Booth) at Edgeville Banking */
    public static int BANK_ID = 26972;
    public static GameObject BANK = GameObjects.getNearest(BANK_ID);

    /* Areas */
    public static Area SUMMONING_AREA = new Area(2204, 5341, 2210, 5348);
    public static Area HOME_AREA = new Area(3077, 3485, 3100, 3506);

    /* Summoning interface and Widget*/
    public static int SUMMONING_INTERFACE_ID = 39700;
    public static Widget SUMMONING_INTERFACE = Widgets.get(SUMMONING_INTERFACE_ID);

    /* Obelisk ID and Game Object */
    public static int OBELISK_ID = 28716;
    public static Object OBELISK = GameObjects.getNearest(OBELISK_ID);

    /* Ingredients from shop */
    public static int WOLF_BONES = 2859;
    public static int RAW_CHICKEN = 2138;
    public static int SPIDER_CARCASS = 6291;
    public static int THIN_SNAIL = 3363;
    public static int HONEYCOMB = 12156;
    public static int COCKATRICE_EGG = 12109;
    public static int PENGATRICE_EGG = 12117;
    public static int CORAXATRICE_EGG = 12119;
    public static int VULATRICE_EGG = 12121;
    public static int GOLD_RING = 1635;
    public static int RAW_BIRD_MEAT = 9978;
    public static int JUG_OF_WATER = 1937;
    public static int LARUPIA_FUR = 10095;
    public static int KYATT_FUR = 10103;
    public static int GRAAHK_FUR = 10099;
    public static int SWAMP_TOAD = 2150;
    public static int TORTOISE_SHELL = 7939;
    public static int RAW_RABBIT = 3226;
    public static int IRON_DAGGER = 1203;
    public static int GRANITE_500G = 6979;
    public static int IRON_BAR = 2351;
    public static int IRON_ORE = 440;
    public static int WILLOW_LOGS = 1519;
    public static int CLEAN_GUAM = 249;
    public static int HARPOON = 311;
    public static int BANANA = 1963;
    public static int WATER_ORB = 571;
    public static int RAW_BEEF = 2132;
    public static int BAGGED_PLANT_1 = 8431;
    public static int WATER_TALISMAN = 1444;


    /* Packets for each Summoning Monster where the items are bought in the Summoning Shop*/
    //First row
    public static int WOLF_PACKET = 39711;
    public static int DREADFOWL_PACKET = 39714;
    public static int SPIRIT_SPIDER_PACKET = 39717;
    public static int THORNY_SNAIL_PACKET = 39720;
    public static int GRANITE_CRAB_PACKET = 39723;

    //Third row
    public static int HONEY_BADGER_PACKET = 39753;
    public static int BEAVER_PACKET = 39756;
    public static int MACAW_PACKET = 39777;

    //Fourth row
    public static int SPIRIT_COCKATRICE_PACKET = 39783;
    public static int SPIRIT_PENGATRICE_PACKET = 39795;
    public static int SPIRIT_CORAXATRICE_PACKET = 39798;
    public static int SPIRIT_VULATRICE_PACKET = 39801;
    public static int IRON_MINOTAUR_PACKET = 39804;

    //Fifth row
    public static int MAGPIE_PACKET = 39810;
    public static int BLOATED_BLEECH_PACKET = 39813;
    public static int SPIRIT_TERRORBIRD_PACKET = 39816;
    public static int IBIS_PACKET = 39825;

    //Sixth row
    public static int SPIRIT_GRAAHK_PACKET = 39831;
    public static int SPIRIT_KYATT_PACKET = 39834;
    public static int SPIRIT_LARUPIA_PACKET = 39837;
    public static int STRANGER_PLANT_POUCH_PACKET = 39852;

    //Seventh row
    public static int BARKER_TOAD_PACKET = 39858;
    public static int WAR_TORTOISE_PACKET = 39861;
    public static int FRUIT_BAT_PACKET = 39864;

    //Eigth row
    public static int GRANITE_LOBSTER_PACKET = 39882;
    public static int ICE_TITAN_PACKET = 39885;
    public static int HYDRA_PACKET = 39888;

    //Ninth row
    public static int GEYSER_TITAN_PACKET = 39927;
    public static int WOLPERTINGER_PACKET = 39930;

    /* Pouch ID's of the supported summons */

    public static int POUCH_WOLF = 12047;
    public static int POUCH_DREADFOWL = 12043;

    public static int POUCH_GRANITE_CRAB = 12009;

    public static int POUCH_COCKATRICE = 12095;



    public static int REQUIRED_INGREDIENT = 0;
    public static int REQUIRED_PACKET = 0;
    public static int REQUIRED_CHARM = 0;
    public static int REQUIRED_POUCH_TO_BANK = 0;


}
