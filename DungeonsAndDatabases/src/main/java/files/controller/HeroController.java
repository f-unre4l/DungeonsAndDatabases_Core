package files.controller;

import files.model.dto.HeroCalculatorDto;
import files.model.dto.HeroCreationDto;
import files.model.dto.HeroDto;
import files.model.dto.HeroStatsDto;
import files.model.entity.Hero;
import files.model.enums.HeroClass;
import files.model.enums.HeroRace;
import files.repository.HeroRepository;
import files.services.CalculatorService;
import files.services.ExternalAPIService;
import files.services.LoggingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
@RequestMapping("/api/v1/heroes")
@Tag(name = "hero-controller", description = "Creates and manages all heroes in the project")
public class HeroController {

    Logger logger = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    HeroRepository heroRepository;

    @PostMapping("")
    @ApiOperation(value = "\"createHero\" adds a hero to the storage/s",
            notes = "Adds the information you provide about a new heroes to the storage")
    public ResponseEntity<Hero> createHero(
            @RequestBody HeroCreationDto heroCreationDto,
            @ApiParam(value = "Set this to true if you want the hitpoints of your hero to be rolled with dice "
                              + "instead of getting the average") @RequestParam boolean randomHitpoints) {
        try {
            Hero _hero = heroRepository.save(createHeroFromDto(heroCreationDto));
            ResponseEntity<Hero> response = new ResponseEntity<>(_hero, HttpStatus.CREATED);

            int hitpoints = CalculatorService.calculateHitpoints(heroCreationDto, randomHitpoints);

            HeroStatsDto heroStatsFromHero = HeroStatsDto.getHeroStatsFromHero(heroCreationDto, Objects.requireNonNull(response.getBody()).getId(), hitpoints);
            HttpEntity<HeroStatsDto> request = new HttpEntity<>(heroStatsFromHero);
            ResponseEntity<HeroStatsDto> responseStats = new RestTemplate().postForEntity(
                    "http://localhost:8079/api/v1/herostats", request, HeroStatsDto.class);
            if (! responseStats.getStatusCode().is2xxSuccessful()) {
                LoggingService.logWarnRequest(logger, responseStats.getStatusCode());
                return new ResponseEntity<>(null, responseStats.getStatusCode());
            }
            LoggingService.logInfoSuccess(logger, HttpMethod.POST, response.getBody());
            return response;
        } catch (IllegalArgumentException e) {
            LoggingService.logError(logger, e, "The race or class of the hero is not valid.");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (NullPointerException e) {
            LoggingService.logError(logger, e, "Some necessary parameter were missing.");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    @DeleteMapping("")
    @ApiOperation(value = "\"deleteAllHeros\" deletes all heroes",
            notes = "Empties all the storages")
    public ResponseEntity<HttpStatus> deleteAllHeros() {
        try {
            heroRepository.deleteAll();

            HttpResponse<String> response = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder().DELETE().uri(new URI("http://localhost:8079/api/v1/herostats")).build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            if (! HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
                LoggingService.logWarnRequest(logger, HttpStatus.valueOf(response.statusCode()));
                return new ResponseEntity<>(null, HttpStatus.valueOf(response.statusCode()));
            }
            LoggingService.logInfoSuccess(logger, HttpMethod.DELETE, "all heroes");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "\"deleteHero\" deletes a specific hero",
            notes = "Deletes specific hero from all storages")
    public ResponseEntity<HttpStatus> deleteHero(@ApiParam(value = "UUID of the hero you want to delete") @PathVariable("uuid") UUID uuid) {
        try {
            heroRepository.deleteById(uuid);

            HttpResponse<String> response = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder().DELETE().uri(new URI("http://localhost:8079/api/v1/herostats/" + uuid)).build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            if (! HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
                LoggingService.logWarnRequest(logger, HttpStatus.valueOf(response.statusCode()));
                return new ResponseEntity<>(null, HttpStatus.valueOf(response.statusCode()));
            }
            LoggingService.logInfoSuccess(logger, HttpMethod.DELETE, "hero with uuid - " + uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    @GetMapping("")
    @ApiOperation(value = "\"getAllHeros\" returns all heroes",
            notes = "Returns the basic information about all the heroes in the storage")
    public ResponseEntity<List<Hero>> getAllHeros() {
        try {
            List<Hero> heroes = new ArrayList<>(heroRepository.findAll());

            if (heroes.isEmpty()) {
                LoggingService.logWarnRequest(logger, HttpStatus.NO_CONTENT);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LoggingService.logInfoSuccess(logger, HttpMethod.GET, heroes);
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (Exception e) {
            return handleError(e);
        }
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "\"getHeroById\" returns details about a specific hero",
            notes = "Returns all the information about the specific hero your're looking for")
    public ResponseEntity<HeroDto> getHeroById(@ApiParam(value = "UUID of the hero you want more information on") @PathVariable("uuid") UUID uuid) {
        Optional<Hero> heroData = heroRepository.findById(uuid);

        ResponseEntity<HeroStatsDto> responseStats = new RestTemplate().getForEntity("http://localhost:8079/api/v1/herostats/{uuid}", HeroStatsDto.class, uuid);
        if (! responseStats.getStatusCode().is2xxSuccessful()) {
            LoggingService.logWarnRequest(logger, responseStats.getStatusCode());
            return new ResponseEntity<>(null, responseStats.getStatusCode());
        }
        HeroStatsDto stats = Objects.requireNonNull(responseStats.getBody());
        HeroCalculatorDto heroCalculationDto = HeroCalculatorDto.getHeroCalculatorDtoFromStats(stats);
        Optional<HeroDto> heroDto = heroData.map(hero -> new HeroDto(hero, stats, heroCalculationDto, ExternalAPIService.generateHeroAvatar(
                heroData.get(),
                stats,
                heroCalculationDto)));
        ResponseEntity<HeroDto> response = heroDto.map(hero -> new ResponseEntity<>(hero, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        LoggingService.logInfoSuccess(logger, HttpMethod.GET, response.getBody());
        return response;
    }

    private Hero createHeroFromDto(@RequestBody HeroCreationDto heroCreationDto) throws IllegalArgumentException, NullPointerException {
        return new Hero(
                heroCreationDto.getName(),
                validateRace(heroCreationDto.getRace()),
                validateHeroClass(heroCreationDto.getHeroClass())
        );
    }

    private <T> ResponseEntity<T> handleError(Exception e) {
        LoggingService.logError(logger, e);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String validateHeroClass(String heroClass) throws IllegalArgumentException, NullPointerException {
        return HeroClass.valueOf(heroClass.toUpperCase()).getValidHeroClass();
    }

    private String validateRace(String race) throws IllegalArgumentException, NullPointerException {
        return HeroRace.valueOf(race.toUpperCase(Locale.ROOT)).getName();
    }
}
