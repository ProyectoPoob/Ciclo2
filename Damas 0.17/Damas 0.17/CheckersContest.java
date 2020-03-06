
/**
 * Write a description of class CheckersContest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CheckersContest
{

    /**
     * Constructor for objects of class CheckersContest
     */
    public CheckersContest()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String[] solve(char player,String[] moves)
    {
        // put your code here
        Solution solvi= new Solution();
        String[] res= solvi.solved(player,moves);
        return res;
    }
    public void simulate(char player,String[] moves,boolean slow)
    {
        Checkers checker = new Checkers(8);
    }
}
