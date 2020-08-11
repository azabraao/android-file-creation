package com.example.fileexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    private static final String FILE_PATH = "banco.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edtText);
    }

    public void salvar(View view) {
        try {
            FileOutputStream fos = openFileOutput(FILE_PATH, MODE_PRIVATE);
            String text = editText.getText().toString() + "\n";

            fos.write(text.getBytes());

            fos.close();

            editText.setText("");
        } catch (FileNotFoundException fnfe) {
            Toast.makeText(this, R.string.file_not_found, Toast.LENGTH_SHORT).show();
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            Toast.makeText(this, R.string.could_not_write, Toast.LENGTH_SHORT).show();
            ioe.printStackTrace();
        }
    }

    public void recuperar(View view) {
        try {
            FileInputStream fis = openFileInput(FILE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String txt = br.readLine();
            fis.close();
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException fnfe) {
            Toast.makeText(this,R.string.file_not_found, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}