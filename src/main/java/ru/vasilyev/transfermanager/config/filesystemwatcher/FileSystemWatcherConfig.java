package ru.vasilyev.transfermanager.config.filesystemwatcher;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.services.FileProcessService;
import java.io.File;

@Configuration
@Slf4j
@EnableConfigurationProperties(FileSystemWatcherProperties.class)
public class FileSystemWatcherConfig {

    private final FileProcessService fileProcessService;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    @Autowired
    public FileSystemWatcherConfig(FileProcessService fileProcessService, FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileProcessService = fileProcessService;

        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        File file1 = new File(fileSystemWatcherProperties.Process());
        log.info(file1.getAbsolutePath());
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(fileSystemWatcherProperties.daemon(),
                fileSystemWatcherProperties.pollInterval(),
                fileSystemWatcherProperties.quietPeriod());
        fileSystemWatcher.addSourceDirectory(new File(fileSystemWatcherProperties.Process()));
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
