# Log Brackets Highlighter

PhpStorm-плагин для подсветки парных `()`, `[]` и `{}` в файлах с расширением `.log`.
Он не меняет file type, поэтому встроенная подсветка даты и уровня логирования сохраняется.

Плагин рассчитан на PhpStorm 2025.3.x.

## Установка:
1. Загрузите ZIP из `build/distributions/` в PhpStorm через **Settings → Plugins → ⚙ → Install Plugin from Disk**. 
2. Перезапустите PhpStorm.

## Сборка
1. Скачайте и распакуйте Gradle 8.14.6 или новее с [gradle.org/releases](https://gradle.org/releases/). Добавлять распакованную папку Gradle в репозиторий не нужно.
2. Откройте эту папку как Gradle-проект в PhpStorm, выбрав Gradle и JDK 21.
3. Запустите задачу `buildPlugin`. Если CDN JetBrains недоступен, используйте установленный PhpStorm: `gradle buildPlugin -PphpStormPath="C:\Program Files\JetBrains\PhpStorm 2025.3.2"`.
