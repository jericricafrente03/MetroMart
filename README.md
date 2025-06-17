🛒 MetroMart
A modular GitHub repository viewer app designed with Jetpack Compose, built using Kotlin, MVVM, and Clean Architecture.
Perfect for learning scalable architecture, UI composition, and modern Android practices.

🧩 Modular Architecture
MetroMart follows a modular structure for better maintainability and separation of concerns:


📦 MetroMart
├── data      → Contains DTOs, repository implementations, and fake/mock data
├── domain    → Defines models, use cases, and repository interfaces
├── ui        → Contains Jetpack Compose UI, themes, navigation, previews


✨ Features
🔹 Jetpack Compose with Material 3 styling

🔹 MVVM Architecture with ViewModel, UseCases, and state management

🔹 Modular project structure (data, domain, ui)

🔹 Sample data using GithubModel and OwnerModel

🔹 Clean separation of UI, business logic, and data

🔹 Multi-device @Preview support (Phone, Tablet, Foldable – Light & Dark modes)

📁 Module Responsibilities
data
Provides data source logic (local/remote)

Supplies fake/mock repository data

Contains actual implementations of the repository interface from domain

domain
Defines core models (GithubModel, OwnerModel)

Hosts business rules and UseCase classes

Declares repository interfaces used by the app

ui
Implements all Compose-based screens and components

Manages theme, navigation, and ViewModels

Provides responsive previews for multiple screen sizes and themes

🚀 How to Run
Clone the repo:

bash
Copy
Edit
git clone https://github.com/jericricafrente03/MetroMart.git
Open with Android Studio Hedgehog or newer

Run the ui module on an emulator or physical device

🧪 Ideal For
Practicing modular and scalable Android architecture

Learning modern UI development with Compose

Quick testing of preview support for different screen sizes
