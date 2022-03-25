package com.live.programming.an15_notebook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.live.programming.an15_notebook.adapters.DisplayAdapter;
import com.live.programming.an15_notebook.databinding.ActivityDisplayBinding;
import com.live.programming.an15_notebook.models.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ActivityDisplayBinding binding;
    private RecyclerView rView;
    private LinearLayoutManager linearManager;

    FirebaseFirestore fireStore;
    private ProgressDialog loader;

    List<NoteModel> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rView = binding.recyclerView;

        // vertical list view layout
        linearManager = new LinearLayoutManager(DisplayActivity.this);
        rView.setLayoutManager(linearManager);

        // arrange in grid view layout format
        GridLayoutManager gm = new GridLayoutManager(DisplayActivity.this, 2); // 1 behaves as linear, ?1 begaves as grid
        rView.setLayoutManager(gm);

        loader = new ProgressDialog(DisplayActivity.this);
        loader.setMessage("Loading your notes...");
        loader.show();
        fetchAllNotes();

    }

    private void fetchAllNotes() {
        fireStore = FirebaseFirestore.getInstance();

        fireStore.collection("Notes")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isComplete()) // fetching
                    {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            NoteModel model = new NoteModel(
                                    snapshot.getId(),
                                    snapshot.getString("title"),
                                    String.valueOf(snapshot.get("date_time")),
                                    snapshot.getString("description")
                            );
                            noteList.add(model);
                        }
                        loader.dismiss();
                        DisplayAdapter adapter = new DisplayAdapter(DisplayActivity.this, noteList);
                        rView.setAdapter(adapter); // applying the adapter
                    }
                })
                .addOnFailureListener(e -> {
                    loader.dismiss();
                    // write the code yourself to display the exception
                });
    }

    /* code that will include menu in the activity */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* to add event against the menu item */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_add){
            startActivity(new Intent(DisplayActivity.this, AddNoteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}