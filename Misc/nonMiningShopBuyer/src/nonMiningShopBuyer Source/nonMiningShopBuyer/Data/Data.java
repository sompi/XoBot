package nonMiningShopBuyer.Data;

import xobot.script.wrappers.Area;

public class Data {

	public static int ITEM_ID_TO_BUY = 0;
	
	public static int BANK_ID = 26972;
	public static int NURMOF_ID = 594; //NPC to buy from
	public static int IRON_ORE_ID = 440;
	public static int COINS_ID = 995;
	public static String STATUS = "";
	
	public static int ORES_BOUGHT = 0;
	public static int ORES_PER_HOUR;
	public static int ORE_PRICE = 19;
	public static int COINS_SPEND= 0;
	public static int BUY_AMOUNT = 0;
	
	public static Area HOME_AREA = new Area(3077, 3485, 3101, 3503);
	public static Area MINING_AREA = new Area(2312, 3832, 2319, 3837);
}
