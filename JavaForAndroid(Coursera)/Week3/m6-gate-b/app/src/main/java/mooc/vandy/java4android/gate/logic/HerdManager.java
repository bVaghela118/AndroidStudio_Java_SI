package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to manage a herd of snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class HerdManager {
    /**
     * Reference to the output.
     */
    private OutputInterface mOut;

    /**
     * The input Gate object.
     */
    private Gate mWestGate;

    /**
     * The output Gate object.
     */
    private Gate mEastGate;

    /**
     * Maximum number of iterations to run the simulation.
     */
    private static final int MAX_ITERATIONS = 10;

    /**
     * Constructor initializes the fields.
     */
    public HerdManager(OutputInterface out,
                       Gate westGate,
                       Gate eastGate) {
        mOut = out;

        mWestGate = westGate;
        mWestGate.open(Gate.IN);

        mEastGate = eastGate;
        mEastGate.open(Gate.OUT);
    }

    // TODO -- Fill your code in here

    public static final int HERD = 24;

    public void simulateHerd(Random rand){
        int pen = HERD;
        int pasture = 0, randNum;

        mOut.println("There are currently "+pen+" snails in the pen and "+pasture+" snails in the pasture");
        for(int i=0;i<MAX_ITERATIONS;i++)
        {
            int cnt=0;
            boolean flag;
            if(pen==0){
                randNum = rand.nextInt(pasture)+1;
                cnt =mWestGate.thru(randNum);
                pen+= cnt;
            }
            else if(pasture==0)
            {
                randNum = rand.nextInt(pen)+1;
                cnt = mEastGate.thru(randNum);
                pen+= cnt;
            }
            else {
                flag = rand.nextBoolean();
                if(flag){
                    randNum = rand.nextInt(pen)+1;
                    cnt = mEastGate.thru(randNum);
                    pen+= cnt;
                }
                else{
                    randNum =rand.nextInt(pasture)+1;
                    cnt = mWestGate.thru(randNum);
                    pen+=cnt;
                }
            }
            pasture = HERD - pen;
            mOut.println("There are currently " + pen + " snails in the pen and " + pasture + " snails in the pasture");
        }
    }
}
