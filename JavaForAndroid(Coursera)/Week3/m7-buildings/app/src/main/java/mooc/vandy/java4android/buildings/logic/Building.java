package mooc.vandy.java4android.buildings.logic;

/**
 * This is the Building class file.
 */
public class Building {

    // TODO - Put your code here.
    private int mLength;
    private int mWidth;
    private int mLotLength;
    private int mLotWidth;

    public Building(int length,int width,int lotLength,int lotWidth)
    {
        this.mLength = length;
        this.mWidth = width;
        this.mLotLength = lotLength;
        this.mLotWidth = lotWidth;
    }
    // Accessor methods
    public int getLength(){
        return  mLength;
    }
    public int getWidth(){
        return mWidth;
    }
    public int getLotLength(){
        return mLotLength;
    }
    public int getLotWidth(){
        return mLotWidth;
    }

    //Mutator methods
    public void setLength(int length){
        this.mLength = length;
    }
    public void setWidth(int width){
        this.mWidth = width;
    }
    public void setLotLength(int lotLength){
        this.mLotLength = lotLength;
    }
    public void setLotWidth(int lotWidth){
        this.mLotWidth = lotWidth;
    }

    public int calcBuildingArea(){
        return mLength * mWidth;
    }

    public int calcLotArea(){
        return mLotLength * mLotWidth;
    }

    public String toString(){
        return "a "+mLength+"x"+mWidth+" building";
    }
}
