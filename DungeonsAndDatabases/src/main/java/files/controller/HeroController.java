package files.controller;

import files.model.Hero;
import files.model.HeroClass;
import files.model.Race;
import files.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class HeroController {

    @Autowired
    HeroRepository heroRepository;

    @PostMapping("/heroes")
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        try {
            Hero _hero = heroRepository
                    .save(new Hero(hero.getName(), validateRace(hero.getRace()), validateHeroClass(hero.getHeroClass())
                    ));
            return new ResponseEntity<>(_hero, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/heroes")
    public ResponseEntity<HttpStatus> deleteAllHeros() {
        try {
            heroRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id") UUID id) {
        try {
            heroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getAllHeros(@RequestParam(required = false) String name) {
        try {
            List<Hero> heros = new ArrayList<Hero>();

            if (name == null) {
                heros.addAll(heroRepository.findAll());
            } else {
                heros.addAll(heroRepository.findByNameContaining(name));
            }

            if (heros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(heros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/heroes/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") UUID id) {
        Optional<Hero> heroData = heroRepository.findById(id);

        return heroData.map(hero -> new ResponseEntity<>(hero, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/heroes/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id") UUID id, @RequestBody Hero hero) {
        Optional<Hero> heroData = heroRepository.findById(id);

        if (heroData.isPresent()) {
            Hero _hero = heroData.get();
            _hero.setName(hero.getName());
            _hero.setRace(hero.getRace());
            _hero.setHeroClass(hero.getHeroClass());
            return new ResponseEntity<>(heroRepository.save(_hero), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private String validateHeroClass(String heroClass) {
        return HeroClass.valueOf(heroClass.toUpperCase()).getValidHeroClass();
    }

    private String validateRace(String race) throws IllegalArgumentException {
        return Race.valueOf(race.toUpperCase(Locale.ROOT)).getValidRace();
    }
}
