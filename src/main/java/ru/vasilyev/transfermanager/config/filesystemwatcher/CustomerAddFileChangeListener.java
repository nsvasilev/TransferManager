package ru.vasilyev.transfermanager.config.filesystemwatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import ru.vasilyev.transfermanager.services.IFileProcessService;

import java.util.Map;
import java.util.Set;

@Slf4j
public class CustomerAddFileChangeListener implements FileChangeListener {
    private final Map<String, IFileProcessService> fileProcessServicesMap;

    public CustomerAddFileChangeListener(Map<String, IFileProcessService> fileProcessServicesMap) {
        this.fileProcessServicesMap = fileProcessServicesMap;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles files : changeSet)
            for (ChangedFile file : files.getFiles())
                if (file.getType().equals(ChangedFile.Type.ADD)) {
                    try {
                        String name = file.getFile().getName();
                        String[] split = name.split("\\.");
                        fileProcessServicesMap.get(split[split.length - 1]).processFile(name);
                    } catch (Exception e) {
                        log.info("ошибка при обработке файла" + e.getMessage());
                    }
                }
    }
}
