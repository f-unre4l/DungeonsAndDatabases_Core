package files.model;

public enum HeroClass {
    ARTIFICER("Artificer", 8),
    BARBARIAN("Barbarian", 12),
    BARD("Bard", 8),
    CLERIC("Cleric", 8),
    DRUID("Druid", 8),
    FIGHTER("Fighter", 10),
    MONK("Monk", 8),
    PALADIN("Paladin", 10),
    RANGER("Ranger", 10),
    ROGUE("Rogue", 8),
    SORCERER("Sorcerer", 6),
    WARLOCK("Warlock", 8),
    WIZARD("Wizard", 6);

    private final String validHeroClass;
    private final int hitDie;

    HeroClass(String chosenHeroClass, int hitDie) {
        this.validHeroClass = chosenHeroClass;
        this.hitDie = hitDie;
    }

    public int getHitDie() {
        return hitDie;
    }

    public String getValidHeroClass() {
        return validHeroClass;
    }
}

