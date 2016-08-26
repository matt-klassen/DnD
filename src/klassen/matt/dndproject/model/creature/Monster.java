package klassen.matt.dndproject.model.creature;


import klassen.matt.dndproject.model.actions.AbstractActionList;
import klassen.matt.dndproject.model.actions.Action;
import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.creature.exception.NoNameException;
import klassen.matt.dndproject.model.traits.AbilityScores;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a monster in D&D 5e
 */
public class Monster extends AbstractCreature {

    private int challengeRating;
    private int experience;

    private static Integer numKobolds = 0;
    private static Integer numGoblins = 0;
    private static Integer numOrcs = 0;
    private static Integer numOwlbears = 0;
    private static Integer numTrolls = 0;
    private static Integer numRedDragons = 0;
    private static Integer numBeholders = 0;
    private static Integer numDevils = 0;
    private static Integer numLichs = 0;

    /**
     * Constructor
     *
     * @param name              the name of the creature
     * @param creatureType      the creature's type
     * @param armorClass        the creature's innate (unmodified) armor class
     * @param hitPoints         the creature's innate hit point total (unmodified)
     * @param speed             the creature's innate speed (unmodified)
     * @param abilityScores     the creature's set of base ability scores
     * @param actions           the set of actions available to the creature
     * @param challengeRating   the monster's challenge rating
     * @param experience        the experience awarded for defeating the monster
     */
    public Monster(String name, String creatureType, int armorClass,
                   int hitPoints, int speed, AbilityScores abilityScores,
                   Set<Action> actions, int challengeRating, int experience)
        throws NoNameException {

        super(name, creatureType, armorClass, hitPoints, speed,
                abilityScores, actions);

        this.challengeRating = challengeRating;
        this.experience = experience;
    }

    public int getChallengeRating() {
        return challengeRating;
    }

    public int getExperience() {
        return experience;
    }


    /**
     * Below is static initialization for all Monsters
     */

    public static Monster newKobold() {
        AbilityScores koboldScores = new AbilityScores(9, 13, 10, 10, 9, 8, "dex");
        try {
            numKobolds++;
            String name = "Kobold " + numKobolds.toString();
            Monster kobold = new Monster(name, "Draconic", 12, 5, 30,
                    koboldScores, new HashSet<>(), 1, 100);
            Item dagger = (Item) AbstractActionList.DAGGER.getAbstractAction();
            kobold.addItem(dagger);
            return kobold;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

    public static Monster newGoblin() {
        AbilityScores goblinScores = new AbilityScores(8, 14, 10, 10, 8, 8, "dex");
        try {
            numGoblins++;
            String name = "Goblin " + numGoblins.toString();
            Monster goblin = new Monster(name, "Humanoid", 15, 7, 30,
                    goblinScores, new HashSet<>(), 1, 150);
            Item axe = (Item) AbstractActionList.AXE.getAbstractAction();
            goblin.addItem(axe);
            return goblin;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

    public static Monster newOrc() {
        AbilityScores orcScores = new AbilityScores(16, 12, 16, 7, 11, 10, "str");
        try {
            numOrcs++;
            String name = "Orc " + numOrcs.toString();
            Monster orc = new Monster(name, "Humanoid", 13, 15, 30,
                    orcScores, new HashSet<>(), 2, 300);
            Item warmaul =
                    (Item) AbstractActionList.WARMAUL.getAbstractAction();
            orc.addItem(warmaul);
            return orc;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

    public static Monster newOwlbear() {
        AbilityScores owlBScores = new AbilityScores(20, 12, 17, 3, 12, 7, "str");
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Set owlBActions = new HashSet<>();
        owlBActions.add(claw);
        owlBActions.add(bite);
        try {
            numOwlbears++;
            String name = "Owlbear " + numOwlbears.toString();
            Monster owlbear = new Monster(name, "Monstrosity", 13, 59,
                    30, owlBScores, owlBActions, 3, 700);
            return owlbear;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

    public static Monster newTroll() {
        AbilityScores trollScores = new AbilityScores(18, 13, 20, 7, 9, 7, "str");
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Action regenerate =
                (Action) AbstractActionList.REGENERATE.getAbstractAction();
        Set trollActions = new HashSet<>();
        trollActions.add(claw);
        trollActions.add(bite);
        trollActions.add(regenerate);
        try {
            numTrolls++;
            String name = "Troll " + numTrolls.toString();
            Monster troll = new Monster(name, "Monstrosity", 15, 84,
                    30, trollScores, trollActions, 5, 1800);
            return troll;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }

    }

    public static Monster newRedDragon() {
        AbilityScores dragonScores = new AbilityScores(27, 10, 25, 16, 13, 21, "str");
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Action fireBreath =
                (Action) AbstractActionList.FIRE_BREATH.getAbstractAction();
        Set dragonActions = new HashSet<>();
        dragonActions.add(claw);
        dragonActions.add(bite);
        dragonActions.add(fireBreath);
        try {
            numRedDragons++;
            String name = "Red Dragon " + numRedDragons.toString();
            Monster redDragon = new Monster(name, "Draconic", 19, 256,
                    40, dragonScores, dragonActions, 17, 18000);
            return redDragon;
        } catch (NoNameException e) {
            throw new RuntimeException();
        }

    }

    public static Monster newBeholder() {
        AbilityScores beholderScores =
                new AbilityScores(10, 14, 18, 17, 15, 17, "int");
        Action bite = (Action) AbstractActionList.BITE.getAbstractAction();
        Action disintegrate = (Action)
                AbstractActionList.DISINTEGRATION_RAY.getAbstractAction();
        Set beholderActions = new HashSet<>();
        beholderActions.add(bite);
        beholderActions.add(disintegrate);
        try {
            numBeholders++;
            String name = "Beholder " + numBeholders.toString();
            Monster beholder = new Monster(name, "Monstrosity", 18, 180,
                    20, beholderScores, beholderActions, 13, 10000);
            return beholder;

        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

    public static Monster newDevil() {
        AbilityScores devilScores = new AbilityScores(21, 14, 18, 18, 15, 18, "cha");
        Action claw = (Action) AbstractActionList.CLAWS.getAbstractAction();
        Set devilActions = new HashSet<>();
        devilActions.add(claw);
        try {
            numDevils++;
            String name = "Devil " + numDevils.toString();
            Monster devil = new Monster(name, "Fiend", 18, 180,
                    40, devilScores, devilActions, 14, 11500);
            Item wandOfDrainLife = (Item)
                    AbstractActionList.WAND_OF_DRAIN_LIFE.getAbstractAction();
            devil.addItem(wandOfDrainLife);
            return devil;

        } catch (NoNameException e) {
            throw new RuntimeException();
        }
    }

    public static Monster newLich() {
        AbilityScores lichScores = new AbilityScores(11, 16, 16, 20, 14, 16, "int");
        Action drainSoul = (Action)
                AbstractActionList.DRAIN_SOUL.getAbstractAction();
        Set lichActions = new HashSet<>();
        lichActions.add(drainSoul);
        try {
            numLichs++;
            String name = "Lich " + numLichs.toString();
            Monster lich = new Monster(name, "Undead", 17, 135,
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
