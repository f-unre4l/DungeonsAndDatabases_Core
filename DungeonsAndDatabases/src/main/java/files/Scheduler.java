package files;

import files.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class Scheduler {
    @Autowired
    HeroRepository heroRepository;

    @Scheduled(cron = "0 */30 * ? * *")
    public void scheduledHeroBackup() {
        heroBackup();
    }

    @PreDestroy
    public void shutdownHeroBackup() {
        System.out.println("Shutting down");
        heroBackup();
    }

    private void heroBackup() {
        System.out.println("Starting Backup");
        ResponseEntity<Void> responseEntity = CsvExporter.backupAllHeroes(heroRepository);
        System.out.println(responseEntity);
    }
}
