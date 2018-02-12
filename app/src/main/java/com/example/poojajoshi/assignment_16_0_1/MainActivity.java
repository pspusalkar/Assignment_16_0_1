package com.example.poojajoshi.assignment_16_0_1;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    File file;
    String fileName = "text.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // create file on external storage
        file = new File(Environment.getExternalStorageDirectory(), fileName);
        // file = new File(getFilesDir(), fileName);

        try {
            if (file.createNewFile()) {
                Toast.makeText(getApplicationContext(), "File Created",
                        Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // get the text handles
        final EditText editText = findViewById(R.id.editText_data);
        final TextView showTextView = findViewById(R.id.textView_fileData);

        // get add button handle and set onclick listener.
        // add button saves the text to file on external storage
        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileReadWrite f = new FileReadWrite(file, "", showTextView);
                String userValue = editText.getText().toString();
                f.execute(userValue);
            }
        });

        // get delete button handle and set onclick listener.
        // delete button deletes the file from external storage
        Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete the file.
                boolean isDeleted = file.delete();
                if ( isDeleted )
                    Toast.makeText(getApplicationContext(), "File Deleted", Toast.LENGTH_SHORT).show();

                // clear the text
                showTextView.setText("");
                editText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
