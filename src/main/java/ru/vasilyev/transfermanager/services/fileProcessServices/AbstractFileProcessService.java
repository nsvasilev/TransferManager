package ru.vasilyev.transfermanager.services.fileProcessServices;

import ru.vasilyev.transfermanager.services.IFileProcessService;
import ru.vasilyev.transfermanager.utils.FileMover;

/**
 * Сюда бы я вынес перемещение файлов с помощью протектед-методов, и бины, которые универсальные для всех типов FileProcessService
 * 1. В файл кастомере передавать не имя файла, а объект файла, тогда мы избежим кучу склеек по типу fileSystemWatcherProperties.processPathDirectory() + fileName
 * 2. Если мы это сделаем, то действительно у нас будет всего одит метод в fileMover - moveFileToTargetDirectory(File, fil)
 * 3. Тогда мы сможем в этот абстрактный класс внедрять через наследников универсальыне бины, и сюда вынести общие бины, они будут протектед
 * 4. Нужно будет вспомнить абстрактные методы, и модификатор protected.
 */
public class AbstractFileProcessService implements IFileProcessService {

    protected final FileMover fileMover;

    public AbstractFileProcessService(FileMover fileMover) {
        this.fileMover = fileMover;
    }


    @Override
    public void processFile(String fileName) throws Exception {

    }

    @Override
    public String getExtension() {
        return null;
    }
}
