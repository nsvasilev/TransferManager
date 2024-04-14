package ru.vasilyev.transfermanager.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.vasilyev.transfermanager.services.FileProcessService;

import java.io.IOException;
import java.nio.file.*;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;

@Configuration
@Slf4j
public class WatchServiceConfiguration {

    private final FileProcessService fileProcessService;

    @Autowired
    public WatchServiceConfiguration(FileProcessService fileProcessService) {
        this.fileProcessService = fileProcessService;
    }

    /**
     * Сервис, который следит за изменениями директории
     */
    public void watchService() throws IOException, InterruptedException {
        java.nio.file.WatchService watchService
                = FileSystems.getDefault().newWatchService();

        //Здесь мы меняем путь
        Path path = Paths.get(PROCESS_PATH);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE
        );

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                log.info("На вход пришёл файл: " + event.context());
                fileProcessService.processFile(event.context().toString());
            }
            key.reset();
        }
    }
}
