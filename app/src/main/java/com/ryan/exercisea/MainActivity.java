package com.ryan.exercisea;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etSubtitle, etAuthor, etCategory;
    private Button btnAddComic;

    private DatabaseReference comicsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSubtitle = findViewById(R.id.etSubtitle);
        etAuthor = findViewById(R.id.etAuthor);
        etCategory = findViewById(R.id.etCategory);

        btnAddComic = findViewById(R.id.btnAddComic);

        comicsRef = FirebaseDatabase.getInstance()
                .getReference("comics");

        btnAddComic.setOnClickListener(v -> addComic());
    }

    private void addComic() {

        String name = etName.getText().toString();
        String subtitle = etSubtitle.getText().toString();
        String author = etAuthor.getText().toString();
        String category = etCategory.getText().toString();

        if (name.isEmpty() || subtitle.isEmpty()
                || author.isEmpty() || category.isEmpty()) {

            Toast.makeText(this,
                    "Please complete all fields",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String id = comicsRef.push().getKey();

        if (id == null) {
            Toast.makeText(this,
                    "Could not generate comic ID",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Comic comic = new Comic(
                id, name, subtitle, author, category
        );

        btnAddComic.setEnabled(false);

        comicsRef.child(id).setValue(comic)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(this,
                            "Comic added successfully!",
                            Toast.LENGTH_SHORT).show();

                    etName.setText("");
                    etSubtitle.setText("");
                    etAuthor.setText("");
                    etCategory.setText("");

                    btnAddComic.setEnabled(true);
                })
                .addOnFailureListener(error -> {

                    btnAddComic.setEnabled(true);

                    Toast.makeText(this,
                            "Save failed: " + error.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }
}