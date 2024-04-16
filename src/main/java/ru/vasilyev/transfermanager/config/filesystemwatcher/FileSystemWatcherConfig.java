package ru.vasilyev.transfermanager.config.filesystemwatcher;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilyev.transfermanager.services.FileProcessService;

import java.io.File;
import java.time.Duration;

import static ru.vasilyev.transfermanager.constants.DirectoryPaths.PROCESS_PATH;

@Configuration
@Slf4j
public class FileSystemWatcherConfig {

    private final FileProcessService fileProcessService;

    @Autowired
    public FileSystemWatcherConfig(FileProcessService fileProcessService) {
        this.fileProcessService = fileProcessService;
    }

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        File file1 = new File(PROCESS_PATH);
        log.info(file1.getAbsolutePath());
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofSeconds(5), Duration.ofSeconds(4));
        fileSystemWatcher.addSourceDirectory(new File(PROCESS_PATH));
        fileSystemWatcher.addListener(new CustomerAddFileChangeListener(fileProcessService));
        fileSystemWatcher.start();
        log.info("FileSystemWatcher инициализирован и готов к работы");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy(){
        log.info("Shutting Down File System Watcher.");
        fileSystemWatcher().stop();
    }
}
