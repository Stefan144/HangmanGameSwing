# Hangman Game


### Содержание
 
1. Об игре
2. Постановка задачи
3. Запуск и сборка
4. Описание проекта
5. Тесты

### Об игре

* Виселица - игра для двух человек. Один из игроков загадывает слово и отмечает места для букв, например чертами. Также рисуется виселица с петлёй. Второй игрок предлагает букву, которая может входить в это слово. Если такая буква есть в слове, то первый игрок пишет её над соответствующими этой букве чертами — столько раз, сколько она встречается в слове. Если такой буквы нет, то к виселице добавляется круг в петле, изображающий голову. Второй игрок продолжает отгадывать буквы до тех пор, пока не отгадает всё слово. За каждый неправильный ответ первый игрок добавляет одну часть туловища к виселице. Если туловище в виселице нарисовано полностью, то отгадывающий игрок проигрывает, считается повешенным. Если игроку удаётся угадать слово, он выигрывает 

### Постановка задачи

* Задача данного проекта реализовать данную игру на Java, используя принципы ООП и с наличием графического интерфейса. Детали - ниже.

### Запуск и сборка

0. Скачать репозиторий
```bash
git clone https://github.com/Stefan144/HangmanGameSwing.git
```
1. Открыть в IDE (в моем случае IntelliJ IDEA) и запустить Main.java
2. Обычный jar сборщик
```bash
cd HangmanGameSwing
java -jar GameHangman.jar
```
3. maven сборщик
```bash
mvn
cd HangmanGameSwing
java -cp target/Test-1.0-SNAPSHOT.jar Main
```


### Описание проекта


Проект реализует вышеописанную игру. Для реализации графического интерфейса используется библиотека Swing. Файлы проекта:

1. Main.java - запуск игры.
2. HangmanCanvas.java - окно игры, в которой находятся буквы для выбора и меняющиеся стадии hangman'a (в виде картинок), а также счетчик неправильно угаданных букв и отгаданное слово (открывающиеся до той степени до которой игрок на данный момент его отгадал). Описана логика взаимодействия с игрой
3. Hangman - отвечает за картинку с hangman'ом (меняет их в зависимости от отгадок игрока).
4. LetterFigure.java - отвечает за загрузку букв и их смену с состояния не нажатая на нажатая.
5. LetterPositioning.java - отвечает за позицонирование букв между собой в главном окне игры (HangmanCanvas).
6. В папке pictures лежат изображения букв (пары буква + нажатая буква), а также hangman'a (8 стадий, 6 промежуточных + 2 на победу и поражение).

Сразу после запуска проекта пользователю предлагается в вести слово, которое в дальнейшем другой пользователь должен отгадать:

<img src="https://i.imgur.com/SjkCZRu.png" width="400" height="150"  hspace="20"/>

* Пользователь вводит свое слово и жмет Let's Play:

<img src="https://i.imgur.com/B6gDlxX.png" width="400" height="150"  hspace="20"/>

* Начинается игра. Пользователь выбирает буквы, ведется подсчет неправильно выбранных букв, а также заполняются правильно выбранные:

<img src="https://i.imgur.com/jmh5dw7.png" width="250" height="330"  hspace="20"/>

* Далее пользователь либо проигрывает, либо выигрывает - об этом ему дает понять появившаяся картинка:

<img src="https://i.imgur.com/xg5zYmv.png" width="250" height="330" hspace="20"/><img src="https://i.imgur.com/oghxYXL.png" width="250" height="330" hspace="20"/>

* В обоих случаях пользователю предлагается сыграть заново, либо закончить играть:

<img src="https://i.imgur.com/jJKtdkA.png" width="300" height="120"  hspace="20"/>

### Тесты

...в процессе...
