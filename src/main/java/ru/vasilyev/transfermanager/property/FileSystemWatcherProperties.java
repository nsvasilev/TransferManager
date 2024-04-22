package ru.vasilyev.transfermanager.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;


/**
 * @param daemon - поток демона используется для отслеживания изменений TODO - неверно, прочитать, исправить, возможно даже переименовать с префиксом is
 * @param pollInterval - время ожидания между проверкой изменений
 * @param quietPeriod - количество времени, необходимое после обнаружения изменения, чтобы убедиться, что обновления завершены.
 * @param successPathDirectory - директория хранения файлов, прошедших валидацию
 * @param processPathDirectory - начальная директория для файлов
 * @param errorPathDirectory - директория для файлов, не прошедших валидацию
 */
@ConfigurationProperties(prefix = "application.file.watch")
public record FileSystemWatcherProperties(
        boolean daemon,
        Duration pollInterval,
        Duration quietPeriod,
        String successPathDirectory,
        String processPathDirectory,
        String errorPathDirectory) {
}
