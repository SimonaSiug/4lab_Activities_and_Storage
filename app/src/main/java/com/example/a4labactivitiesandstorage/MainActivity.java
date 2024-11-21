// MainActivity.java
package com.example.a4labactivitiesandstorage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView lvNotes;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = findViewById(R.id.notesListView);
        if (lvNotes == null) {
            throw new IllegalStateException("ListView with ID 'notesListView' not found in activity_main.xml");
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> notesSet = sp.getStringSet("notes", new HashSet<>());
        notesList = new ArrayList<>(notesSet);

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvNotes.setAdapter(listAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> notesSet = sp.getStringSet("notes", new HashSet<>());
        notesList.clear();
        notesList.addAll(notesSet);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.add_note) {
            Intent addActivityIntent = new Intent(this, AddNoteActivity.class);
            startActivity(addActivityIntent);
            return true;
        } else if (itemId == R.id.delete_note) {
            Intent deleteActivityIntent = new Intent(this, DeleteNoteActivity.class);
            startActivity(deleteActivityIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
