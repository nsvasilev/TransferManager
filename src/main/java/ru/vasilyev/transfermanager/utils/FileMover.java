package ru.vasilyev.transfermanager.utils;

import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class FileMover {
    private final FileSystemWatcherProperties fileSystemWatcherProperties;


    public FileMover(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    }


    public static void moveToSuccess (String fileName) throws IOException {
        Path path1 = Path.of("ts-files\\process\\" + fileName);
        Path path2 = Path.of("ts-files\\success\\" + fileName);
        Files.move(path1, path2);
    }
}
