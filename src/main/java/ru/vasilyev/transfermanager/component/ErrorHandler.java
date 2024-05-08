package ru.vasilyev.transfermanager.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vasilyev.transfermanager.property.FileSystemWatcherProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;

/**
 * класс ErrorHandler предназначен для очистки директории Error,
 * после постулпения туда файлов
 * На данный момент класс не является рабочим
 *
 */
@Slf4j
@Component
public class ErrorHandler  {
    private final FileSystemWatcherProperties fileSystemWatcherProperties; // добавляем филд
    public ErrorHandler(FileSystemWatcherProperties fileSystemWatcherProperties) {
        this.fileSystemWatcherProperties = fileSystemWatcherProperties;
    } // создаем конструктор с этим филдом

    /**
     * создается метод clearError, принимающий в параметрах название файла
     * необходимо доработать метод, он не работает
     */
    public void clearError(String fileName){
        Timer timer;
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override //переопеделение метода run.
            public void run() {
                try {
                    Files.newDirectoryStream(fileSystemWatcherProperties.errorPathDirectory().toPath(), Files::isRegularFile)
                            // происходит перебор всех файлов в директории Error.
                            //ниже указана процедура удаления файла из директории
                            .forEach(p -> {
                                try {
                                    Files.delete(p);
                                    log.info("Файл" + fileName + "был удален. Сработал clearError");
                                } catch (IOException ignored) {
                                    log.info("ошибка при удалении файла");
                                }
                            });

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, 30);
    }
}
