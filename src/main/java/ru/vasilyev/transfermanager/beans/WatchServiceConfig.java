package ru.vasilyev.transfermanager.beans;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

import static ru.vasilyev.transfermanager.constants.ProjectConstants.DIRECTORY_PATH;


/*
    Посмотри иерархия аннотаций компонент
     component -> Repository, Service, Configuration, Controller
        Убрать компонент
 */
@Configuration
@Component
public class WatchServiceConfig {

    @Value("${directory.path}")
    private String directoryPath;

    /**
     * Сервис, который следит за изменениями директории
     */
    @PostConstruct
    @Async
    public void watchService() throws IOException, InterruptedException {
        WatchService watchService
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

