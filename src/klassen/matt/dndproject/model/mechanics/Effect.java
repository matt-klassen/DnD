package klassen.matt.dndproject.model.mechanics;

import klassen.matt.dndproject.model.mechanics.Die;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the effect of an invoked action, which has
 * an associated type, such as fire damage, healing, etc,
 * a die or multiple die for rolling the effect
 * and an optional description field.
 */
public class Effect {

    private List<Die> dieList;
    private String effectType;
    private String description;


    /**
     * Constructor
     *
     * @param dieList        the (list of) die that will be rolled when the effect is invoked
     * @param effectType     the type of effect
     */
    public Effect(List<Die> dieList, String effectType) {
        this.dieList = dieList;
        this.effectType = effectType;
    }

    /**
     * Constructor
     *
     * @param dieList        the (list of) die that will be rolled when the effect is invoked
     * @param effectType     the type of effect
     * @param description    the description of the effect
     */
    public Effect(List<Die> dieList, String effectType, String description) {
        this.dieList = dieList;
        this.effectType = effectType;
        this.description = description;
    }

    /**
     * Constructor
     *
     * @param dieList        the die that will be rolled when the effect is invoked
     * @param effectType     the type of effect
     */
    public Effect(Die dieList, String effectType) {
        this.dieList = new ArrayList<Die>();
        this.dieList.add(dieList);
        this.effectType = effectType;
    }

    /**
     * Constructor
     *
     * @param dieList        the die that will be rolled when the effect is invoked
     * @param effectType     the type of effect
     * @param description    the description of the effect
     */
    public Effect(Die dieList, String effectType, String description) {
        this.dieList = new ArrayList<Die>();
        this.dieList.add(dieList);
        this.effectType = effectType;
        this.description = description;
    }


    /**
     * Rolls all die, returns sum of rolls
     *
     * @return the int result of rolling all die
     */
    public int rollDie() {
        int result = 0;
        for (Die d : dieList) {
            result += d.roll();
        }
        return result;
    }

    public String getEffectType() { return effectType; }

    public String getDescription() { return description; }

}
