package ru.vasilyev.transfermanager.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileValidator {


    private final FileParser fileParser;

    @Autowired
    public FileValidator(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public boolean checkFileStructure(String fileName){




        return true;
    }

    public boolean checkFileExtension(String fileName){



        return false;
    }




//    public void validateFile(String fileName) {
//        Path Directory = Paths.get(DIRECTORY_PATH + fileName);
//        log.info("Проверяем файл: " + fileName);
//        String s = fileName.split("\\.")[1];
//        log.info("Расширение файла: " + s);
//        if (fileName.endsWith("xlsx")) //заменили метод equals на endWish
//        {
//
//            //Внесли 2 конст. стринг переменные , использовали их в указании путей файлов success и error.
//            //Вынесли переменную directory_path под отдельную переменную за блок if (19 строка).
//            //Прописали блок else с перемещением файла в папку error.
//            try {
//                log.info("Файл нужного расширения.Проверяю на проверку структуры ");
////                exelParser.checkedStructure();
//                Path Success = Paths.get(SUCCESS_PATH + fileName);
//                Files.move(Directory, Success);
//            } catch (IOException e) {
//                System.out.println("ошибка ебаная");
//
//            }
//
//        } else {
//            try {
//                Path destPath = Paths.get(ERROR_PATH + fileName);
//                Files.move(Directory, destPath);
//                log.info("файл другого расширения,перенаправляю в error");
//                if (Files.exists(destPath)) {
//                    System.out.println("файл " + fileName + "поступил");
//                    clearError();//создали метод , очищающий директорию Error через каждые 30 секунд.
//                }
//
//            } catch (IOException e) {
//                System.out.println("ошибка в error");
//            }
//        }
//    }
//
//
//    //Метод должен срабатывать для каждого файла отдельно. Т.е. добавил один файл
//    //через 10 секунд добавил друой файл. Спустя 20 секунд удалится первый файл
//    //еще спустя 20 удалится второй файл.
//    public void clearError() throws IOException {
//        Timer timer;
//        timer = new Timer();
//        timer.schedule(new TimerTask() //использовали класс Time. Переопределили метод schedule.
//        {
//            @Override
//            public void run() {
//                try {
//                    Files.newDirectoryStream(Path.of(ERROR_PATH), Files::isRegularFile)
//                            .forEach(p -> {
//                                try {
//                                    Files.delete(p);
//                                    System.out.println("Файл был удален. Сработал clearError");
//                                } catch (IOException ignored) {
//                                }
//                            });
//
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        }, 30000);
//    }
}
