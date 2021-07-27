package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Multiply operation.
 */
public class Multiply {
    // TODO - add your solution here.
    private final int a1;
    private final int a2;

    // Parameterize Constructor
    Multiply(int arg1,int arg2){
        this.a1=arg1;
        this.a2=arg2;
    }

    public String multiplication(){
        return String.valueOf(a1*a2);
    }
}
