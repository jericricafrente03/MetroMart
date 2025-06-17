🛒 MetroMart
MetroMart is a modern GitHub repository viewer app built with Jetpack Compose, following MVVM and Clean Architecture principles.
It showcases responsive UI design, performance optimizations, and testability — perfect for learning real-world Android development.

✨ Key Features
✅ Built with Jetpack Compose and Material 3

✅ Follows MVVM + Clean Architecture

✅ Sample GitHub repository list using GithubModel & OwnerModel

✅ Light & Dark Mode support

✅ Multi-device @Preview support (Phone, Tablet, Foldable)

✅ UI testing with ComposeTestRule

✅ Unit testing for core business logic

✅ Integrated Baseline Profile for improved startup performance

🧪 Testing Overview
🧠 Unit Testing
Verifies business logic and use case behavior

Uses fake repositories for clean, isolated tests

🧱 Compose UI Testing
Uses ComposeTestRule and testTag for UI assertions

kotlin
Copy
Edit
composeTestRule.onNodeWithTag("repoList").assertIsDisplayed()
⚡ Baseline Profile
Pre-compiles performance-critical code paths

Boosts startup time and smoothens first render experience

Located in the baselineprofile module

🚀 Getting Started
bash
Copy
Edit
git clone https://github.com/jericricafrente03/MetroMart.git
Open in Android Studio Hedgehog or newer

Sync and build the project

Run the app on an emulator or device

Use the Run tool window to execute tests

🧑‍💻 Tech Stack
Kotlin

Jetpack Compose

Material 3

MVVM + UseCases

Hilt (Dependency Injection)

JUnit & Compose UI Testing

AndroidX Baseline Profile

