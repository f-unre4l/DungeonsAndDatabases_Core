package files.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Locale;
import java.util.UUID;

@Entity(name = "Hero")
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "race")
    private String race;

    @Column(name = "class")
    private String heroClass;

    public Hero() {

    }

    public Hero(
            String name, String race, String heroClass) {
        this.name = name;
        this.race = race;
        this.heroClass = heroClass;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Hero{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", race='" + race + '\'' +
               ", heroClass='" + heroClass + '\'' +
               '}';
    }
}
