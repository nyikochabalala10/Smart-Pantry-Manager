package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextUnit;
    private EditText editTextExpiryDate;
    private Button buttonSave;
    private int ingredientId = -1;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        editTextName = findViewById(R.id.editTextIngredientName);
        editTextQuantity = findViewById(R.id.editTextIngredientQuantity);
        editTextUnit = findViewById(R.id.editTextIngredientUnit);
        editTextExpiryDate = findViewById(R.id.editTextIngredientExpiry);
        buttonSave = findViewById(R.id.buttonSaveIngredient);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("INGREDIENT_ID")) {
            isEditMode = true;
            ingredientId = intent.getIntExtra("INGREDIENT_ID", -1);
            editTextName.setText(intent.getStringExtra("INGREDIENT_NAME"));
            editTextQuantity.setText(intent.getStringExtra("INGREDIENT_QUANTITY"));
            editTextUnit.setText(intent.getStringExtra("INGREDIENT_UNIT"));
            editTextExpiryDate.setText(intent.getStringExtra("INGREDIENT_EXPIRY"));
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIngredient();
            }
        });
    }

    private void saveIngredient() {
        String name = editTextName.getText().toString().trim();
        String quantityStr = editTextQuantity.getText().toString().trim();
        String unit = editTextUnit.getText().toString().trim();
        String expiryDate = editTextExpiryDate.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Name is required");
            return;
        }

        if (TextUtils.isEmpty(quantityStr)) {
            editTextQuantity.setError("Quantity is required");
            return;
        }

        if (TextUtils.isEmpty(unit)) {
            editTextUnit.setError("Unit is required");
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityStr);
        } catch (NumberFormatException e) {
            editTextQuantity.setError("Invalid quantity");
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("NAME", name);
        resultIntent.putExtra("QUANTITY", quantity);
        resultIntent.putExtra("UNIT", unit);
        resultIntent.putExtra("EXPIRY", expiryDate);
        if (isEditMode) {
            resultIntent.putExtra("ID", ingredientId);
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}