package ru.vasilyev.transfermanager.config.filesystemwatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import ru.vasilyev.transfermanager.services.FileProcessService;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class CustomerAddFileChangeListener implements FileChangeListener {

    private final FileProcessService fileProcessService;

    public CustomerAddFileChangeListener(FileProcessService fileProcessService) {
        this.fileProcessService = fileProcessService;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles files : changeSet)
            for (ChangedFile file : files.getFiles())
                if (file.getType().equals(ChangedFile.Type.ADD)) {
                    try {
                        fileProcessService.processFile(file.getFile().getName());
                    } catch (IOException e) {
                        log.info("поймали ошибку!");
                    }
                }
    }
}
