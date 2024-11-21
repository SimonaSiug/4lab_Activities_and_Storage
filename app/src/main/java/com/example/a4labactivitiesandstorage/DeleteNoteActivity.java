package com.example.a4labactivitiesandstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {
    private Spinner spinnerNotes;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        notesList = new ArrayList<>(sp.getStringSet("notes", new HashSet<>()));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotes.setAdapter(adapter);

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this::onDeleteNoteClick);
    }

    public void onDeleteNoteClick(View view) {
        if (!notesList.isEmpty()) {
            String selectedNote = (String) spinnerNotes.getSelectedItem();

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sp.edit();

            Set<String> notesSet = sp.getStringSet("notes", new HashSet<>());
            notesSet.remove(selectedNote);

            editor.putStringSet("notes", notesSet);
            editor.apply();

            notesList.remove(selectedNote);
            ((ArrayAdapter) spinnerNotes.getAdapter()).notifyDataSetChanged();
        }

        finish();
    }
}
