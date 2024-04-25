package ru.vasilyev.transfermanager.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * TODO: - зачем тут fileSystemWatcherProperties если ты захардкодил пути
 * Зачем ьы захардкодил, если у тебя есть fileSystemWatcherProperties
 */
@Component
public class FileMover {
    public void moveToSuccess (File targetFile, File targetPath) throws IOException {
        Files.move(targetFile.toPath(), targetPath.toPath());
    }
}
