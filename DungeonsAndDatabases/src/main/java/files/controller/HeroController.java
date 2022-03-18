package files.controller;

import files.model.*;
import files.model.dto.HeroCreationDto;
import files.model.dto.HeroDto;
import files.model.dto.HeroStatsDto;
import files.model.entity.Hero;
import files.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class HeroController {

    @Autowired
    HeroRepository heroRepository;

    @PostMapping("/heroes")
    public ResponseEntity<Hero> createHero(@RequestBody HeroCreationDto heroCreationDto) {
        try {
            Hero _hero = heroRepository.save(
                    new Hero(
                            heroCreationDto.getName(),
                            validateRace(heroCreationDto.getRace()),
                            validateHeroClass(heroCreationDto.getHeroClass())
                    ));
            ResponseEntity<Hero> response = new ResponseEntity<>(_hero, HttpStatus.CREATED);
            HttpEntity<HeroStatsDto> request = new HttpEntity<>(HeroStatsDto.getHeroStatsFromHero(heroCreationDto, Objects.requireNonNull(response.getBody()).getId()));
            ResponseEntity<HeroStatsDto> responseStats = new RestTemplate().postForEntity("http://localhost:8079/api/v1/herostats", request, HeroStatsDto.class);
            if (! responseStats.getStatusCode().is2xxSuccessful()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
            }
            return response;
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/heroes")
    public ResponseEntity<HttpStatus> deleteAllHeros() {
        try {
            heroRepository.deleteAll();

            HttpResponse<String> response = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder().DELETE().uri(new URI("http://localhost:8079/api/v1/herostats")).build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            if (! HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id") UUID id) {
        try {
            heroRepository.deleteById(id);

            HttpResponse<String> response = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder().DELETE().uri(new URI("http://localhost:8079/api/v1/herostats/" + id)).build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            if (! HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getAllHeros(@RequestParam(required = false) String name) {
        try {
            List<Hero> heros = new ArrayList<>();

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
    public ResponseEntity<HeroDto> getHeroById(@PathVariable("id") UUID id) {
        Optional<Hero> heroData = heroRepository.findById(id);

        ResponseEntity<HeroStatsDto> responseStats = new RestTemplate().getForEntity("http://localhost:8079/api/v1/herostats/{id}", HeroStatsDto.class, id);
        if (! responseStats.getStatusCode().is2xxSuccessful()){
            return new ResponseEntity<>(null, responseStats.getStatusCode());
        }
        Optional<HeroDto> heroDto = heroData.map(hero -> new HeroDto(hero, Objects.requireNonNull(responseStats.getBody())));

        return heroDto.map(hero -> new ResponseEntity<>(hero, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //TODO evtl. später anpassen, nicht mandatory.
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
