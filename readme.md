<h1>ТранфсерМенеджер - сервис обработки и парсинга поступающих файлов с последующей агрегацией.</h1>

<ul>1. Обработка  - приём файла и его складирование (process, success - здесь будет лежать файл после успешной обработки, error - если файл не подходящего формата)</ul>
   <ul> 1.1 Не подходящий формат - это любой файл не .xlsx и .csv, а также который не соблюдает структуру из пункта 2.2 (ФИО, +7xxx, Город, подписан на рассылку)</ul>
<ul>2. Парсинг - считывание данных и преобразования в целевую структуру данных - </ul>
  <ul> 2.1 Сохранить сведения о файле(id, название файла, расширение, дата создания)[1 таблица]</ul>
   <ul>2.2 Данные в файле, которые ссылаются на сам файл(id, ФИО, +7xxx, Город, подписан на рассылку, внешний ключ на исходный файл)</ul>


(После написания кода сохранения в базу. Пока забудь)

3. Агрегацией. Сколько людей в городе Москва подписаны на рассылку? Сколько в базе Никит? - по запросу
4. VAADIN

ПОСТРГ в докере -

1. Докер
2. Докерфайл с постгре
3. Наладить коннект spring boot с postgres - application.property и проперти dockerfile либо docker-compose.yml

В целом по проекту -
Соблюдать структуру слоев - service, repository, entity, dto, controller
<h1> ОБЯЗАТЕЛЬНО ОСТАВЛЯЙ КОММЕНТАРИИ К ТОМУ, ЧТО ТЫ СДЕЛАЛ </h1>
<h1> В ПРОЕКТКЕ ПО МИНИМУМУ ДОЛЖНО БЫТЬ МАГИЧЕСКИХ ЛИТЕРАЛОВ </h1>

База данных будет запускаться с помощью команды

```shell
docker build -t nikita .   # в папке postgre_config
docker run -d -p 5432:5432 nikita    
```

Для взаимодействия с базой данных на уровне jpa/hibernate нам нужны следующие виды сущностей
Entity - это класс, который из себя представляет таблицу в базе данных.
Repository - репозиторий, в котором будут храниться методы для взаимодействия с БД

Задание 24.04.2024

1. Разделить класс CsvFileProcessService. Т.е. Оставить в нём только бины и код, которые умеют работать с CSV, а
   остальное перекинуть в XlsxFileProcessService
2. Заставить код работать так, чтобы xlsx у тебя обрабатывался в XlsxFileProcessService, а CSV в CSVFileProcessService