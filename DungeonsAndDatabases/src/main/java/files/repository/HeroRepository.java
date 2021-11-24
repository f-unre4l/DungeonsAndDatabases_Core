package files.repository;

import files.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HeroRepository extends JpaRepository<Hero, UUID> {
    List<Hero> findByNameContaining(String name);
}

