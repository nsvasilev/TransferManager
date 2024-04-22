package ru.vasilyev.transfermanager.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;


@Slf4j
@Component
public class ErrorHandler  {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;
    public ErrorHandler(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }
    public void clearError(String fileName) {
        Timer timer;
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
                try {
                    Files.newDirectoryStream(Path.of(fileSystemWatcherProperties.errorPathDirectory()), Files::isRegularFile)
                            .forEach(p -> {
                                try {
                                    Files.delete(p);
                                    log.info("Файл" + fileName + "был удален. Сработал clearError");
                                } catch (IOException ignored) {
                                    log.info("ошибка при удалении файла");
                                }
                            });

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, 30);
    }
}
