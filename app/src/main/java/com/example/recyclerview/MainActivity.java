package com.example.recyclerview;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    // Untuk membuat LinkedList yang menyimpan daftar dalam variabel mWorldList
    private final LinkedList<String> mWordList = new LinkedList<>();
    // Untuk mendeklarasi RecylerView menjadi mRecyclerView
    private RecyclerView mRecyclerView;
    // Untuk mendeklarasi WordListAdapter menjadi mAdapter
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // Menambahkan kata ke wordlist
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter, datanya berubah.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        // Metode untuk menambahkan mWorldList dengan awal kata 'Word' lalu diikuti dengan angka
        // i untuk inisialisasi angkanya
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Inflates the menu, dana menambahkan items ke action bar jika ada
     *
     * menu : Menu to inflate.
     * @return : Returns true jika menunya inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; menambahkan items ke action bar jika ada.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles app bar item clicks.
     *
     * item : Item clicked.
     * @return True, jika salah satu item yang ditentukan di klik
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Untuk handle action bar item clicks. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // This comment suppresses the Android Studio warning about simplifying
        // the return statements.
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}