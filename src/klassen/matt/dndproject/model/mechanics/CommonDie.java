package klassen.matt.dndproject.model.mechanics;

/**
 *
 */
public enum CommonDie {

    D4(initD4()),
    D6(initD6()),
    D8(initD8()),
    D10(initD10()),
    D12(initD12()),
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
    private static Die initD6() {
        return new Die("1d6");
    }
    private static Die initD8() {
        return new Die("1d8");
    }
    private static Die initD10() {
        return new Die("1d10");
    }
    private static Die initD12() {
        return new Die("1d12");
    }
    private static Die initD20() {
        return new Die("1d20");
    }

}
