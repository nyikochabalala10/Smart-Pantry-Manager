Overview
Smart Pantry Manager is an Android application designed to help users keep track of their household food inventory, manage pantry items, and discover recipe suggestions based on what they currently have in stock. The app provides a clean, intuitive interface following modern mobile architecture principles.

Features
Pantry Inventory Management: Add, update, and organize food items with quantities and categories.
Recipe Matching: Browse recipes and check ingredient availability against your current pantry stock.
Local Data Persistence: Utilizes SQLite database helpers and shared preferences to securely store user data locally across application life cycles. 
Custom Interactive UI: Features custom dialogs, navigation bars, and responsive layouts built using ConstraintLayout and RecyclerView components. 


Technology Stack & Architecture
Language: Java 
UI Framework: Android Views, ConstraintLayout, RecyclerView 
Database: SQLite (via custom SQLiteOpenHelper and data source implementation classes) 
Build System: Gradle (Version Catalogs) 
Minimum SDK: API Level 15+ (Ice Cream Sandwich) 
Target SDK: API Level 34 / 37


Project Structure
com.example.smartpantrymanager.activities: Contains application screens and activity lifecycle controllers.
com.example.smartpantrymanager.adapter: RecyclerView adapters for binding data to UI lists.
com.example.smartpantrymanager.database: SQLite database helpers and data source management classes.
com.example.smartpantrymanager.model: Data classes representing core entities such as Pantry items and Recipes.
com.example.smartpantrymanager.logic: Business logic and matching algorithms.


