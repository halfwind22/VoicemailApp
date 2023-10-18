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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Config extends AppCompatActivity {

   //public static String EMAIL="balupradeep41@gmail.com";  //gmail address
   // public static String PASSWORD="balu4434"; //password

    public static String EMAIL;  //gmail address
    public static String PASSWORD; //password
    public static String SCC;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextToSpeech tts;
    private TextView mail_id, pass,sc;
    private int noc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Whats the user mail id?");



                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });


        mail_id = (TextView) findViewById(R.id.id1);
        pass = (TextView) findViewById(R.id.id2);
        sc = (TextView) findViewById(R.id.id3);
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
            Toast.makeText(Config.this, "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
        }
    }

   /* private void sendEmail() {
        //Getting content for email
       /* String email = To.getText().toString().trim();
        String subject = Subject.getText().toString().trim();
        String message = Message.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
*/
   private void Mailinbox() {
       //String fromm,sdate,subj,cont;
       MyAsynk inbox = new MyAsynk(this);
       inbox.execute();
       speak("Entered to My Asynk");
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
                            String from;
                            from= result.get(0).replaceAll("underscore","_");
                            from = from.replaceAll("\\s+","");
                            from = from.toLowerCase();
                            from = from + "@gmail.com";
                            mail_id.setText(from);
                            EMAIL=from;  //gmail address
                            speak("Whats the password?");
                            break;
                        case 2:
                            String pass1;
                            pass1= result.get(0).replaceAll("underscore","_");
                            pass1 = pass1.replaceAll("\\s+","");
                            PASSWORD=pass1; //password
                            pass.setText(pass1);
                            speak("Whats the secret code?");
                            break;
                        case 3:
                            String sc1;
                            sc1= result.get(0);
                            sc1 = sc1.replaceAll("\\s+","");
                            SCC = sc1;
                            sc.setText(sc1);
                            speak("Please Confirm the login details \n User id : " + mail_id.getText().toString() + "\nPassword : " + pass.getText().toString() + "\nSpeak Yes to confirm or no to reenter");
                            break;
                        case 4:
                            if(result.get(0).equals("yes")) {
                                //Mailinbox();
                                String b_name = mail_id.getText().toString().trim();
                                String pass, sec;
                                DatabaseHelper db = new DatabaseHelper(this);
                                pass = db.validate(b_name);
                                sec = db.validatesc(b_name);
                                if (PASSWORD.equals(pass) && SCC.equals(sec)) {
                                    Intent intent = new Intent(Config.this, Options.class);
                                    startActivity(intent);
                                } else {
                                        speak("You are not a Registered User,You can register now");
                                        Intent intent = new Intent(Config.this, Register.class);
                                        startActivity(intent);
                                       }
                            }
                           else if(result.get(0).equals("no")) {
                                Intent intent = new Intent(Config.this, Config.class);
                                startActivity(intent);
                            }
                            else
                            {
                                speak("Failed to recognize voice command Try Again");
                                Intent intent = new Intent(Config.this, Config.class);
                                startActivity(intent);
                            }
                    }
                }
            }
        }
    }




}

