🛒 MetroMart
A modern, modular GitHub repository viewer app built with Jetpack Compose, MVVM, and Clean Architecture.
Designed for performance, testability, and scalability — ideal for learning production-ready Android development.

🧩 Modular Architecture
MetroMart is structured into clearly separated modules:

kotlin
Copy
Edit
📦 MetroMart
├── data      → DTOs, repository implementations, fake/mock data
├── domain    → Models, use cases, repository interfaces
├── ui        → Jetpack Compose UI, navigation, previews, ViewModels
✨ Features
🧱 Built with Jetpack Compose and Material 3

🧭 Follows MVVM + Clean Architecture

📦 Modular structure: data, domain, and ui

🎨 Full support for multi-device @Preview (Phone, Tablet, Foldable) and Dark Mode

📃 Includes Baseline Profile for faster app startup and rendering

✅ Compose UI Testing with test tags and assertions

🔍 Unit Testing for business logic and use cases

🧪 Testing
✅ Unit Testing
Located in domain and data modules

Verifies use cases and repository behavior using fake/mock implementations

✅ Compose UI Tests
Written with AndroidJUnit4 and ComposeTestRule

Tests components using testTag + UI assertions

Example:

kotlin
Copy
Edit
composeTestRule.onNodeWithTag("repoList").assertIsDisplayed()
⚡ Baseline Profile
Located in the baselineprofile module

Optimizes runtime performance by pre-compiling key code paths

Enables smooth first-launch experience

🚀 How to Run
bash
Copy
Edit
git clone https://github.com/jericricafrente03/MetroMart.git
Open in Android Studio Hedgehog or newer

Build and run the ui module

Run tests from the test and androidTest directories

📁 Module Responsibilities
Module	Responsibilities
data	Repository implementation, local/remote sources, fake data
domain	Use cases, core models, business logic interfaces
ui	Jetpack Compose UI, theming, navigation, previews, tests
baselineprofile	BaselineProfileGenerator for performance optimization

📌 Tech Stack
Kotlin

Jetpack Compose + Material 3

Hilt (DI)

MVVM + UseCases

SQLDelight / Room (optional)

Jetpack Navigation

JUnit, Compose UI Test

Baseline Profiles

