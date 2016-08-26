package klassen.matt.dndproject.ui;

import javafx.scene.control.TextArea;
import klassen.matt.dndproject.model.creature.Hero;

/**
 * Field that features info on currently selected hero
 */
public class HeroInfo extends TextArea {

    private static HeroInfo instance;
    private static GroupBox party;

    private HeroInfo(GroupBox party) {
        this.party = party;
        this.setEditable(false);
    }

    public static HeroInfo getInstance(GroupBox party) {
        if (instance == null) {
            instance = new HeroInfo(party);
            return instance;
        } else {
            return instance;
        }
    }

    public static void displaySelectedHero(Hero hero) {
        instance.clear();
        instance.setText(hero.getName() + "\n---------" +
        "\nLevel: " + hero.getLevel() + "\nCurrent Hit Points: " + hero.getCHitPoints() +
        "\nMax Hit Points: " + hero.getHitPoints() +
        "\nArmor Class: " + hero.getArmorClass() +
        "\nPrimary Ability: " + hero.getAbilityScores().getKeyScore() + " (modifier " +
        ((hero.getAbilityScores().getKeyScore()-10)/2) + ")" +
        "\nProficiency Bonus: " + hero.getProfBonus() +
        "\nCurrent Experience Point Total: " + hero.getExperience());
    }

}
