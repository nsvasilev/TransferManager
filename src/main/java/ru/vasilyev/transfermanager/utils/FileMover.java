package ru.vasilyev.transfermanager.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/**
 * FileMover - класс, отвечающий за перемещение файлов.
 */

/**
 * TODO: - зачем тут fileSystemWatcherProperties если ты захардкодил пути
 * Зачем ьы захардкодил, если у тебя есть fileSystemWatcherProperties
 */
@Component
public class FileMover {
    public void moveToTargetDirectory(File targetFile, File targetPath) throws IOException {

        Files.move(targetFile.toPath(), targetPath.toPath());
    }



}
