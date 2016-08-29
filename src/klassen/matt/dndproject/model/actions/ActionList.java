package klassen.matt.dndproject.model.actions;

import klassen.matt.dndproject.model.mechanics.CommonDie;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;

/**
 * Enum for actions to be used by creatures
 */
public enum ActionList {

    DAGGER(initDagger()),
    AXE(initAxe()),
    WAND_OF_FIREBALL(initWandOfFireball()),
    WAND_OF_DRAIN_LIFE(initWandOfDrainLife()),
    WARMAUL(initWarmaul()),
    CLAWS(initClaws()),
    BITE(initBite()),
    REGENERATE(initRegenerate()),
    FIRE_BREATH(initFireBreath()),
    DISINTEGRATION_RAY(initDisintegrationRay()),
    DRAIN_SOUL(initDrainSoul());

    private Action abstractAction;

    ActionList(Action abstractAction) {
        this.abstractAction = abstractAction;
    }

    public Action getAbstractAction() {
        return abstractAction;
    }

    private static Action initDagger() {
        Effect effect = new Effect(CommonDie.D4.getDie(), "Damage");
        Item dagger = new Item("Dagger", effect, false);
        return dagger;
    }
    private static Action initAxe() {
        Effect effect = new Effect(CommonDie.D10.getDie(), "Damage");
        Item axe = new Item("Axe", effect, false);
        return axe;
    }
    private static Action initWandOfFireball() {
        Die die = new Die("8d6");
        Effect effect = new Effect(die, "Damage");
        Item WandOfFireball = new Item("Wand of Fireball", effect, true);
        return WandOfFireball;

    }
    private static Action initWandOfDrainLife() {
        Die die = new Die("4d8");
        Effect effect = new Effect(die, "Hybrid");
        Item WandOfDrainLife = new Item("Wand of Drain Life", effect, true);
        return WandOfDrainLife;
    }
    private static Action initWarmaul() {
        Die die = new Die("2d6");
        Effect effect = new Effect(die, "Damage");
        Item warmaul = new Item("Warmaul", effect, false);
        return warmaul;
    }
    private static Action initClaws() {
        Die die = new Die("2d8");
        Effect effect = new Effect(die, "Damage");
        Action claws = new Action("Claw Attack", effect);
        return claws;
    }
    private static Action initBite() {
        Die die = new Die("2d8");
        Effect effect = new Effect(die, "Damage");
        Action bite = new Action("Bite", effect);
        return bite;
    }
    private static Action initRegenerate() {
        Die die = new Die("2d4");
        Effect effect = new Effect(die, "Healing");
        Action regenerate = new Action("Regenerate", effect);
        return regenerate;
    }
    private static Action initFireBreath() {
        Die die = new Die("8d6");
        Effect effect = new Effect(die, "Damage");
        Action fireBreath = new Action("Fire Breath", effect);
        return fireBreath;
    }

    private static Action initDisintegrationRay() {
        Die die = new Die("8d6");
        Effect effect = new Effect(die, "Damage");
        Action disintegrationRay = new Action("Disintegration Ray", effect);
        return disintegrationRay;
    }
    private static Action initDrainSoul() {
        Die die = new Die("10d10");
        Effect effect = new Effect(die, "Hybrid");
        Action drainSoul = new Action("Drain Soul", effect);
        return drainSoul;
    }


}
