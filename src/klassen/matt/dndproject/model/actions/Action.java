package klassen.matt.dndproject.model.actions;

import klassen.matt.dndproject.model.mechanics.Effect;

/**
 * Represents an action that can be taken in or out
 * of combat by a character, monster or NPC
 */
public class Action extends AbstractAction {

    /** action is classified as a legendary action if true */
    private boolean isLegendary;
    /** action is classified as a spell if true */
    private boolean isSpell;

    /**
     * Constructor
     *
     * @param name      the name of the action
     */
    public Action(String name) {
        super(name);
        isLegendary = false;
    }

    /**
     * Constructor
     *
     * @param name      the name of the action
     * @param effect    the actions's effect when invoked
     */
    public Action(String name, Effect effect) {
        super(name, effect);
        isLegendary = false;
    }

    /**
     * Constructor
     *
     * @param name          the name of the action
     * @param effect        the actions's effect when invoked
     * @param isLegendary   the action's legendary status
     */
    public Action(String name, Effect effect, boolean isLegendary, boolean isSpell) {
        super(name, effect);
        this.isLegendary = isLegendary;
        this.isSpell = isSpell;
    }

    public boolean getIsLegendary() {
        return isLegendary;
    }

    public boolean getIsSpell() { return isSpell; }
}
