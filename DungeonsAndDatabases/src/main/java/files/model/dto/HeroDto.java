package files.model.dto;

import files.model.entity.Hero;

import java.util.UUID;

@SuppressWarnings("unused") //Needed in HeroController.java/getHeroById
public class HeroDto extends HeroCreationDto {

    private final UUID id;
    private final int hitpoints;
    private final int level;
    private final int strengthModifier;
    private final int dexterityModifier;
    private final int constitutionModifier;
    private final int wisdomModifier;
    private final int intelligenceModifier;
    private final int charismaModifier;
    private final String avatar;

    public HeroDto(
            Hero heroBase, HeroStatsDto stats, HeroCalculatorDto heroCalculatorDto, String avatar) {
        super(
                heroBase.getName(), heroBase.getRace(), heroBase.getHeroClass(), stats.getStrength(), stats.getDexterity(), stats.getConstitution(),
                stats.getWisdom(), stats.getIntelligence(), stats.getCharisma(), stats.getExperience());
        this.id = heroBase.getId();
        this.hitpoints = stats.getHitpoints();
        this.level = heroCalculatorDto.getLevel();
        this.strengthModifier = heroCalculatorDto.getStrengthModifier();
        this.dexterityModifier = heroCalculatorDto.getDexterityModifier();
        this.constitutionModifier = heroCalculatorDto.getConstitutionModifier();
        this.wisdomModifier = heroCalculatorDto.getWisdomModifier();
        this.intelligenceModifier = heroCalculatorDto.getIntelligenceModifier();
        this.charismaModifier = heroCalculatorDto.getCharismaModifier();
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getCharismaModifier() {
        return charismaModifier;
    }

    public int getConstitutionModifier() {
        return constitutionModifier;
    }

    public int getDexterityModifier() {
        return dexterityModifier;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public UUID getId() {
        return id;
    }

    public int getIntelligenceModifier() {
        return intelligenceModifier;
    }

    public int getLevel() {
        return level;
    }

    public int getStrengthModifier() {
        return strengthModifier;
    }

    public int getWisdomModifier() {
        return wisdomModifier;
    }

    @Override
    public String toString() {
        return "HeroDto{" +
               "id=" + id +
               super.toString() +
               ", hitpoints=" + hitpoints +
               ", level=" + level +
               ", strengthModifier=" + strengthModifier +
               ", dexterityModifier=" + dexterityModifier +
               ", constitutionModifier=" + constitutionModifier +
               ", wisdomModifier=" + wisdomModifier +
               ", intelligenceModifier=" + intelligenceModifier +
               ", charismaModifier=" + charismaModifier +
               ", avatar=" + avatar +
               '}';
    }
}
