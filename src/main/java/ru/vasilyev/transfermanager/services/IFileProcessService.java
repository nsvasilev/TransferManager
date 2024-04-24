package ru.vasilyev.transfermanager.services;

/**
 * todo Внедрение паттернов: command, chains of responsibility,
 * todo Соблюдение принципа open close principle
 */
public interface IFileProcessService {

    /**
     * Метод, который будет обрабатывать целевые файлы
     *
     * @param fileName
     * @throws Exception
     */
    void processFile(String fileName) throws Exception;

    /**
     * Метод, который возвращает тип файла и будет обрабатывать сервис-обработчик-имплементация
     *
     * @return
     */
    String getExtension();
}
