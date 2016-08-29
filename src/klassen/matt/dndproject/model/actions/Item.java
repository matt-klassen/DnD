package klassen.matt.dndproject.model.actions;

import klassen.matt.dndproject.model.actions.exception.MagicItemException;
import klassen.matt.dndproject.model.creature.AbstractCreature;
import klassen.matt.dndproject.model.mechanics.Effect;

/**
 * Represents a useable item
 */
public class Item extends Action {

    AbstractCreature owner;
    /** item is considered to be a magic item */
    boolean isMagic;

    /**
     * Constructor
     *
     * @param name      the name of the item
     * @param effect    the item's effect when used as an action
     * @param owner     the owner of the item
     * @param isMagic   true if item is magic, false otherwise
     */
    public Item(String name, Effect effect, AbstractCreature owner, boolean isMagic) {
        super(name, effect);
        this.owner = owner;
        this.isMagic = isMagic;
        owner.addItem(this);
    }

    /**
     * Constructor
     *
     * @param name      the name of the item
     * @param effect    the item's effect when used as an action
     * @param isMagic   true if item is magic, false otherwise
     */
    public Item(String name, Effect effect, boolean isMagic) {
        super(name, effect);
        owner = null;
        this.isMagic = isMagic;
    }

    public void setOwner(AbstractCreature owner) {
        if (this.owner != null) {
            this.owner.removeItem(this);
        }
        this.owner = owner;
    }

    public AbstractCreature getOwner() {
        return owner;
    }

    public boolean getIsMagic() {
        return isMagic;
    }

    /**
     * Changes the item's effect and sets its status to magical
     */
    public void enchantItem(Effect effect) throws MagicItemException {

        if (isMagic) {
            throw new MagicItemException("Magic items cannot be enchanted");
        }
        this.effect = effect;
        isMagic = true;
    }

}
