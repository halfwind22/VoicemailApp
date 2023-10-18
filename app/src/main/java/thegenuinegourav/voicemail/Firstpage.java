package thegenuinegourav.voicemail;
//package com.example.balupradeep.voicemail;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Firstpage extends AppCompatActivity {

    private TextToSpeech tts;
    private int numberOfClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);


        SharedPreferences sharedPref = getSharedPreferences("m_id", Context.MODE_PRIVATE);

        if(sharedPref.getInt("MAIL_ID",0)==0){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("MAIL_ID",3000);
            editor.apply();
        }





        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Welcome to voice based email system for visually impaired,Are you a new user or an existing user?");



                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });
        numberOfClicks = 0;
    }

    private void speak(String text){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void layoutClicked(View view)
    {
        numberOfClicks++;
        listen();
    }

    private void listen(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try {
            startActivityForResult(i, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(Firstpage.this, "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
        }
    }

    private void exitFromApp()
    {
        this.finishAffinity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if(result.get(0).equals("cancel"))
                {
                    speak("Cancelled!");
                    exitFromApp();
                }
                else {
                    switch (numberOfClicks) {
                        case 1:
                            //speak("Are you a new user or an existing user?");
                            if((result.get(0).equals("new user"))||(result.get(0).equals("new")))
                            {
                                Intent intent = new Intent(Firstpage.this,Register.class);
                                startActivity(intent);
                            }
                            else if((result.get(0).equals("existing user"))||(result.get(0).equals("existing")))
                            {
                                Intent intent = new Intent(Firstpage.this,Config.class);
                                startActivity(intent);
                            }
                            else
                            {
                                speak("Failed to recognize voice command Try Again ");
                                Intent intent = new Intent(Firstpage.this, Firstpage.class);
                                startActivity(intent);
                            }
                    }
                }
            }
        }
    }
}
