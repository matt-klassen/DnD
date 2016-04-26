package klassen.matt.dndproject.model.actions;

import klassen.matt.dndproject.model.actions.exception.SpellException;
import klassen.matt.dndproject.model.mechanics.Effect;

/**
 * Represents spells.
 * Spells can either be a cantrip, which can be cast at will, or
 * have an associated minimum spell level, where the spell cannot be
 * cast with a spell slot of a level lower than the minimum. The
 * possible range of spell levels is the set of integers from 1 to 9.
 *
 */
public class Spell extends AbstractAction {

    private Integer minSpellLevel;
    private boolean cantrip;

    /**
     * Constructor
     *
     * @param name              the name of the spell
     * @param minSpellLevel     the minimum spell level the spell can be cast at
     * @param effect            the effect of casting the spell
     */
    public Spell(String name, int minSpellLevel, Effect effect) throws SpellException {

        super(name, effect);

        if (minSpellLevel < 1 || minSpellLevel > 9) {
            throw new SpellException("Invalid spell level");
        }
        this.minSpellLevel = minSpellLevel;
        cantrip = false;
}

    /**
     * Constructs a cantrip spell with a given name, which has no
     * associated level and can be cast at will by anyone who knows it.
     *
     * @param name              the name of the spell
     * @param effect            the effect of casting the spell
     */
    public Spell(String name, Effect effect) {
        super(name, effect);
        cantrip = true;
        minSpellLevel = null;
    }

    public boolean getCantripStatus() { return cantrip; }

    public int getMinSpellLevel() { return minSpellLevel; }

}
