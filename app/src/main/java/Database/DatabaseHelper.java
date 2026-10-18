package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PANTRY = "pantry";
    public static final String COLUMN_PANTRY_ID = "id";
    public static final String COLUMN_PANTRY_NAME = "name";
    public static final String COLUMN_PANTRY_QUANTITY = "quantity";
    public static final String COLUMN_PANTRY_UNIT = "unit";
    public static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_STEPS = "steps";

    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String COLUMN_RI_ID = "id";
    public static final String COLUMN_RI_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RI_NAME = "ingredient_name";
    public static final String COLUMN_RI_QUANTITY = "quantity";
    public static final String COLUMN_RI_UNIT = "unit";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PANTRY_TABLE = "CREATE TABLE " + TABLE_PANTRY + "("
                + COLUMN_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PANTRY_NAME + " TEXT, "
                + COLUMN_PANTRY_QUANTITY + " REAL, "
                + COLUMN_PANTRY_UNIT + " TEXT, "
                + COLUMN_PANTRY_EXPIRY + " TEXT)";
        db.execSQL(CREATE_PANTRY_TABLE);

        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECIPE_NAME + " TEXT, "
                + COLUMN_RECIPE_STEPS + " TEXT)";
        db.execSQL(CREATE_RECIPES_TABLE);

        String CREATE_RI_TABLE = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + "("
                + COLUMN_RI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RI_RECIPE_ID + " INTEGER, "
                + COLUMN_RI_NAME + " TEXT, "
                + COLUMN_RI_QUANTITY + " REAL, "
                + COLUMN_RI_UNIT + " TEXT, "
                + "FOREIGN KEY(" + COLUMN_RI_RECIPE_ID + ") REFERENCES " + TABLE_RECIPES + "(" + COLUMN_RECIPE_ID + "))";
        db.execSQL(CREATE_RI_TABLE);

        seedInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        onCreate(db);
    }

    private void seedInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, "Tomato Pasta");
        values.put(COLUMN_RECIPE_STEPS, "Boil pasta. Cook tomatoes with garlic. Mix together.");
        long recipeId1 = db.insert(TABLE_RECIPES, null, values);

        values.clear();
        values.put(COLUMN_RI_RECIPE_ID, recipeId1);
        values.put(COLUMN_RI_NAME, "pasta");
        values.put(COLUMN_RI_QUANTITY, 200.0);
        values.put(COLUMN_RI_UNIT, "g");
        db.insert(TABLE_RECIPE_INGREDIENTS, null, values);

        values.clear();
        values.put(COLUMN_RI_RECIPE_ID, recipeId1);
        values.put(COLUMN_RI_NAME, "tomato");
        values.put(COLUMN_RI_QUANTITY, 3.0);
        values.put(COLUMN_RI_UNIT, "pieces");
        db.insert(TABLE_RECIPE_INGREDIENTS, null, values);
    }

    public long addPantryItem(String name, double quantity, String unit, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PANTRY_NAME, name);
        values.put(COLUMN_PANTRY_QUANTITY, quantity);
        values.put(COLUMN_PANTRY_UNIT, unit);
        values.put(COLUMN_PANTRY_EXPIRY, expiryDate);
        long id = db.insert(TABLE_PANTRY, null, values);
        db.close();
        return id;
    }

    public Cursor getAllPantryItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PANTRY, null);
    }

    public int updatePantryItem(long id, String name, double quantity, String unit, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PANTRY_NAME, name);
        values.put(COLUMN_PANTRY_QUANTITY, quantity);
        values.put(COLUMN_PANTRY_UNIT, unit);
        values.put(COLUMN_PANTRY_EXPIRY, expiryDate);
        int rows = db.update(TABLE_PANTRY, values, COLUMN_PANTRY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public void deletePantryItem(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANTRY, COLUMN_PANTRY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
    }

    public Cursor getRecipeIngredients(long recipeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RECIPE_INGREDIENTS + " WHERE " + COLUMN_RI_RECIPE_ID + " = ?", new String[]{String.valueOf(recipeId)});
    }
}