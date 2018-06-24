
import xobot.script.wrappers.Tile;


/**
 * Created by mgrimberg on 2017-09-15.
 */
public enum Imp {

    BABY(6055, 560, new Tile[]{new Tile(2497, 2842), new Tile(2477, 2845), new Tile(2459, 2849)}, 11238),
    ECLECTIC(6060, 3000, new Tile[]{new Tile(2496, 2890), new Tile(2498, 2874), new Tile(2511, 2865)}, 11248),
    NINJA(6063, 3000, new Tile[]{new Tile(2503, 2908), new Tile(2490, 2901), new Tile(2475, 2927)},11254),
    KINGLY(7903, 3000, new Tile[]{new Tile(2496, 2933), new Tile(2515, 2910)},15517);


    private int id;
    private int xp;
    private Tile[] tileArray;
    private int Count = 0;
    private int jarId = 0;
    private Tile currentTile;

    private int dir = 0;

    Imp(int id, int xp, Tile[] tileArray, int jarId){
        this.id = id;
        this.xp = xp;
        this.tileArray = tileArray;
        this.jarId = jarId;
        this.currentTile = tileArray[0];
    }

    public int getId(){
        return id;

    }

    public int getXp(){
        return xp;
    }

    public Tile[] getTiles(){
        return tileArray;
    }

    public Tile getNextTile(){
        if (Count >= this.tileArray.length -1 ) {
            dir = -1;
        }else if(Count <= 0){
            dir = 0;
        }
        if(dir == 0){
            Count++;
        }else if(dir == -1){
            Count--;
        }

        Tile returnTile = tileArray[Count];
        return returnTile;
    }

    public int getJarId(){
        return jarId;
    }
}
