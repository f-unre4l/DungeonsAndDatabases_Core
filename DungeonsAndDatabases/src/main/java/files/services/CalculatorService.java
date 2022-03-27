package files.services;

import files.model.dto.HeroCreationDto;
import files.model.dto.HeroStatsDto;
import files.model.enums.HeroClass;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculatorService {
    public static int calculateHitpoints(HeroCreationDto hero, boolean randomHitpoints) {
        String hitpointCalculationVariant = "hitpointsstandard";
        if (randomHitpoints) {
            hitpointCalculationVariant = "hitpointsrandom";
        }
        int exp = hero.getExperience();
        int hitDie = HeroClass.valueOf(hero.getHeroClass().toUpperCase()).getHitDie();
        int constitution = hero.getConstitution();

        String url = "http://localhost:8078/api/v1/calculator/" + hitpointCalculationVariant + "/" + exp + "/" + hitDie + "/" + constitution;
        return intResponse(url);
    }

    public static int calculateLevel(HeroStatsDto heroStats) {
        int exp = heroStats.getExperience();

        String url = "http://localhost:8078/api/v1/calculator/level/" + exp;
        return intResponse(url);
    }

    public static int calculateStatModifier(int stat) {
        String url = "http://localhost:8078/api/v1/calculator/statmodifier/" + stat;
        return intResponse(url);
    }

    private static int intResponse(String url) {
        ResponseEntity<Integer> responseHitpoints = new RestTemplate().getForEntity(
                url, Integer.class);

        if (responseHitpoints.getBody() == null) {
            throw new NullPointerException();
        }
        if (! responseHitpoints.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException();
        }
        return responseHitpoints.getBody();
    }
}
