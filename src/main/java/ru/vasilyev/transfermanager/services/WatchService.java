package ru.vasilyev.transfermanager.services;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

import static ru.vasilyev.transfermanager.constants.ProjectConstants.DIRECTORY_PATH;



@Service
public class WatchService {


    /**
     * Сервис, который следит за изменениями директории
     */
    @PostConstruct
    @Async
    public void watchService() throws IOException, InterruptedException {
        java.nio.file.WatchService watchService
                = FileSystems.getDefault().newWatchService();

        //Здесь мы меняем путь
        Path path = Paths.get(DIRECTORY_PATH);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }

}

