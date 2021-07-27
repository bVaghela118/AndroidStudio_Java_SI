package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Add operation.
 */
public class Add {
    // TODO - add your solution here.
    private final int a1;
    private final int a2;

    // Parameterize Constructor
    Add(int arg1,int arg2){
        this.a1=arg1;
        this.a2=arg2;
    }

    public String addition(){
        return String.valueOf(a1+a2);
    }
}
