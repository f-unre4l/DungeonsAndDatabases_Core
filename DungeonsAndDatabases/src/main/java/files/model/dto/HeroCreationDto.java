package files.model.dto;

public class HeroCreationDto {

    private String name;
    private String race;
    private String heroClass;
    private int strength;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private int intelligence;
    private int charisma;
    private int experience;

    public HeroCreationDto(
            String name, String race, String heroClass, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma,
            int experience) {
        this.name = name;
        this.race = race;
        this.heroClass = heroClass;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;
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
}
