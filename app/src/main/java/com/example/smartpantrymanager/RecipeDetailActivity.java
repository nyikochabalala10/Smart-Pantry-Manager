package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView ingredientsTextView;
    private TextView instructionsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleTextView = findViewById(R.id.textRecipeTitle);
        ingredientsTextView = findViewById(R.id.textRecipeIngredients);
        instructionsTextView = findViewById(R.id.textRecipeInstructions);

        String title = getIntent().getStringExtra("RECIPE_TITLE");
        String ingredients = getIntent().getStringExtra("RECIPE_INGREDIENTS");
        String instructions = getIntent().getStringExtra("RECIPE_INSTRUCTIONS");

        if (title != null) {
            titleTextView.setText(title);
        }

        if (ingredients != null) {
            ingredientsTextView.setText(ingredients);
        }

        if (instructions != null) {
            instructionsTextView.setText(instructions);
        }
    }
}