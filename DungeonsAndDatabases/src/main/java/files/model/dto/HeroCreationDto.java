package files.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All the information needed to create a new hero. Strength, dexterity, constitution, wisdom, intelligence, charisma & experience can " +
                        "be left empty. They will be set to default values.")
public class HeroCreationDto {
    @ApiModelProperty(notes = "The name of a hero")
    private final String name;
    @ApiModelProperty(notes = "The race of your Hero. Has to be one of these: Dragonborn, Dwarf, Elf, Gnome, Halfelf, Halforc, Halfling, Human, Tiefling")
    private final String race;
    @ApiModelProperty(notes = "The class of your Hero. Has to be one of these: Artificer, Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, " +
                              "Rogue, Sorcerer, Warlock, Wizard")
    private final String heroClass;
    @ApiModelProperty(notes = "The points a hero has in the strength stat typically from 8 to 20")
    private final int strength;
    @ApiModelProperty(notes = "The points a hero has in the dexterity stat typically from 8 to 20")
    private final int dexterity;
    @ApiModelProperty(notes = "The points a hero has in the constitution stat typically from 8 to 20")
    private final int constitution;
    @ApiModelProperty(notes = "The points a hero has in the wisdom stat typically from 8 to 20")
    private final int wisdom;
    @ApiModelProperty(notes = "The points a hero has in the intelligence stat typically from 8 to 20")
    private final int intelligence;
    @ApiModelProperty(notes = "The points a hero has in the charisma stat typically from 8 to 20")
    private final int charisma;
    @ApiModelProperty(notes = "The experience points a hero has. These determine the heroes level. Level 1 at 0 exp and the maximum level 20 at 355000 exp.")
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
