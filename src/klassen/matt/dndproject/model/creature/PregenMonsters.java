package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.AbstractActionList;
import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.model.traits.AbilityScores;

import java.util.HashSet;
import java.util.Set;

/**
 * Creates new Monsters based on user input data
 */
public enum PregenMonsters {

    KOBOLD(initKobold()),
    GOBLIN(initGoblin()),
    ORC(initOrc()),
    OWLBEAR(initOwlbear()),
    TROLL(initTroll()),
    RED_DRAGON(initRedDragon()),
    BEHOLDER(initBeholder()),
    DEVIL(initDevil()),
    LICH(initLich());

    private Monster monster;

    PregenMonsters(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    private static Monster initKobold() {
        AbilityScores koboldScores = new AbilityScores(9, 13, 10, 10, 9, 8);
        try {
            Monster kobold = new Monster("Kobold", "Draconic", 12, 5, 30,
                    koboldScores, new HashSet<>(), 1, 100);
            Item dagger = (Item) AbstractActionList.DAGGER.getAbstractAction();
            kobold.addItem(dagger);
            return kobold;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }
    private static Monster initGoblin() {
        AbilityScores goblinScores = new AbilityScores(8, 14, 10, 10, 8, 8);
        try {
            Monster goblin = new Monster("Goblin", "Humanoid", 15, 7, 30,
                    goblinScores, new HashSet<>(), 1, 150);
            Item axe = (Item) AbstractActionList.AXE.getAbstractAction();
            goblin.addItem(axe);
            return goblin;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }
    private static Monster initOrc() {
        AbilityScores orcScores = new AbilityScores(16, 12, 16, 7, 11, 10);
        try {
            Monster orc = new Monster("Orc", "Humanoid", 13, 15, 30,
                    orcScores, new HashSet<>(), 2, 300);
            Item warmaul =
                    (Item) AbstractActionList.WARMAUL.getAbstractAction();
            orc.addItem(warmaul);
            return orc;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }
    private static Monster initOwlbear() {
        AbilityScores owlBScores = new AbilityScores(20, 12, 17, 3, 12, 7);
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Set owlBActions = new HashSet<>();
        owlBActions.add(claw);
        owlBActions.add(bite);
        try {
            Monster owlbear = new Monster("Owlbear", "Monstrosity", 13, 59,
                    30, owlBScores, owlBActions, 3, 700);
            return owlbear;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }
    private static Monster initTroll() {
        AbilityScores trollScores = new AbilityScores(18, 13, 20, 7, 9, 7);
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Action regenerate =
                (Action) AbstractActionList.REGENERATE.getAbstractAction();
        Set trollActions = new HashSet<>();
        trollActions.add(claw);
        trollActions.add(bite);
        trollActions.add(regenerate);
        try {
            Monster troll = new Monster("Troll", "Monstrosity", 15, 84,
                    30, trollScores, trollActions, 5, 1800);
            return troll;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }

    }
    private static Monster initRedDragon() {
        AbilityScores dragonScores = new AbilityScores(27, 10, 25, 16, 13, 21);
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Action fireBreath =
                (Action) AbstractActionList.FIRE_BREATH.getAbstractAction();
        Set dragonActions = new HashSet<>();
        dragonActions.add(claw);
        dragonActions.add(bite);
        dragonActions.add(fireBreath);
        try {
            Monster redDragon = new Monster("Red Dragon", "Draconic", 19, 256,
                    40, dragonScores, dragonActions, 17, 18000);
            return redDragon;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }

    }
    private static Monster initBeholder() {
        AbilityScores beholderScores =
                new AbilityScores(10, 14, 18, 17, 15, 17);
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Action disintegrate = (Action)
                AbstractActionList.DISINTEGRATION_RAY.getAbstractAction();
        Set beholderActions = new HashSet<>();
        beholderActions.add(bite);
        beholderActions.add(disintegrate);
        try {
            Monster beholder = new Monster("Beholder", "Monstrosity", 18, 180,
                    20, beholderScores, beholderActions, 13, 10000);
            return beholder;

        } catch (NoNameException e) {
            throw new RuntimeException();
        }

    }
    private static Monster initDevil() {
        AbilityScores devilScores = new AbilityScores(21, 14, 18, 18, 15, 18);
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Set devilActions = new HashSet<>();
        devilActions.add(claw);
        try {
            Monster devil = new Monster("Devil", "Fiend", 18, 180,
                    40, devilScores, devilActions, 14, 11500);
            Item wandOfDrainLife = (Item)
                    AbstractActionList.WAND_OF_DRAIN_LIFE.getAbstractAction();
            devil.addItem(wandOfDrainLife);
            return devil;

        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }
    private static Monster initLich() {
        AbilityScores lichScores = new AbilityScores(11, 16, 16, 20, 14, 16);
        Action drainSoul = (Action)
                AbstractActionList.DRAIN_SOUL.getAbstractAction();
        Set lichActions = new HashSet<>();
        lichActions.add(drainSoul);
        try {
            Monster lich = new Monster("Lich", "Undead", 17, 135,
                    30, lichScores, lichActions, 20, 33000);
            Item wandOfFireball = (Item)
                    AbstractActionList.WAND_OF_FIREBALL.getAbstractAction();
            Item wandOfDrainLife = (Item)
                    AbstractActionList.WAND_OF_DRAIN_LIFE.getAbstractAction();
            lich.addItem(wandOfFireball);
            lich.addItem(wandOfDrainLife);
            return lich;

        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

}
