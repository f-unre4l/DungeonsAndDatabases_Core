package files.services;

import files.model.dto.HeroCalculatorDto;
import files.model.dto.HeroStatsDto;
import files.model.entity.Hero;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ExternalAPIService {
    private static String[] availableHeadTypes() {
        return new String[]{
                "afro", "bangs", "bangs2", "bantuknots", "bun", "cornrows", "cornrows2", "graybun", "graymedium", "long",
                "longbangs", "longcurly", "medium1", "medium2", "medium3", "mediumbangs2", "mediumbangs3", "flattop", "flattoplong", "grayshort", "mohawk",
                "nohair2", "nohair3", "pomp", "shaved2", "shaved3", "short1", "short2", "short4", "short5", "buns",
                "hat-beanie", "hat-hip", "longafro", "mediumbangs", "mediumstraight", "mohawk2", "nohair1", "shaved1", "short3", "twists", "twists2", "bear",
                "hijab", "turban"};
    }

    public static String generateHeroAvatar(Hero hero, HeroStatsDto heroStats, HeroCalculatorDto heroCalculation) {
        String url = "https://avatar-endpoint.herokuapp.com/api/";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                                                           // Add query parameter
                                                           .queryParam("size", "400")
                                                           .queryParam("img_type", "png")
                                                           .queryParam("head_type", getHeadType(heroStats))
                                                           .queryParam("face_type", getFaceType(hero.getRace()))
                                                           .queryParam("shrink", getShrink(heroCalculation.getLevel()))
                                                           .queryParam("bg_color", getBgColor(hero.getHeroClass()))
                                                           .queryParam("head_color", getHeadColor(hero.getName()));
        URI uri = builder.buildAndExpand().toUri();

        byte[] imageBytes = new RestTemplate().getForObject(uri, byte[].class);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static String getBgColor(String heroClass) {
        Map<String, String> map = heroClassToBgColorMap();

        return map.get(heroClass);
    }

    private static String getFaceType(String race) {
        Map<String, String> map = raceToFaceTypeMap();

        return map.get(race);
    }

    private static String getHeadColor(String name) {
        int nameHashCode = name.hashCode();

        Random random = new Random();
        random.setSeed(nameHashCode);
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color headColor = new Color(red, green, blue);

        return String.format("#%02x%02x%02x", headColor.getRed(), headColor.getGreen(), headColor.getBlue());
    }

    private static String getHeadType(HeroStatsDto stats) {
        String[] headTypes = availableHeadTypes();
        int statsHashCode = getSumOfHeroStats(stats);

        Random random = new Random();
        random.setSeed(statsHashCode);
        int headIndex = random.nextInt(45);
        return headTypes[headIndex];
    }

    private static double getShrink(int level) {
        double shrink = 0.8;
        if (level != 1) {
            shrink = shrink - (shrink / 20) * level;
        }
        return shrink;
    }

    private static int getSumOfHeroStats(HeroStatsDto stats) {
        return stats.getStrength() + stats.getDexterity() + stats.getConstitution() + stats.getWisdom() + stats.getIntelligence() + stats.getCharisma();
    }

    private static Map<String, String> heroClassToBgColorMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Artificer", "#233b47");
        map.put("Barbarian", "#ffa3ae");
        map.put("Bard", "#82e5e4");
        map.put("Cleric", "#cbe2e2");
        map.put("Druid", "#d0926d");
        map.put("Fighter", "#fdbfa0");
        map.put("Monk", "#ffe2ab");
        map.put("Paladin", "#99e3fc");
        map.put("Ranger", "#8de5a8");
        map.put("Rogue", "#91abc2");
        map.put("Sorcerer", "#ffb8fb");
        map.put("Warlock", "#bcabfa");
        map.put("Wizard", "#67b6fa");
        return map;
    }

    private static Map<String, String> raceToFaceTypeMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Dragonborn", "monster");
        map.put("Dwarf", "eyesclosed");
        map.put("Elf", "contempt");
        map.put("Gnome", "cute");
        map.put("Halfelf", "concerned");
        map.put("Halforc", "angrywithfang");
        map.put("Halfling", "calm");
        map.put("Human", "driven");
        map.put("Tiefling", "suspicious");
        return map;
    }
}
