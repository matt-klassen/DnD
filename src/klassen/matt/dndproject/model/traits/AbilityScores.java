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
    /** the key(primary) ability score for the owner of this set of Ability Scores */
    private String keyScore;

    public AbilityScores(int strScore, int dexScore,
                         int conScore, int intScore,
                         int wisScore, int chaScore,
                         String keyScore) {
        this.strScore = strScore;
        this.dexScore = dexScore;
        this.conScore = conScore;
        this.intScore = intScore;
        this.wisScore = wisScore;
        this.chaScore = chaScore;
        this.keyScore = keyScore;
    }

    public void incKeyScore() {
        switch (keyScore) {
            case "str": strScore++; break;
            case "dex": dexScore++; break;
            case "con": conScore++; break;
            case "int": intScore++; break;
            case "wis": wisScore++; break;
            case "cha": chaScore++; break;
        }
    }

    public void incConScore() {
        conScore++;
    }

    public String getKeyScoreAsString() {
        return keyScore;
    }

    public int getKeyScore() {
        switch (keyScore) {
            case "str": return strScore;
            case "dex": return dexScore;
            case "con": return conScore;
            case "int": return intScore;
            case "wis": return wisScore;
            case "cha": return chaScore;
            default: throw new RuntimeException();
        }
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
