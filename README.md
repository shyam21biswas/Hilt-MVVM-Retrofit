# Hilt-MVVM-Retrofit

An Android application demonstrating modern Android development practices using **Hilt** for dependency injection, **MVVM** (Model-View-ViewModel) architecture pattern, and **Retrofit** for network operations.

## 📱 About

This project showcases a clean architecture approach to building Android applications with the following key technologies:

- **Hilt**: Dependency injection library built on top of Dagger
- **MVVM Architecture**: Separation of concerns using Model-View-ViewModel pattern
- **Retrofit**: Type-safe HTTP client for Android and Java
- **Kotlin**: Modern programming language for Android development

## 🏗️ Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern:

```
├── Model: Data layer (API services, repositories, data models)
├── View: UI layer (Activities, Fragments)
└── ViewModel: Business logic layer (manages UI-related data)
```

## 🛠️ Tech Stack

- **Language**: Kotlin 100%
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **Architecture**: MVVM
- **Build System**: Gradle (Kotlin DSL)

## 📦 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/              # Application source code
│   │   ├── res/               # Resources (layouts, drawables, etc.)
│   │   └── AndroidManifest.xml
│   └── test/                  # Unit tests
├── build.gradle.kts           # App-level build configuration
└── proguard-rules.pro
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 11 or later
- Android SDK with minimum API level 21

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/shyam21biswas/Hilt-MVVM-Retrofit.git
   ```

2. Open the project in Android Studio

3. Let Gradle sync the project

4. Run the app on an emulator or physical device

## 🔑 Key Features

- **Dependency Injection**: Uses Hilt for clean and maintainable dependency management
- **Network Calls**: Retrofit integration for API communication
- **Reactive Programming**: LiveData/Flow for data observation
- **Clean Architecture**: Separation of concerns with MVVM pattern

## 📚 Dependencies

Key libraries used in this project:

- Hilt - Dependency Injection
- Retrofit - REST API calls
- Gson/Moshi - JSON parsing
- Coroutines - Asynchronous programming
- ViewModel & LiveData - Architecture components

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## 📄 License

This project is available for educational and reference purposes.

## 👤 Author

**Shyam Biswas**
- GitHub: [@shyam21biswas](https://github.com/shyam21biswas)

---

⭐ If you found this project helpful, please give it a star!