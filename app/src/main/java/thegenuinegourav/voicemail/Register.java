package thegenuinegourav.voicemail;
//package com.example.balupradeep.voicemail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Register extends AppCompatActivity {

    private TextToSpeech tts;
    private TextView status;
    private TextView uname,dob,phno,pass,conpass,sc;
    private int numberOfClicks;
    private String pass3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Welcome to Registration Page.Whats the user name?");


                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });

        uname = (TextView)findViewById(R.id.txtname);
        dob = (TextView) findViewById(R.id.txtdob);
        phno  =(TextView)findViewById(R.id.txtphno);
        pass  =(TextView)findViewById(R.id.txtpass);
        conpass = (TextView) findViewById(R.id.cpass);
        sc = (TextView) findViewById(R.id.sc1);
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
            Toast.makeText(Register.this, "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
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
                if(result.get(0).equals("back")||result.get(0).equals("Back"))
                {
                   // speak("Failed to recognize voice command Try Again");
                    Intent intent = new Intent(Register.this, Config.class);
                    startActivity(intent);
                }
                else {
                    switch (numberOfClicks) {
                        case 1:
                            String name;
                            name= result.get(0).replaceAll("underscore","_");
                            name = name.replaceAll("\\s+","");
                            name = name.toLowerCase();
                            name = name + "@gmail.com";
                            uname.setText(name);
                            speak("Date of Birth in Day Month Year format?");
                            break;

                        case 2:
                            dob.setText(result.get(0));
                            speak("Phone Number");
                            break;
                        case 3 :
                            phno.setText(result.get(0));
                            speak("speak out 20 digit Secret Code");
                            break;
                        case 4:
                            String sc1;
                            sc1= result.get(0);
                            sc1 = sc1.replaceAll("\\s+","");
                            sc.setText(sc1);
                            speak("Password");
                            break;

                        case 5 :
                            String pass1;
                            pass1= result.get(0).replaceAll("underscore","_");
                            pass1 = pass1.replaceAll("\\s+","");
                            pass.setText(pass1);
                            pass3=pass1;
                            speak("Confirm your Password");
                            break;
                        case 6:
                            String pass2;
                            pass2= result.get(0).replaceAll("underscore","_");
                            pass2 = pass2.replaceAll("\\s+","");
                            if(pass3.equals(pass2)) {
                                conpass.setText(pass2);
                                speak("Please Confirm the Registration Details\n User Mail ID : " + uname.getText().toString() + "\nDate of Birth : " + dob.getText().toString() + "\nPhone Number : " + phno.getText().toString() +"\nSecret Code : " + sc.getText().toString() + "\nPassword : " + pass.getText().toString() + "\nSpeak Yes to confirm or No to reenter");
                            }
                            else {
                                speak("Confirm Password Failed");
                                Intent intent = new Intent(Register.this, Register.class);
                                startActivity(intent);
                            }
                                break;
                        case 7:
                            if(result.get(0).equals("yes"))
                            {

                                String b_name = uname.getText().toString().trim();
                                String b_dob = dob.getText().toString().trim();
                                String b_phone = phno.getText().toString().trim();
                                String b_pass = pass.getText().toString().trim();
                                String b_sc = sc.getText().toString().trim();


                                DatabaseHelper db = new DatabaseHelper(this);
                                db.insertUser(b_name, b_dob, b_phone,b_pass,b_sc);
                                speak("Registration completed successfully");
                                //DatabaseHelper db1 = new DatabaseHelper(this);
                                db.showUsers();
                                Intent intent = new Intent(Register.this,Config.class);
                                startActivity(intent);
                            }
                           else if(result.get(0).equals("no"))
                            {
                                Intent intent = new Intent(Register.this,Register.class);
                                startActivity(intent);
                            }
                            else
                            {
                                speak("Failed to recognize voice command Try Again");
                                Intent intent = new Intent(Register.this, Register.class);
                                startActivity(intent);
                            }
                    }
                }
            }
        }
    }
}
