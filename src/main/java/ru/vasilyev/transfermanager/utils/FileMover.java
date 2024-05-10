package ru.vasilyev.transfermanager.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * FileMover - класс, отвечающий за перемещение файлов.
 */

@Component
public class FileMover {
    public void moveToTargetDirectory(File targetFile, File targetPath) throws IOException {

        Files.move(targetFile.toPath(), Path.of(targetPath+ UUID.randomUUID().toString()+targetFile.getName()));
    }



}
