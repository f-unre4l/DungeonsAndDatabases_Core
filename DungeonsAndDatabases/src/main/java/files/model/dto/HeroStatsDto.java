package files.model.dto;

import java.util.UUID;

public class HeroStatsDto {

    private UUID id;
    private int strength;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private int intelligence;
    private int charisma;
    private int experience;

    public HeroStatsDto(UUID id, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma, int experience) {
        this.id = id;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.experience = experience;
    }

    public HeroStatsDto() {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
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

    public static HeroStatsDto getHeroStatsFromHero(HeroCreationDto hero, UUID uuid) {
        return new HeroStatsDto(
                uuid,
                hero.getStrength(),
                hero.getDexterity(),
                hero.getConstitution(),
                hero.getWisdom(),
                hero.getIntelligence(),
                hero.getCharisma(),
                hero.getExperience());
    }

    @Override
    public String toString() {
        return "HeroStats{" +
               "id=" + id +
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
