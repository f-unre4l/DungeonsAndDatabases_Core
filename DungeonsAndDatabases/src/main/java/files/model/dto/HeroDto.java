package files.model.dto;

import files.model.entity.Hero;

import java.util.UUID;

public class HeroDto extends HeroCreationDto {

    UUID id;

    public HeroDto(Hero heroBase, HeroStatsDto stats) {
        super(
                heroBase.getName(), heroBase.getRace(), heroBase.getHeroClass(), stats.getStrength(), stats.getDexterity(), stats.getConstitution(),
                stats.getWisdom(), stats.getIntelligence(), stats.getCharisma(), stats.getExperience());
        this.id = heroBase.getId();

    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "HeroDto{" +
               "id=" + id +
               super.toString() +
               '}';
    }
}
