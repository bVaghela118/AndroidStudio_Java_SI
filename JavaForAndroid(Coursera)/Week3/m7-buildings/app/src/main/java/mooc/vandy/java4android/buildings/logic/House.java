package mooc.vandy.java4android.buildings.logic;

import java.util.Objects;

/**
 * This is the House class file that extends Building.
 */
public class House
       extends Building {

    // TODO - Put your code here.
    private String mOwner;
    private boolean mPool;

    public House(int length,int width,int lotLength,int lotWidth){
        super(length,width,lotLength,lotWidth);
        mOwner = null;
        mPool = false;
    }

    public House(int length,int width,int lotLength,int lotWidth,String owner){
        super(length,width,lotLength,lotWidth);
        mOwner = owner;
    }

    public House(int length,int width,int lotLength,int lotWidth,String owner,boolean pool){
        super(length,width,lotLength,lotWidth);
        mOwner = owner;
        mPool = pool;
    }

    public String getOwner() {
        return mOwner;
    }

    public boolean hasPool(){
        return mPool;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public void setPool(boolean mPool) {
        this.mPool = mPool;
    }

    @Override
    public String toString() {
        String str;
        if(mOwner==null)
            return "Owner: n/a";
        else {
            str = "Owner: "+getOwner()+";";
            if (mPool) {
                str.concat(" has a pool;");
            } else if (calcLotArea() >= calcBuildingArea()) {
                str = str.concat(" has a big open space");
            }
            return str;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return mPool == house.mPool;
    }


}
