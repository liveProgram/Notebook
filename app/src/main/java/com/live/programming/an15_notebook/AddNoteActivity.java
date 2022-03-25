package com.live.programming.an15_notebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.live.programming.an15_notebook.databinding.ActivityAddNoteBinding;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;
    private TextInputLayout titleLayout, descLayout;
    private TextInputEditText titleEdit, descEdit;
    private FirebaseFirestore fireStore;  // declaring the fire store reference variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_add_note);

        // defining the binding
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        titleEdit = binding.titleInput;
        titleLayout = binding.titleLayout;
        descLayout = binding.descLayout;
        descEdit = binding.descInput;

        // define or initializing the reference
        fireStore = FirebaseFirestore.getInstance();

        binding.btnUpload.setOnClickListener(view -> {
            String title = String.valueOf(titleEdit.getText());
            String desc = String.valueOf(descEdit.getText());

            titleLayout.setError("");
            descLayout.setError("");

            if (validate(title, desc)) {
                // adding data into cloud db

                Map<String, Object> noteMap = new HashMap<>();
                noteMap.put("title", title);
                noteMap.put("description", desc);
                noteMap.put("date_time", new Date().toString());

                fireStore
                        .collection("Notes")
                        .add(noteMap) // allow to add the data
                        .addOnCompleteListener(task -> {
                            if (task.isComplete()) {
                                Snackbar.make(binding.getRoot(), "Just upload e newly created note", Snackbar.LENGTH_SHORT).show();
                                startActivity(new Intent(AddNoteActivity.this, DisplayActivity.class));
                            }
                            else
                                Snackbar.make(binding.getRoot(), "Try again", Snackbar.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e ->
                                Snackbar.make(binding.getRoot(), "Sorry : " + e.getMessage(), Snackbar.LENGTH_SHORT).show()
                        );

            }
        });
    }

    private boolean validate(String title, String desc) {
        if (title.isEmpty())
            titleLayout.setError("Put a title please");
        else if (desc.isEmpty())
            descLayout.setError("Add note description to proceed");
        else
            return true;
        return false;
    }
}










