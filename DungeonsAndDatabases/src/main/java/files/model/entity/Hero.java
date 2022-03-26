package files.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Hero")
@Table(name = "heroes")
@ApiModel(description = "All the basic and necessary information about a hero")
public class Hero {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @ApiModelProperty(notes = "The unique identifier of a hero")
    private UUID id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The name of a hero")
    private String name;

    @Column(name = "race")
    @ApiModelProperty(notes = "The race of your Hero. Has to be one of these: Dragonborn, Dwarf, Elf, Gnome, Halfelf, Halforc, Halfling, Human, Tiefling")
    private String race;

    @Column(name = "class")
    @ApiModelProperty(notes = "The class of your Hero. Has to be one of these: Artificer, Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, " +
                              "Rogue, Sorcerer, Warlock, Wizard")
    private String heroClass;

    public Hero() {

    }

    public Hero(
            String name, String race, String heroClass) {
        this.name = name;
        this.race = race;
        this.heroClass = heroClass;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Hero{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", race='" + race + '\'' +
               ", heroClass='" + heroClass + '\'' +
               '}';
    }
}
