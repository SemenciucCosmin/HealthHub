# HealthHub

HealthHub is a modular Android application for managing health-related activities, appointments, and medical files. It is built using modern Android development practices (Kotlin, Jetpack Compose, modular architecture).

## Features
- User authentication and account management
- Appointment scheduling and management
- Medical file storage and viewing
- Home dashboard and info sections
- Modular architecture for scalability
- Network communication with secure API integration

## Project Structure
- `app/` - Main application module
- `data/` - Data layer modules (account, appointments, authentication, etc.)
- `domain/` - Domain logic modules
- `feature/` - Feature modules (UI and logic for each feature)
- `network/` - Network communication layer
- `ui/` - UI components and navigation

## Getting Started
1. Clone the repository.
2. Add your API keys to `local.properties` (see below).
3. Build and run the project using Android Studio.

### Required local.properties entries
```
SONAR_TOKEN=your_sonar_token
GOOGLE_MAPS_API_KEY=your_google_maps_api_key
```

## Security
- API keys are loaded from `local.properties` and not hardcoded in the source code.
- Cleartext traffic is disabled by default.

## Backup & Data Extraction
- Data extraction rules are defined in `app/src/main/res/xml/data_extraction_rules.xml` and `ui/catalog/src/main/res/xml/data_extraction_rules.xml`.

## Code Quality & Linting
- Android Lint is enabled for all modules. Run `./gradlew lint` to analyze code quality and detect issues.
- Use `./gradlew lintFix` to automatically fix safe issues.

## Contribution
Contributions are welcome! Please open issues and pull requests for improvements.

## License
[Specify your license here]

## Technologies Used
- Kotlin
- Jetpack Compose
- AndroidX
- Koin (Dependency Injection)
- Retrofit & OkHttp (Networking)
- Google Maps SDK
- SonarCloud (Code Quality)

## Testing
- Unit and instrumentation tests are located in each module's `src/test` and `src/androidTest` directories.
- Run all tests with `./gradlew test` and `./gradlew connectedAndroidTest`.

## Troubleshooting
- If you encounter build or dependency issues, ensure your local.properties is set up and dependencies are up to date.
- For API key or token issues, check your `local.properties` file and do not commit sensitive information to version control.
