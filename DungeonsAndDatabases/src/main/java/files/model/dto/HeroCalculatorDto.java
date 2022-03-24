package files.model.dto;

import files.services.CalculatorService;

public class HeroCalculatorDto {

    private int level;
    private int strengthModifier;
    private int dexterityModifier;
    private int constitutionModifier;
    private int wisdomModifier;
    private int intelligenceModifier;
    private int charismaModifier;

    public HeroCalculatorDto(
            int level, int strengthModifier, int dexterityModifier, int constitutionModifier, int wisdomModifier, int intelligenceModifier,
            int charismaModifier) {
        this.level = level;
        this.strengthModifier = strengthModifier;
        this.dexterityModifier = dexterityModifier;
        this.constitutionModifier = constitutionModifier;
        this.wisdomModifier = wisdomModifier;
        this.intelligenceModifier = intelligenceModifier;
        this.charismaModifier = charismaModifier;
    }

    public HeroCalculatorDto() {
    }

    public static HeroCalculatorDto getHeroCalculatorDtoFromStats(HeroStatsDto heroStats) {
        return new HeroCalculatorDto(
                CalculatorService.calculateLevel(heroStats),
                CalculatorService.calculateStatModifier(heroStats.getStrength()),
                CalculatorService.calculateStatModifier(heroStats.getDexterity()),
                CalculatorService.calculateStatModifier(heroStats.getConstitution()),
                CalculatorService.calculateStatModifier(heroStats.getWisdom()),
                CalculatorService.calculateStatModifier(heroStats.getIntelligence()),
                CalculatorService.calculateStatModifier(heroStats.getCharisma()));
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
        return "HeroCalculatorDto{" +
               "level=" + level +
               ", strengthModifier=" + strengthModifier +
               ", dexterityModifier=" + dexterityModifier +
               ", constitutionModifier=" + constitutionModifier +
               ", wisdomModifier=" + wisdomModifier +
               ", intelligenceModifier=" + intelligenceModifier +
               ", charismaModifier=" + charismaModifier +
               '}';
    }
}
