package files.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "race")
    private String race;

    @Column(name = "heroClass")
    private String heroClass;

    @Column(name = "STR")
    private int strength;

    @Column(name = "DEX")
    private int dexterity;

    @Column(name = "CON")
    private int constitution;

    @Column(name = "WIS")
    private int wisdom;

    @Column(name = "INT")
    private int intelligence;

    @Column(name = "CHA")
    private int charisma;

    @Column(name = "EXP")
    private int experience;

    public Hero() {

    }

    public Hero(
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

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + name + ", desc=" + race + ", heroClass=" + heroClass + ", STR=" + strength + ", DEX=" + dexterity + ", CON=" +
               constitution + ", WIS=" + wisdom + ", INT=" + intelligence + ", CHA=" + charisma + ", EXP=" + experience + "]";
    }
}
