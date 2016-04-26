package klassen.matt.dndproject.tests;

import klassen.matt.dndproject.model.mechanics.Die;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for die and rolling of die
 */
public class DieTest {

    private Die d20;
    private Die d12;
    private Die d10;
    private Die d8;
    private Die d6;
    private Die d4;
    private Die d100;
    private Die d3d6;

    @Before
    public void setUp() {
        d20 = new Die("1d20");
        d12 = new Die("1d12");
        d10 = new Die(1, 10);
        d8 = new Die("1d8");
        d6 = new Die(1, 6);
        d4 = new Die("1d4");
        d100 = new Die(1, 100);
        d3d6 = new Die("3d6");
    }

    @Test
    public void testConstructor() {
        assertEquals(1, d20.getNumOfDie());
        assertEquals(20, d20.getSidesOfDie());
        assertEquals(1, d10.getNumOfDie());
        assertEquals(10, d10.getSidesOfDie());
        assertEquals(1, d100.getNumOfDie());
        assertEquals(100, d100.getSidesOfDie());
        assertEquals(3, d3d6.getNumOfDie());
        assertEquals(6, d3d6.getSidesOfDie());
    }

    @Test
    public void testRoll() {

        for (int i = 0; i < 100000; i++) {
            int r = d20.roll();
            assertTrue(r > 0 && r <= 20);
        }
        for (int i = 0; i < 100000; i++) {
            int r = d10.roll();
            assertTrue(r > 0 && r <= 10);
        }
        for (int i = 0; i < 100000; i++) {
            int r = d100.roll();
            assertTrue(r > 0 && r <= 100);
        }
        for (int i = 0; i < 100000; i++) {
            int r = d3d6.roll();
            assertTrue(r >= 3 && r <= 18);
        }
        for (int i = 0; i < 100000; i++) {
            int r = d4.roll();
            assertTrue(r > 0 && r <= 4);
        }
    }

}
