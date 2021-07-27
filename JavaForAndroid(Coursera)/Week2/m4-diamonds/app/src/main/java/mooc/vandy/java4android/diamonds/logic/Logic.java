package mooc.vandy.java4android.diamonds.logic;

import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    final private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        // TODO -- add your code here
        // Print Top line of Box
        if(size>0 && size<=8) {
            mOut.print("+");
            printString("-", size * 2);
            mOut.print("+\n");

            // Print Top half of Diamond
            for (int i = 1; i <= size - 1; i++) {
                printLine(size, i, '/', '\\');
            }

            // Middle Of Diamond
            printLine(size, size, '<', '>');

            // Bottom half of Diamond
            for (int i = size - 1; i >= 1; i--) {
                //here bottom of Diamond have reverse loop so we interchange the character
                printLine(size, i, '\\', '/');
            }

            // Print Bottom line Of Box
            mOut.print("+");
            printString("-", size * 2);
            mOut.print("+\n");
        }
    }
    // TODO -- add any helper methods here
    public  void printString(String str, int n) {
        for (int i = 0; i < n; i++) {
            mOut.print(str);
        }
    }

    public void printLine(int num, int line, char lChar, char rChar) {
        // Print left length of box
        mOut.print("|");

        printString(" ", num - line);
        mOut.print(lChar);
        if (line % 2 == 0) {
            printString("-", 2 * (line - 1));
        } else {
            printString("=", 2 * (line - 1));
        }
        mOut.print(rChar);
        printString(" ", num - line);
        // Print right length of Box
        mOut.println("|");
    }

    
}
