package klassen.matt.dndproject.model.mechanics;

import java.util.regex.Pattern;

/**
 * Represents a die (or group of same-sided die)
 */
public class Die {

    private static final String REGEX = "d";
    private int numOfDie;
    private int numOfSides;

    /**
     * Constructor
     *
     * @param dieExpression      the string expression to be interpreted as
     *                           number of and number of sides of die, where
     *                           the int before "d" is number of die and
     *                           the int after "d" is the number of sides
     */
    public Die(String dieExpression) {
        Pattern pattern = Pattern.compile(REGEX);
        String[] dice = pattern.split(dieExpression);

        numOfDie = Integer.parseInt(dice[0]);
        numOfSides = Integer.parseInt(dice[1]);
    }

    /**
     * Constructor
     *
     * @param numOfDie          the number of die in the group
     * @param numOfSides        the number of sides on the group of die
     */
    public Die(int numOfDie, int numOfSides) {
        this.numOfDie = numOfDie;
        this.numOfSides = numOfSides;
    }

    /**
     * Generates a random int within the range defined by
     * numOfDie to numOfDie * numOfSides
     *
     * @return the int result of the die roll
     */
    public int roll() {
        int result = 0;

        for (int i = 0; i < numOfDie; i++) {
            result += (int) Math.ceil(Math.random() * numOfSides);
        }

        return result;
    }

}
