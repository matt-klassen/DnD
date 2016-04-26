package klassen.matt.dndproject.model.traits;

/**
 * Enumeration of all skills in D&D 5e, represented with
 * skill name and Ability Score associated with that skill
 */
public enum Skill {

    ACROBATICS("Acrobatics", "Dexterity"),
    ANIMAL_HANDLING("Animal Handling", "Wisdom"),
    ARCANA("Arcana", "Intelligence"),
    ATHLETICS("Athletics", "Strength"),
    DECEPTION("Deception", "Charisma"),
    HISTORY("History", "Intelligence"),
    INSIGHT("Insight", "Wisdom"),
    INTIMIDATION("Intimidation", "Charisma"),
    INVESTIGATION("Investigation", "Intelligence"),
    MEDICINE("Medicine", "Wisdom"),
    NATURE("Nature", "Intelligence"),
    PERCEPTION("Perception", "Wisdom"),
    PERFORMANCE("Performance", "Charisma"),
    PERSUATION("Persuation", "Charisma"),
    RELIGION("Religion", "Intelligence"),
    SLEIGHT_OF_HAND("Sleight of Hand", "Dexterity"),
    STEALTH("Stealth", "Dexterity"),
    SURVIVAL("Survival", "Wisdom");

    private String skillName;
    private String associatedScore;

    Skill(String skillName, String associatedScore) {
        this.skillName = skillName;
        this.associatedScore = associatedScore;
    }

    public String getSkillName() { return skillName; }

    public String getAssociatedScore() { return associatedScore; }

}
