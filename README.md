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
- Собрать проект `mvn clean package`
- Забрать архив `yougile-manager-${current-version}-release.tar.gz`
- Распаковать
- В папку `plugins` положить jar-файлы плагинов.
- Добавить +x скрипту `start.sh`
```bash
chmod +x ./start.sh
```
- запустить `strat.sh`
___