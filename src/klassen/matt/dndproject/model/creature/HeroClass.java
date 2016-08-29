package klassen.matt.dndproject.model.creature;

import klassen.matt.dndproject.model.actions.Item;
import klassen.matt.dndproject.model.mechanics.Die;
import klassen.matt.dndproject.model.mechanics.Effect;
import klassen.matt.dndproject.model.traits.AbilityScores;

/**
 * Enum for all possible Hero classes: key ability scores, starting ability scores,
 * starting armor class, items and actions
 */
public enum HeroClass {

    BARBARIAN("Barbarian", 12, initBarbarianScores(), axe()),
    BARD("Bard", 12, initBardScores(), lute()),
    CLERIC("Cleric", 14, initClericScores(), holySymbol()),
    DRUID("Druid", 13, initDruidScores(), staff()),
    FIGHTER("Fighter", 16, initFighterScores(), sword()),
    MONK("Monk", 14, initMonkScores(), fists()),
    PALADIN("Paladin", 16, initPaladinScores(), sword()),
    RANGER("Ranger", 15, initRangerScores(), longbow()),
    ROGUE("Rogue", 14, initRogueScores(), sword()),
    SORCERER("Sorcerer", 10, initSorcererScores(), wand()),
    WARLOCK("Warlock", 10, initWarlockScores(), wand()),
    WIZARD("Wizard", 10, initWizardScores(), wand());

    private String className;
    private int ac;
    private AbilityScores classScores;
    private Item starterItem;

    HeroClass(String className, int ac,
              AbilityScores classScores, Item starterItem) {
        this.className = className;
        this.ac = ac;
        this.classScores = classScores;
        this.starterItem = starterItem;
    }

    public String getClassName() {
        return className;
    }

    public int getAc() {
        return ac;
    }

    public AbilityScores getClassScores() {
        return classScores;
    }

    public Item getStarterItem() {
        return starterItem;
    }

    private static AbilityScores initBarbarianScores() {
        return new AbilityScores(15, 13, 14, 8, 12, 10, "str");
    }

    private static AbilityScores initBardScores() {
        return new AbilityScores(8, 14, 13, 10, 12, 15, "cha");
    }

    private static AbilityScores initClericScores() {
        return new AbilityScores(13, 12, 14, 8, 15, 10, "wis");
    }

    private static AbilityScores initDruidScores() {
        return new AbilityScores(13, 12, 14, 8, 15, 10, "wis");
    }

    private static AbilityScores initFighterScores() {
        return new AbilityScores(15, 13, 14, 8, 12, 10, "str");
    }

    private static AbilityScores initMonkScores() {
        return new AbilityScores(10, 15, 14, 8, 13, 12, "dex");
    }

    private static AbilityScores initPaladinScores() {
        return new AbilityScores(15, 10, 14, 8, 13, 12, "str");
    }

    private static AbilityScores initRogueScores() {
        return new AbilityScores(10, 15, 14, 12, 8, 13, "dex");
    }

    private static AbilityScores initRangerScores() {
        return new AbilityScores(13, 14, 15, 10, 12, 8, "dex");
    }

    private static AbilityScores initSorcererScores() {
        return new AbilityScores(8, 14, 13, 10, 12, 15, "cha");
    }

    private static AbilityScores initWarlockScores() {
        return new AbilityScores(8, 13, 14, 10, 12, 15, "cha");
    }

    private static AbilityScores initWizardScores() {
        return new AbilityScores(8, 14, 13, 15, 12, 10, "int");
    }

    private static Item axe() {
        return new Item("War Axe", axeEffect(), false);
    }

    private static Item sword() {
        return new Item("Short Sword", swordEffect(), false);
    }

    private static Item longbow() {
        return new Item("Longbow", longbowEffect(), false);
    }

    private static Item lute() {
        return new Item("Lute", luteEffect(), false);
    }

    private static Item fists() {
        return new Item("Fists", fistsEffect(), false);
    }

    private static Item holySymbol() {
        return new Item("Holy Symbol", holySymbolEffect(), false);
    }

    private static Item wand() {
        return new Item("Wand of Firebolt", wandEffect(), false);
    }

    private static Item staff() {
        return new Item("Staff", staffEffect(), false);
    }

    private static Effect axeEffect() {
        Die die = new Die("1d12");
        return new Effect(die, "Damage");
    }

    private static Effect swordEffect() {
        Die die = new Die("1d8");
        return new Effect(die, "Damage");
    }

    private static Effect longbowEffect() {
        Die die = new Die("1d10");
        return new Effect(die, "Damage");
    }

    private static Effect luteEffect() {
        Die die = new Die("1d6");
        return new Effect(die, "Healing");
    }

    private static Effect fistsEffect() {
        Die die = new Die("2d4");
        return new Effect(die, "Damage");
    }

    private static Effect holySymbolEffect() {
        Die die = new Die("1d6");
        return new Effect(die, "Healing");
    }

    private static Effect wandEffect() {
        Die die = new Die("1d10");
        return new Effect(die, "Damage");
    }

    private static Effect staffEffect() {
        Die die = new Die("1d8");
        return new Effect(die, "Damage");
    }

}

