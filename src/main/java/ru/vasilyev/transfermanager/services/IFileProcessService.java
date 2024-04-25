package ru.vasilyev.transfermanager.services;

/**
 * todo Внедрение паттернов: command, chains of responsibility,
 * todo Соблюдение принципа open close principle
 */
public interface IFileProcessService {

    /**
     * Метод, который будет обрабатывать целевые файлы
     *
     * @param fileName - файл, пришедший на вход, готовый к проверке и обработке
     * @throws Exception - ошибки при обработке расширения файла
     */
    void processFile(String fileName) throws Exception;

    /**
     * Метод, который возвращает тип файла и будет обрабатывать сервис-обработчик-имплементация
     *
     *
     */
    String getExtension();
}
