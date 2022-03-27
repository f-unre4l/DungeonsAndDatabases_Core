package files.model.dto;

import files.model.entity.Hero;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

@SuppressWarnings("unused") //Needed in HeroController.java/getHeroById
@ApiModel(description = "Has all the possible information of a hero.")
public class HeroDto extends HeroCreationDto {
    @ApiModelProperty(notes = "The unique identifier of a hero")
    private final UUID id;
    @ApiModelProperty(notes = "The calculated hitpoints of a hero based on it's level, class & constitutionModifier")
    private final int hitpoints;
    @ApiModelProperty(notes = "The calculated level of a hero based on it's experience")
    private final int level;
    @ApiModelProperty(notes = "The calculated modifier of the corresponding stat")
    private final int strengthModifier;
    @ApiModelProperty(notes = "The calculated modifier of the corresponding stat")
    private final int dexterityModifier;
    @ApiModelProperty(notes = "The calculated modifier of the corresponding stat")
    private final int constitutionModifier;
    @ApiModelProperty(notes = "The calculated modifier of the corresponding stat")
    private final int wisdomModifier;
    @ApiModelProperty(notes = "The calculated modifier of the corresponding stat")
    private final int intelligenceModifier;
    @ApiModelProperty(notes = "The calculated modifier of the corresponding stat")
    private final int charismaModifier;
    @ApiModelProperty(notes = "The Base64 String of the avatar.png")
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
               ", Size of avatar in Base64=" + avatar.length() +
               '}';
    }
}
