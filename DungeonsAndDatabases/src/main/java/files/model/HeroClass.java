package files.model;

public enum HeroClass {
    ARTIFICER("Artificer"),
    BARBARIAN("Barbarian"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    FIGHTER("Fighter"),
    MONK("Monk"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final String validHeroClass;

    HeroClass(String chosenHeroClass) {
        this.validHeroClass = chosenHeroClass;
    }

    public String getValidHeroClass() {
        return validHeroClass;
    }
}

