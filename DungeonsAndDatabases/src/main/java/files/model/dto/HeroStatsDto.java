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
    private int hitpoints;

    public HeroStatsDto(UUID id, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma, int experience, int hitpoints) {
        this.id = id;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.experience = experience;
        this.hitpoints = hitpoints;
    }

    @SuppressWarnings("unused") //Needed in HeroController.java/createHero for new RestTemplate().postForEntity()
    public HeroStatsDto() {
    }

    public static HeroStatsDto getHeroStatsFromHero(HeroCreationDto hero, UUID uuid, int hitpoints) {
        return new HeroStatsDto(
                uuid,
                hero.getStrength(),
                hero.getDexterity(),
                hero.getConstitution(),
                hero.getWisdom(),
                hero.getIntelligence(),
                hero.getCharisma(),
                hero.getExperience(),
                hitpoints);
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

    public int getHitpoints() {
        return hitpoints;
    }

    @SuppressWarnings("unused") //Needed in HeroController.java/createHero for new RestTemplate().postForEntity()
    public UUID getId() {
        return id;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getWisdom() {
        return wisdom;
    }

    @Override
    public String toString() {
        return "HeroStatsDto{" +
               "id=" + id +
               ", strength=" + strength +
               ", dexterity=" + dexterity +
               ", constitution=" + constitution +
               ", wisdom=" + wisdom +
               ", intelligence=" + intelligence +
               ", charisma=" + charisma +
               ", experience=" + experience +
               ", hitpoints=" + hitpoints +
               '}';
    }
}
