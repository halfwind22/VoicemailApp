package thegenuinegourav.voicemail;

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

import java.util.ArrayList;
import java.util.Locale;

public class Trash extends AppCompatActivity {

    //public static String EMAIL="balupradeep41@gmail.com";  //gmail address
    // public static String PASSWORD="balu4434"; //password


    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextToSpeech tts;
    private TextView ft, st;
    private int noc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Say from address of the mailer whose message you want to delete");

                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });


        ft = (TextView) findViewById(R.id.id1);
        st = (TextView) findViewById(R.id.id2);
        noc=0;
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
        noc++;
        listen();
    }

    private void listen(){
        Intent j = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        j.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        j.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        j.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say anything");

        try {
            startActivityForResult(j, 100);
        } catch (ActivityNotFoundException a) {
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
                    switch (noc) {
                        case 1:
                            String f;
                            f = result.get(0).replaceAll("underscore","_");
                            f = f.replaceAll("\\s+","");
                            f = f.toLowerCase();
                            f = f + "@gmail.com";
                            ft.setText(f);
                            speak("Say subject of the mail whose message you want to delete");
                            break;
                        case 2:
                            String s;
                            s = result.get(0);
                            st.setText(s);
                            speak("Please Confirm the trash details \n from : " + ft.getText().toString() + "\nSubject : " + st.getText().toString() + "\nSpeak Yes to confirm or no to reenter");
                            break;
                        case 3:
                            if(result.get(0).equals("yes"))
                            {
                                String ftrash = ft.getText().toString().trim();
                                String strash = st.getText().toString().trim();
                                System.out.println("FTRASH    :  " +ftrash);
                                System.out.println("STRASH    :  " +strash);

                                DatabaseHelper db1 = new DatabaseHelper(this);
                                db1.trashMail(ftrash,strash);
                                speak("Message Deleted");
                                Intent intent = new Intent(Trash.this, Options.class);
                                startActivity(intent);
                            }
                            else if(result.get(0).equals("no")) {
                                Intent intent = new Intent(Trash.this, Trash.class);
                                startActivity(intent);
                            }
                            else
                            {
                                speak("Failed to recognize voice command");
                                Intent intent = new Intent(Trash.this, Trash.class);
                                startActivity(intent);
                            }
                    }
                }
            }
        }
    }




}


