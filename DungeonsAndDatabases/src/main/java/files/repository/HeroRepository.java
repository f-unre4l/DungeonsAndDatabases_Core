package files.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import files.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByRace(String race);

    List<Hero> findByNameContaining(String name);
}

