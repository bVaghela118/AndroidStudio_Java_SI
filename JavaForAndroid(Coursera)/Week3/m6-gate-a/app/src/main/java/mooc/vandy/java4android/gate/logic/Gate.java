package mooc.vandy.java4android.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {
    // TODO -- Fill in your code here
    public static final int OUT = -1;
    public static final int IN = 1;
    public static final int CLOSED = 0;
    private int mSwing;

    public Gate(){
        close();
    }

    public int getSwingDirection()
    {
        return mSwing;
    }

    public boolean setSwing(int swing)
    {
        if (swing == IN) {
            mSwing = swing;
            return true;
        } else if (swing == OUT) {
            mSwing = swing;
            return true;
        }else
            return false;
    }

    public boolean open(int direct)
    {
        if(direct == OUT || direct == IN)
        {
            return setSwing(direct);
        }
        else
        {
            return false;
        }
    }
    public void close()
    {
        mSwing = CLOSED;
    }
    public int thru(int cnt)
    {
        if(mSwing == 1 ) {
            return cnt;
        }
        else if(mSwing == -1) {
            return -cnt;
        }
        else if(mSwing == 0) {
            cnt=0;
        }
        return cnt;
    }

    public String toString() {
        if (mSwing == IN) {
            return "This gate is open and swings to enter the pen only";
        } else if (mSwing == OUT) {
            return "This gate is open and swings to exit the pen only";
        } else if (mSwing == CLOSED) {
            return "This gate is closed";
        } else{
            return "This gate has an invalid swing direction";
        }
    }
    
}
