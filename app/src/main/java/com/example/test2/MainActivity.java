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
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextView textView,textview2;
    Button record,send;

    SpeechRecognizer recognizer;
    Intent i;
    TextToSpeech t1;

    DatabaseReference reference,set,get;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        reference= FirebaseDatabase.getInstance().getReference();
        set = reference.child("set");





        send=findViewById(R.id.send);
        textView=findViewById(R.id.tx);
        textview2=findViewById(R.id.tx2);
        record=findViewById(R.id.record);
        recognizer=SpeechRecognizer.createSpeechRecognizer(this);





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textView.getText().toString()!=null) {
                    set.setValue(textView.getText().toString());
                }else {

                    Toast.makeText(MainActivity.this, new Exception().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });






        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status != TextToSpeech.ERROR){


                    t1.setLanguage(Locale.getDefault());
                }

            }
        });



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

                if (matches != null)
                    textView.setText(matches.get(0));




            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });






        record.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){

                    case MotionEvent.ACTION_UP:
                        record.setBackgroundResource(R.drawable.stop);
                        recognizer.stopListening();
                        textview2.setVisibility(View.GONE);
                        textView.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        record.setBackgroundResource(R.drawable.start);
                        recognizer.startListening(i);
                        textView.setText("");
                        textview2.setVisibility(View.VISIBLE);
                        textview2.setText("Listening...");
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
