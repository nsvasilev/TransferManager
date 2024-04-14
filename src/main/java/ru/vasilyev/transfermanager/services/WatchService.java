package ru.vasilyev.transfermanager.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.beans.WatchServiceConfiguration;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Service
public class WatchService {

    private final WatchServiceConfiguration watchServiceConfiguration;

    @Autowired
    public WatchService(WatchServiceConfiguration watchServiceConfiguration) {
        this.watchServiceConfiguration = watchServiceConfiguration;
    }

    @PostConstruct
    public void runWatchService() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Thread watchServiceThread = new Thread(() -> {
            try {
                watchServiceConfiguration.watchService();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        watchServiceThread.setDaemon(true);
        log.info("Сетим нашему потоку ватчсервис");
        executorService.submit(watchServiceThread);
        log.info("Запустили ватчсервис");
    }
}

