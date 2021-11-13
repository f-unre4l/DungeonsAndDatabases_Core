package files.controller;

import files.model.Hero;
import files.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                    .save(new Hero(hero.getName(), hero.getRace(), hero.getHeroClass(), hero.getStrength(), hero.getDexterity(), hero.getConstitution(),
                                   hero.getWisdom(), hero.getIntelligence(), hero.getCharisma(), hero.getExperience()));
            return new ResponseEntity<>(_hero, HttpStatus.CREATED);
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
    public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id") long id) {
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
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") long id) {
        Optional<Hero> heroData = heroRepository.findById(id);

        return heroData.map(hero -> new ResponseEntity<>(hero, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/heroes/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id") long id, @RequestBody Hero hero) {
        Optional<Hero> heroData = heroRepository.findById(id);

        if (heroData.isPresent()) {
            Hero _hero = heroData.get();
            _hero.setName(hero.getName());
            _hero.setRace(hero.getRace());
            _hero.setHeroClass(hero.getHeroClass());
            _hero.setStrength(hero.getStrength());
            _hero.setDexterity(hero.getDexterity());
            _hero.setConstitution(hero.getConstitution());
            _hero.setWisdom(hero.getWisdom());
            _hero.setIntelligence(hero.getIntelligence());
            _hero.setCharisma(hero.getCharisma());
            _hero.setExperience(hero.getExperience());
            return new ResponseEntity<>(heroRepository.save(_hero), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}