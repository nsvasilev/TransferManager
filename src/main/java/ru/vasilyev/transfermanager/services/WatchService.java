package ru.vasilyev.transfermanager.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.component.FileValidator;

import java.io.IOException;
import java.nio.file.*;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.DIRECTORY_PATH;



@Service
@Slf4j
public class  WatchService {

    private final FileValidator fileValidator;
    private final FileProcessService fileProcessService;

    @Autowired
    public WatchService(FileValidator fileValidator, FileProcessService fileProcessService) {
        this.fileValidator = fileValidator;
        this.fileProcessService = fileProcessService;
    }

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

