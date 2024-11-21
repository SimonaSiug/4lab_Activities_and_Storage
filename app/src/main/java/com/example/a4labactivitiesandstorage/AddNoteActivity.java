package com.example.a4labactivitiesandstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void onAddNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> oldSet = sp.getStringSet("notes", new HashSet<>());
        Set<String> newStrSet = new HashSet<>();
        newStrSet.add(txtNote.getText().toString());
        newStrSet.addAll(oldSet);

        spEd.putStringSet("notes", newStrSet);
        spEd.apply();

        finish();
    }
}

