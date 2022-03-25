package files;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import files.model.entity.Hero;
import files.repository.HeroRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvExporter {
    public static ResponseEntity<Void> backupAllHeroes(HeroRepository heroRepository) {
        try {
            List<Hero> heroes = heroRepository.findAll();

            CsvMapper mapper = new CsvMapper();

            StringWriter stringWriter = new StringWriter();
            SequenceWriter sequenceWriter = mapper.writer().writeValues(stringWriter);

            for (Hero hero : heroes) {
                sequenceWriter.write(getValueFromHero(hero));
            }
            sequenceWriter.close();
            String heroCsv = stringWriter.toString();
            if (heroCsv.equals("")) {
                heroCsv = "empty";
            }
            HttpEntity<String> request = new HttpEntity<>(heroCsv);
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    "http://localhost:8079/api/v1/herobackup", request, String.class);

            if (! response.getStatusCode().is2xxSuccessful()) {
                return new ResponseEntity<>(null, response.getStatusCode());
            }
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static List<String> getValueFromHero(Hero hero) {
        List<String> tmp = Arrays.asList(hero.getId().toString(), hero.getName(), hero.getRace(), hero.getHeroClass());
        return tmp;
    }
}
