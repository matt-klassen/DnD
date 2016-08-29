package klassen.matt.dndproject.model.mechanics;

/**
 * An enum for some commonly used die for quicker access in the program
 */
public enum CommonDie {

    D4(initD4()),
    D10(initD10()),
    D20(initD20());

    private Die die;

    CommonDie(Die die) {
        this.die = die;
    }

    public Die getDie() {
        return die;
    }

    private static Die initD4() {
        return new Die("1d4");
    }
    private static Die initD10() {
        return new Die("1d10");
    }
    private static Die initD20() {
        return new Die("1d20");
    }

}
