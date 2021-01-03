package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class te extends AppCompatActivity {







    SpeechRecognizer recognizer;
    Intent i;
    TextView input,output,Listening;
    Button button;
    String text;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_te);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkPermission();

        output=findViewById(R.id.output);
        input = findViewById(R.id.input);
        Listening = findViewById(R.id.Listening);
        button=findViewById(R.id.button);

        recognizer = SpeechRecognizer.createSpeechRecognizer(this);


        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());


        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {


            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {


                ArrayList<String> matches = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);


                text = matches.get(0);
                input.setText(text);

                if (matches.get(0).equals("welcome")) {


                    output.setText("hi");

                }


            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });





        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){


                    case MotionEvent.ACTION_DOWN:

                        button.setBackgroundResource(R.drawable.start);
                        recognizer.startListening(i);
                        input.setText("");
                        output.setVisibility(View.GONE);
                        Listening.setVisibility(View.VISIBLE);

                        break;

                    case MotionEvent.ACTION_UP:

                        button.setBackgroundResource(R.drawable.stop);
                        recognizer.stopListening();
                        Listening.setVisibility(View.GONE);
                        output.setVisibility(View.VISIBLE);
                        input.setHint("You will see input here");

                        break;



                }

                return false;


            }
        });






















        }
















    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }











}