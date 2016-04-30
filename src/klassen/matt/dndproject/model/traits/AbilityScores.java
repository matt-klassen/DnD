package klassen.matt.dndproject.model.traits;

/**
 * Represents the set of standard ability scores for a creature:
 * Strength, Dexterity, Constitution, Intellect, Wisdom, Charisma
 */
public class AbilityScores {

    private int strScore;
    private int dexScore;
    private int conScore;
    private int intScore;
    private int wisScore;
    private int chaScore;

    public AbilityScores(int strScore, int dexScore,
                         int conScore, int intScore,
                         int wisScore, int chaScore) {
        this.strScore = strScore;
        this.dexScore = dexScore;
        this.conScore = conScore;
        this.intScore = intScore;
        this.wisScore = wisScore;
        this.chaScore = chaScore;
    }

    public void setStrScore(int newStrScore) { strScore = newStrScore; }

    public void setDexScore(int newDexScore) {
        dexScore = newDexScore;
    }

    public void setConScore(int newConScore) {
        conScore = newConScore;
    }

    public void setIntScore(int newIntScore) {
        intScore = newIntScore;
    }

    public void setWisScore(int newWisScore) {
        wisScore = newWisScore;
    }

    public void setChaScore(int newChaScore) {
        chaScore = newChaScore;
    }

    public int getStrScore() {
        return strScore;
    }

    public int getDexScore() {
        return dexScore;
    }

    public int getConScore() {
        return conScore;
    }

    public int getIntScore() {
        return intScore;
    }

    public int getWisScore() {
        return wisScore;
    }

    public int getChaScore() {
        return chaScore;
    }

}
