

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CheckersTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckersTest
{
    /**
     * Default constructor for test class CheckersTest
     */
    public CheckersTest()
    {
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void noDeberiaAñadirFichaQueSalgaDeRango()
    {
        Checkers checkers1 = new Checkers(8);
        checkers1.add(true,9,9);
        assertFalse(checkers1.ok());
    }
    @Test
    public void noDejaPonerUnaFichaSobreOtra()
    {
        Checkers checkers1 = new Checkers(8);
        checkers1.add(true,2,3);
        checkers1.add(true,2,3);
        assertFalse(checkers1.ok());
    }


    @Test
    public void lasfichasnosepuedensalirdeltablero()
    {
        Checkers checkers1 = new Checkers(8);
        checkers1.add(true, 2, 3);
        checkers1.select(2, 3);
        checkers1.shift(true, true);
        checkers1.shift(true, true);
    }

    @Test
    public void deberiaguardaryrecuperartablero()
    {
        Checkers checkers1 = new Checkers(8);
        checkers1.add(true, 2, 3);
        checkers1.add(false, 3, 4);
        checkers1.save("gurrero");
        checkers1.remove(2, 3);
        checkers1.remove(3, 4);
        assertEquals("-.-.-.-..-W-.-.--.-w-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", checkers1.recover("gurrero"));
    }

    @Test
    public void elSaltoFunciona()
    {
        Checkers checkers1 = new Checkers(8);
        checkers1.add(false, 2, 3);
        checkers1.swap();
        checkers1.add(true , 4, 7);
        checkers1.select(2, 3);
        checkers1.move("left");
        checkers1.swap();
        checkers1.add(true, 3, 4);
        checkers1.add(false, 4, 5);
        checkers1.swap();
        checkers1.select(3, 4);
        checkers1.jump(false, true);
    }
}




