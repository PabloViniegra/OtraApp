package com.example.otraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String aux = intent.getStringExtra(MainActivity.MAIN_WORD);
        TextView text = findViewById(R.id.miTexto);
        text.setText(aux);
        Toast.makeText(getApplicationContext(), aux, Toast.LENGTH_SHORT).show();
        ImageView img = findViewById(R.id.estaImagen);
        img.setImageURI(Uri.parse("https://cde.laprensa.e3.pe/ima/0/0/2/3/8/238082.jpg"));

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.MAIN_WORD,aux);
        setResult(RESULT_OK,returnIntent);
        Intent getData = getIntent();
        Toast.makeText(getApplicationContext(), String.valueOf(getData.getIntExtra("SEND",1)), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}