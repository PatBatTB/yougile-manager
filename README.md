# YouGile-Plugins Platform
___
## Plugin maanger
___
___
### Description

Менеджер для запуска плагинов для `YouGile`
___
### Usage

#### Скачать готовый архив:
- Скачать архив с последней версией из [релизов](https://github.com/PatBatTB/yougile-manager/releases).
- Распаковать
- В папку `plugins` положить jar-файлы плагинов.
- Добавить +x скрипту `start.sh` 
```bash
chmod +x ./start.sh
```
- запустить `strat.sh`

#### Собрать из исходников:
- Клонировать текущий репозиторий
- Собрать проект `mvn clean package` (jar-файл необходимо брать "толстый" с суффиксом `-full`)
- Создать в папке с jar-файлом менеджера папку `plugins`
- Скопировать jar-файлы плагинов в папку `plugins`
- Для удобства можно создать скрипт запуска `start.sh`
```bash
WORK_DIR="$(dirname "$0")"
cd $WORK_DIR
java -jar manager_file_name.jar
```
- Добавить +x скрипту `start.sh`
```bash
chmod +x ./start.sh
```
- запустить `strat.sh`
___