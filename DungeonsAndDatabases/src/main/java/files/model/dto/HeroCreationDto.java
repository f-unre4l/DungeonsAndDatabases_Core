package files.model.dto;

public class HeroCreationDto {

    private final String name;
    private final String race;
    private final String heroClass;
    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int wisdom;
    private final int intelligence;
    private final int charisma;
    private final int experience;

    public HeroCreationDto(
            String name, String race, String heroClass, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma,
            int experience) {
        this.name = name;
        this.race = race;
        this.heroClass = heroClass;
        this.strength = setDefaultIfZero(strength);
        this.dexterity = setDefaultIfZero(dexterity);
        this.constitution = setDefaultIfZero(constitution);
        this.wisdom = setDefaultIfZero(wisdom);
        this.intelligence = setDefaultIfZero(intelligence);
        this.charisma = setDefaultIfZero(charisma);
        this.experience = experience;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getExperience() {
        return experience;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getStrength() {
        return strength;
    }

    public int getWisdom() {
        return wisdom;
    }

    @Override
    public String toString() {
        return "HeroCreationDto{" +
               "name='" + name + '\'' +
               ", race='" + race + '\'' +
               ", heroClass='" + heroClass + '\'' +
               ", strength=" + strength +
               ", dexterity=" + dexterity +
               ", constitution=" + constitution +
               ", wisdom=" + wisdom +
               ", intelligence=" + intelligence +
               ", charisma=" + charisma +
               ", experience=" + experience +
               '}';
    }

    private int setDefaultIfZero(int stat) {
        if (stat == 0) {
            return 10;
        }
        return stat;
    }
}
