# My Random Users
Android приложение для генерации случайных пользователей с использованием API [randomuser.me](https://randomuser.me). 

Приложение демонстрирует современные подходы к Android разработке с использованием Clean Architecture, Jetpack Compose и Dependency Injection.

[Расположение APK-файла](https://github.com/eXTrimeXT/MyRandomUsers/blob/main/app/build/outputs/apk/debug/app-debug.apk):
```app/build/outputs/apk/debug/app-debug.apk```

## Функциональность
- **Генерация случайных пользователей** через API randomuser.me
- **Фильтрация** по полу и национальности
- **Кеширование данных** для работы оффлайн
- **Детальная информация** о каждом пользователе
- **Случайный выбор** фильтров ("Random" опция)
- **Обновление данных** с индикацией загрузки

## Пример
Папка screen_example:

![1](https://github.com/eXTrimeXT/MyRandomUsers/blob/main/screen_example/1.jpg)
![2](https://github.com/eXTrimeXT/MyRandomUsers/blob/main/screen_example/2.jpg)
![3](https://github.com/eXTrimeXT/MyRandomUsers/blob/main/screen_example/3.jpg)
![4](https://github.com/eXTrimeXT/MyRandomUsers/blob/main/screen_example/4.jpg)
![5](https://github.com/eXTrimeXT/MyRandomUsers/blob/main/screen_example/5.jpg)

## Архитектура
Приложение построено по принципам **Clean Architecture** с разделением на три слоя:

### Presentation
- **Jetpack Compose** для UI
- **ViewModel** для управления состоянием
- **Navigation Component** для навигации
- **Material Design 3** для современных UI компонентов

### Domain
- **Use Cases** для бизнес-логики
- **Repository interfaces** для абстракции данных
- **Domain Models** для бизнес-сущностей

### Data
- **Retrofit** для работы с API
- **Room** для локального кеширования
- **Repository implementations** для управления данными

## Технологии
- **Kotlin** + **Coroutines** для асинхронности
- **Jetpack Compose** для декларативного UI
- **Dagger Hilt** для dependency injection
- **Retrofit** + **Gson** для сетевых запросов
- **Room** для локальной базы данных
- **Coil** для загрузки изображений
- **Material Design 3** для дизайна

## Зависимости

Основные зависимости проекта:

```kotlin
// UI
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose")

// DI
implementation("com.google.dagger:hilt-android")
ksp("com.google.dagger:hilt-compiler")

// Network
implementation("com.squareup.retrofit2:retrofit")
implementation("com.squareup.retrofit2:converter-gson")

// Database
implementation("androidx.room:room-runtime")
implementation("androidx.room:room-ktx")
ksp("androidx.room:room-compiler")

// Image Loading
implementation("io.coil-kt:coil-compose")
```

## Интерфейс
### Главный экран (**MainScreen**)
- Выбор пола (**Male/Female/Random**)
- Выбор национальности (**US/GB/CA/.../Random**)
- Кнопка генерации пользователей
### Экран списка (**UserListScreen**)
- Список пользователей с **фото** и основной информацией
- **Индикатор** загрузки и ошибок
- Кнопка обновления данных
- Отображение **кешированных данных** при отсутствии интернета

### Экран деталей (UserDetailScreen)
- Детальная информация о пользователе
- **Фотография** большого размера
- Персональные и контактные данные

## Настройка и сборка
### Требования
- **Intellij Idea** 2025.2.4 или аналог Android Studio
- Минимальная версия Android SDK: 33
- Целевая версия Android SDK: **36**
- Kotlin **2.2.21+**

### Сборка
- Синхронизировать Gradle
- Собрать проект (Build → Make Project)


## API
Приложение использует Random User Generator API:
- Базовый URL: https://randomuser.me/
- Эндпоинт: /api/
- Параметры:
- - results - количество пользователей (по умолчанию 20)
- - gender - фильтр по полу (male/female)
- - nat - фильтр по национальности (US, GB, CA, etc.)

## Кеширование
- Room Database для хранения пользователей
- Автоматическое использование кеша при отсутствии интернета
- Сохранение новых данных при успешном API запросе
- Фильтрация кешированных данных по выбранным критериям

## Особенности реализации
- State Management: Использование StateFlow для управления состоянием
- Error Handling: Грамотная обработка ошибок сети и данных
- Offline Support: Полная работа без интернета с кешированными данными
- Modern UI: Полностью на Jetpack Compose с Material Design 3
- Clean Code: Следование принципам чистой архитектуры и SOLID
