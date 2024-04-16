package ru.vasilyev.transfermanager.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.time.Duration;


@ConfigurationProperties(prefix = "application.file.watch")
public  record FileSystemWatcherProperties(boolean daemon,
                                           Duration pollInterval,
                                           Duration quietPeriod,
                                           String Success,
                                           String Process,
                                           String Error)
{}
