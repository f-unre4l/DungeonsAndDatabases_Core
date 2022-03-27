package files.services;

import files.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    HeroRepository heroRepository;

    @Scheduled(cron = "0 */30 * ? * *")
    public void scheduledHeroBackup() {
        heroBackup();
    }

    @PreDestroy
    public void shutdownHeroBackup() {
        logger.info("Shutting down");
        heroBackup();
    }

    private void heroBackup() {
        logger.info("Starting Backup");
        CsvExporterService.backupAllHeroes(heroRepository);
    }
}
