package klassen.matt.dndproject.model.actions;

import klassen.matt.dndproject.model.mechanics.Effect;

/**
 * Represents actions that can be taken by a creature
 */
public class Action {

    protected String name;
    protected Effect effect;

    public Action(String name) {
        this.name = name;
    }

    public Action(String name, Effect effect)   {
        this.name = name;
        this.effect = effect;
    }

    /**
     * Invokes the action, returning the action's effect or
     * null if the action has no effect.
     *
     * @return the effect of the action
     */
    public Effect invokeAction() { return effect; } // revise as necessary with change of effect class

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action that = (Action) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
