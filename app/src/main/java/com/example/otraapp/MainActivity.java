package com.example.otraapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public final static String MAIN_WORD = "HOLA MUNDO";
    private final int REQUEST_ACTIVITY_TWO = 3;
    private final int OK = -1;
    private final String TAG = getClass().getName();
    private final String PARAM = "PARAMETER";
    private int currentTime = 0;
    private TimerTask mtimer = null;
    private Timer myTimer = null;
    private TextView mtext = null;
    Button btnPermissions = null;
    MainActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.botonUno);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(MAIN_WORD, String.valueOf(MAIN_WORD));
                startActivityForResult(intent, REQUEST_ACTIVITY_TWO);
                Intent senData = new Intent(getApplicationContext(),MainActivity2.class);
                senData.putExtra("SEND",currentTime);
                startActivity(senData);

            }
        });
        Button btn2 = (Button) findViewById(R.id.botonGoogle);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent google = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(R.string.uriNav)));
                startActivity(google);
            }
        });
        mtext = findViewById(R.id.textoCron);
        btnPermissions = findViewById(R.id.btnPermisos);
        btnPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        launchTask();

    }

    //-1 si todo ha ido bien, 0 si algo ha ido mal.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Este es el resultado: ".concat(String.valueOf(resultCode)));
        Log.d(TAG, "Este es el resultado: ".concat(String.valueOf(Activity.RESULT_OK)));
        if (resultCode == Activity.RESULT_OK) {
            int value = data.getIntExtra(PARAM, -1);
            if (requestCode == REQUEST_ACTIVITY_TWO) {
                Toast.makeText(getApplicationContext(), "Vengo del Activity 2", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), data.getStringExtra(MAIN_WORD), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No vengo de ninguna activity", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "No vengo de ninguna activity");
            }
        }
    }

    public void chronometer() {
        mtimer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentTime++;

                        launchTaskCrono();

                    }
                });
            }
        };
        myTimer = new Timer();
        myTimer.schedule(mtimer, 1, 1000);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    public void launchTaskCrono() {
        MyAsyncTask taskCrono = new MyAsyncTask();
        taskCrono.setContext();
        taskCrono.execute();
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, Void> {
        private ProgressDialog mprogress = null;
        private boolean showProgressDialog = true;
        private Context mcontext;

        public void setContext () {
            mcontext=getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogress= new ProgressDialog(activity);
            mprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mprogress.setTitle("Download....");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mprogress.setMessage("Download values....");
            mprogress.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            if (showProgressDialog){
                publishProgress();
            }
            showProgressDialog=false;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mprogress.dismiss();
            mtext.setText(String.valueOf(currentTime));
            Toast.makeText(getApplicationContext(), "Async Task ended", Toast.LENGTH_SHORT).show();
        }
    }

    public void launchTask() {
        MyAsyncTask mytask = new MyAsyncTask();
        mytask.setContext();
        mytask.execute();
    }
}