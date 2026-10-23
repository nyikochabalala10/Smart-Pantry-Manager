Overview

Smart Pantry Manager is an Android application designed to help users keep track of their household food inventory, manage pantry items, and discover recipe suggestions based on what they currently have in stock. The app provides a clean, intuitive interface following modern mobile architecture principles.

How to Use Smart Pantry

Using Smart Pantry Manager to organize your household food stock is quick and straightforward. You begin by opening the application on your device or emulator and tapping the action button from the main home screen to add new items. Enter details such as the item name, quantity, category, and expiration date using the date picker dialog before tapping save to store the item into your local database. Once added, you can manage and update your inventory by browsing your current stock list in a clean, responsive view where tapping any listed item allows you to adjust quantities or update details when restocking. To discover meal options, navigate to the suggested recipes section via the navigation bar, where the application automatically compares your stored pantry ingredients against available recipes to show what you can prepare immediately. Finally, you can customize display preferences by opening the settings screen to adjust options such as sorting your pantry items alphabetically or by expiration date.

Features
1.	Pantry Inventory Management: Add, update, and organize food items with quantities and categories.
2.	Recipe Matching: Browse recipes and check ingredient availability against your current pantry stock.
3.	Local Data Persistence: Utilizes SQLite database helpers and shared preferences to securely store user data locally across application life cycles.
4.	Custom Interactive UI: Features custom dialogs, navigation bars, and responsive layouts built using ConstraintLayout and RecyclerView components.

Technology Stack & Architecture
1.	Language: Java
2.	UI Framework: Android Views, ConstraintLayout, RecyclerView
3.	Database: SQLite (via custom SQLiteOpenHelper and data source implementation classes)
4.	Build System: Gradle (Version Catalogs)
5.	Minimum SDK: API Level 15+ (Ice Cream Sandwich)
6.	Target SDK: API Level 34 / 37

Project Structure
1.	com.example.smartpantrymanager.activities: Contains application screens and activity lifecycle controllers.
2.	com.example.smartpantrymanager.adapter: RecyclerView adapters for binding data to UI lists.
3.	com.example.smartpantrymanager.database: SQLite database helpers and data source management classes.
4.	com.example.smartpantrymanager.model: Data classes representing core entities such as Pantry items and Recipes.
5.	com.example.smartpantrymanager.logic: Business logic and matching algorithms.

Getting Started & Installation
1.	Clone or download the repository to your local machine.
2.	Open Android Studio.
3.	Select Open an existing Android Studio project and navigate to the project root directory.
4.	Ensure your environment's JAVA_HOME variable points to the bundled JetBrains JDK (C:\Program Files\Android\Android Studio\jbr).
5.	Sync the project with Gradle files (File > Sync Project with Gradle Files).
6.	Build and run the project on an Android Virtual Device (AVD emulator running API 33 or higher).

Comments & Developer Notes
This application successfully manages household pantry inventory.
Build & Environment Setup: During development, the project was configured using Gradle Version Catalogs (libs.versions.toml) to streamline plugin and dependency management.
Architecture & UI Optimizations: Layouts were optimized using ConstraintLayout to reduce view hierarchy depth and improve performance.
