package ru.vasilyev.transfermanager.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;


/**
 * ПОЛЯ С МАЛЕНЬКОЙ БУКВЫ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * СЛОВО ПРОЦЕСС СКАКСЕС И ЕРОР НЕ ПРЕДОСТАВЛЧЮТ НАМ ИНФОРМАЦИЮ, ЧТО ХНАРИТСЯ В ПОЛЕ.
 * НУЖНО ПЕРЕИМЕНОВАТЬ В successPathDirectory
 *
 * @param daemon TODO: описать параметры
 * @param pollInterval
 * @param quietPeriod
 * @param Success
 * @param Process
 * @param Error
 */
@ConfigurationProperties(prefix = "application.file.watch")
public record FileSystemWatcherProperties(
        boolean daemon,
        Duration pollInterval,
        Duration quietPeriod,
        String Success,
        String Process,
        String Error) {
}
