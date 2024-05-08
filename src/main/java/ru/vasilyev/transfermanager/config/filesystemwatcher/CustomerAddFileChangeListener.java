package ru.vasilyev.transfermanager.config.filesystemwatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import ru.vasilyev.transfermanager.services.IFileProcessService;

import java.io.File;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CustomerAddFileChangeListener implements FileChangeListener {
    private final Map<String, IFileProcessService> fileProcessServicesMap;
    //создаем филд мапу.
    public CustomerAddFileChangeListener(Map<String, IFileProcessService> fileProcessServicesMap) {
        this.fileProcessServicesMap = fileProcessServicesMap;
    } // создаем конструктор

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles files : changeSet)
            for (ChangedFile file : files.getFiles())
                if (file.getType().equals(ChangedFile.Type.ADD)) {
                    try {
                        /*
                          TODO: обработать ситуацию, когда приходит файл, с неизвестным нам расширением.
                           То есть мы не умеем его обрабатывать.
                           Дать знать об этом пользователю, то есть выкинуть ошибку, залогировать её и обработать
                         */
                        String name = file.getFile().getName();
                        File file1 = file.getFile();
                        String[] split = name.split("\\.");
                        fileProcessServicesMap.get(split[split.length - 1]).processFile(file1); //todo последняя ошибка была тут. Скрин есть
                    } catch (Exception e) {
                        log.info("ошибка при обработке файла" + e.getMessage());
                    }
                }
    }
}
