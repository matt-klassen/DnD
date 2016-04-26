package klassen.matt.dndproject.model.actions;

import klassen.matt.dndproject.model.mechanics.Effect;

/**
 * Represents an action that can be taken in or out
 * of combat by a character, monster or NPC
 */
public class Action extends AbstractAction {

    /** action is classified as a legendary action if true */
    private boolean isLegendary;

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
    public Action(String name, Effect effect, boolean isLegendary) {
        super(name, effect);
        this.isLegendary = isLegendary;
    }

    public boolean getIsLegendary() {
        return isLegendary;
    }
}
