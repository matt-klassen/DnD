package klassen.matt.dndproject.model.traits;

/**
 * Represents levels one to twenty for player characters. Each level has the
 * int that represents what level it is, the int that represents how much exp
 * is needed to attain that level, and the int that represents the proficiency
 * bonus that level provides.
 */
public enum Levels {

    LVL1(1, 0, 2),
    LVL2(2, 300, 2),
    LVL3(3, 900, 2),
    LVL4(4, 2700, 2),
    LVL5(5, 6500, 3),
    LVL6(6, 14000, 3),
    LVL7(7, 23000, 3),
    LVL8(8, 34000, 3),
    LVL9(9, 48000, 4),
    LVL10(10, 64000, 4),
    LVL11(11, 85000, 4),
    LVL12(12, 100000, 4),
    LVL13(13, 120000, 5),
    LVL14(14, 140000, 5),
    LVL15(15, 165000, 5),
    LVL16(16, 195000, 5),
    LVL17(17, 225000, 6),
    LVL18(18, 265000, 6),
    LVL19(19, 305000, 6),
    LVL20(20, 355000, 6);

    private int level;
    private int expThreshold;
    private int profBonus;

    Levels(int level, int expThreshold, int profBonus) {
        this.level = level;
        this.expThreshold = expThreshold;
        this.profBonus = profBonus;
    }

    public int getExpThreshold() {
        return expThreshold;
    }

    public int getProfBonus() {
        return profBonus;
    }

    public int getLevel() {
        return level;
    }

}
