package ru.vasilyev.transfermanager.services.fileProcessServices;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.file.attribute.FileTimes;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;
import ru.vasilyev.transfermanager.entity.UnexpectedExtensionFileEntity;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;
import ru.vasilyev.transfermanager.repostitory.UnexpectedExtensionFileRepository;
import ru.vasilyev.transfermanager.utils.FileMover;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;

/**
 * Этот сервис должен, во-первых, логировать приход и перемещение файла
 * Во-вторых, перемещение файла
 * В-третьих - сохранить сведения о файле в БД
 */
@Slf4j
@Service
public class UnexpectedExtensionFileProcessService extends AbstractFileProcessService{
    private UnexpectedExtensionFileEntity unexpectedExtensionFileEntity;
    private final FileSystemWatcherProperties fileSystemWatcherProperties;
    private final UnexpectedExtensionFileRepository unexpectedExtensionFileRepository;
    public UnexpectedExtensionFileProcessService(FileMover fileMover, FileSystemWatcherProperties fileSystemWatcherProperties, UnexpectedExtensionFileRepository unexpectedExtensionFileRepository) {
        super(fileMover);

        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
        this.unexpectedExtensionFileRepository = unexpectedExtensionFileRepository;
    }

    @Override
    public void processFile(File fileName) throws Exception {
    log.info("Поступил файл неподходящего расширения " + fileName);
        BasicFileAttributes basicFileAttributes = Files.readAttributes(Path.of(fileName.getPath()),BasicFileAttributes.class);
        FileTime fileTime = basicFileAttributes.creationTime(); //по задумке, это время создания файла
        long size = fileName.length();
        unexpectedExtensionFileEntity.setPathOfFile(fileName.getName());
        unexpectedExtensionFileEntity.setSize(size);
        //unexpectedExtensionFileEntity.setDateOfAppearance;
        fileMover.moveToTargetDirectory(fileName,fileSystemWatcherProperties.errorPathDirectory());
        unexpectedExtensionFileRepository.save(unexpectedExtensionFileEntity);
        log.info("файл " + fileName + "был перемещен в error");

    }

    @Override
    public String getExtension() {
        return "unexpected";
    }
}
