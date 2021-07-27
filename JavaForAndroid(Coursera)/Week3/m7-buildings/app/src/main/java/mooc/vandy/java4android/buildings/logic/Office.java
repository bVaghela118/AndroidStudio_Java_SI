package mooc.vandy.java4android.buildings.logic;

import java.util.Objects;

/**
 * This is the office class file, it is a subclass of Building.
 */
public class Office
       extends Building {

    // TODO - Put your code here.
    private String mBusinessName;
    private int mParkingSPaces;
    private static int sTotalOffices=0;

    public Office(int length,int width,int lotLength,int lotWidth){
        super(length,width,lotLength,lotWidth);
        mBusinessName = null;
        mParkingSPaces = 0;
        sTotalOffices+=1;
    }

    public Office(int length,int width,int lotLength,int lotWidth,String businessName){
        this(length,width,lotLength,lotWidth);
        mBusinessName = businessName;
        mParkingSPaces =0;
        sTotalOffices+=1;
    }

    public Office(int length,int width,int lotLength,int lotWidth,String businessName,int parkingSpaces){
        this(length,width,lotLength,lotWidth);
        mBusinessName = businessName;
        mParkingSPaces = parkingSpaces;
        sTotalOffices+=1;
    }

    public String getBusinessName(){
        return mBusinessName;
    }
    public int getParkingSpaces(){
        return mParkingSPaces;
    }

    public static int getTotalOffices(){
        return sTotalOffices;
    }

    public void setBusinessName(String name){
        this.mBusinessName = name;
    }

    public void setParkingSpaces(int space){
        this.mParkingSPaces = space;
    }

    @Override
    public String toString(){
        if(getBusinessName()==null)
            return "Business: unoccupied "+"(total offices: "+getTotalOffices()+")";
        else{
            if(getParkingSpaces()>0)
                return "Business: "+getBusinessName()+"; has "+getParkingSpaces()+" parking spaces (total offices: "+getTotalOffices()+")";
            else
                return "Business: "+getBusinessName()+" (total offices: "+getTotalOffices()+")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Office) {
            Office otherOffice = (Office) o;
            if (otherOffice.calcBuildingArea() == this.calcBuildingArea() && otherOffice.getParkingSpaces() == this.getParkingSpaces())
                return true;
        }
        return false;
    }

}
