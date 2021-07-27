package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Subtract operation.
 */
public class Subtract {
    // TODO - add your solution here.
    private final int a1;
    private final int a2;

    // Parameterize Constructor
    Subtract(int arg1,int arg2){
        this.a1=arg1;
        this.a2=arg2;
    }

    public String subtraction(){
        //Convert int to String
        return String.valueOf(a1-a2);
    }
}
