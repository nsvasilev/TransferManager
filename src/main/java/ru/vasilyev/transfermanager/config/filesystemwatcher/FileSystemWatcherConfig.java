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

/**
 * FileSystemWatcherConfig - класс, необходимый для проверки директории на изменения внутри нее
 */

@Slf4j
@Configuration
public class FileSystemWatcherConfig {

    private final Map<String, IFileProcessService> fileProcessServicesMap;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;
    // обозначаем необходимые филды и сразу вписываем их через конструктор ниже.
    @Autowired
    public FileSystemWatcherConfig(List<IFileProcessService> fileProcessServicesList, FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        fileProcessServicesMap = fileProcessServicesList.stream().collect(Collectors.toMap(IFileProcessService::getExtension, Function.identity()));
    }

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        File file1 = (fileSystemWatcherProperties.processPathDirectory());
        log.info(file1.getAbsolutePath()); //todo что за лог?
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(
                fileSystemWatcherProperties.daemon(), //демон поток для отслеживания изменений
                fileSystemWatcherProperties.pollInterval(), //время ожидания между проверкой изменений
                fileSystemWatcherProperties.quietPeriod() //количество времени, необходимое после обнаружения изменения, чтобы убедиться, что обновления завершены.
        ); //создаем FileSystemWatcher
        fileSystemWatcher.addSourceDirectory((fileSystemWatcherProperties.processPathDirectory())); //добавляем директорию проверки
        fileSystemWatcher.addListener(new CustomerAddFileChangeListener(fileProcessServicesMap));
        fileSystemWatcher.start();
        log.info("FileSystemWatcher conceived and ready to go");
        return fileSystemWatcher;
    }

    /**
     * ниже метод, который срабатывает после отработки бина
     */
    @PreDestroy
    public void onDestroy() {
        log.info("Shutting Down File System Watcher.");
        fileSystemWatcher().stop(); //происходит остановка
    }
}
