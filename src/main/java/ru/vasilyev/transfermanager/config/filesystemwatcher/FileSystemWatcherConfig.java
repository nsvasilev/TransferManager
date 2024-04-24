package ru.vasilyev.transfermanager.config.filesystemwatcher;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.services.IFileProcessService;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Configuration
public class FileSystemWatcherConfig {

    private final Map<String, IFileProcessService> fileProcessServicesMap;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;

    @Autowired
    public FileSystemWatcherConfig(List<IFileProcessService> fileProcessServicesList, FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        fileProcessServicesMap = fileProcessServicesList.stream().collect(Collectors.toMap(IFileProcessService::getExtension, Function.identity()));
    }

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        File file1 = new File(fileSystemWatcherProperties.processPathDirectory());
        log.info(file1.getAbsolutePath());
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(
                fileSystemWatcherProperties.daemon(),
                fileSystemWatcherProperties.pollInterval(),
                fileSystemWatcherProperties.quietPeriod()
        );
        fileSystemWatcher.addSourceDirectory(new File(fileSystemWatcherProperties.processPathDirectory()));
        fileSystemWatcher.addListener(new CustomerAddFileChangeListener(fileProcessServicesMap));
        fileSystemWatcher.start();
        log.info("FileSystemWatcher conceived and ready to go");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() {
        log.info("Shutting Down File System Watcher.");
        fileSystemWatcher().stop();
    }
}
