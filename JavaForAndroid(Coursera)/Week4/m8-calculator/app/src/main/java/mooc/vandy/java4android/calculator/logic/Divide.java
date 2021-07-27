package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Divide operation.
 */
public class Divide {
    // TODO - add your solution here.
    private final int a1;
    private final int a2;

    // Parameterize Constructor
    Divide(int arg1,int arg2){
        this.a1=arg1;
        this.a2=arg2;
    }

    public String division(){
        if(a2==0)
            return "WARNING: division by 0";
        else
            return String.valueOf(a1/a2)+" R:"+String.valueOf(a1%a2);
    }

}
