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


public class Imp1 extends AppCompatActivity {


    private TextView to, sdat, subje, conten;
    private TextToSpeech tts;
    private int numberOfClicks;
    public static String t_id = Config.EMAIL;
    String to1 = t_id;
    public int tcount;
    //public static String stat ="Inbox";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Say first to access first favourite message,second for second message and so on.Say back to go back to options page");


                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });


        to = (TextView) findViewById(R.id.from);
        sdat = (TextView) findViewById(R.id.date);
        subje = (TextView) findViewById(R.id.sub);
        conten = (TextView) findViewById(R.id.body);

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
        //numberOfClicks++;
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
            //Toast.makeText(Sent.this, "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
        }
    }
    private void exitFromApp()
    {
        this.finishAffinity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                DatabaseHelper db = new DatabaseHelper(this);
                tcount = db.countImp(to1);
                System.out.println("Imp Count    :  " + tcount);

                if (result.get(0).equals("cancel")) {
                    speak("Cancelled!");
                    exitFromApp();
                }

                else if (result.get(0).equals("first")) {

                    if (tcount >= 1) {
                        to1 = t_id;
                        //st1 = stat;
                        // System.out.println("To1    :  " + to1);
                        //System.out.println("T_id    :  " + t_id);
                        String msg;
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.impList(to1);
                        System.out.println("MSG    :  " + msg);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 1    :  " + str[0]);
                        System.out.println("DATE  1   :  " + str[1]);
                        System.out.println("SUBJECT 1  :  " + str[2]);
                        System.out.println("CONTENT 1 :  " + str[3]);

                        to.setText(str[0]);
                        sdat.setText(str[1]);
                        subje.setText(str[2]);
                        conten.setText(str[3]);
                        speak("First message from favourites list :\n To : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Imp1.this, Imp1.class);
                        startActivity(intent);
                    }
                }

               else if (result.get(0).equals("second")) {

                    if (tcount >= 2) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                        msg = db1.impList(to1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 2    :  " + str[4]);
                        System.out.println("DATE  2   :  " + str[5]);
                        System.out.println("SUBJECT 2  :  " + str[6]);
                        System.out.println("CONTENT 2 :  " + str[7]);

                        to.setText(str[4]);
                        sdat.setText(str[5]);
                        subje.setText(str[6]);
                        conten.setText(str[7]);
                        speak("Second message from favourites list :\n To : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Imp1.this, Imp1.class);
                        startActivity(intent);
                    }
                }

                  else if (result.get(0).equals("third") || result.get(0).equals("III")) {

                    if (tcount >= 3) {
                        String to1 = t_id;
                        String msg;
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.impList(to1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 3    :  " + str[8]);
                        System.out.println("DATE  3   :  " + str[9]);
                        System.out.println("SUBJECT 3  :  " + str[10]);
                        System.out.println("CONTENT 3 :  " + str[11]);

                        to.setText(str[8]);
                        sdat.setText(str[9]);
                        subje.setText(str[10]);
                        conten.setText(str[11]);
                        speak("Third message from favourites list :\n To : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Imp1.this, Imp1.class);
                        startActivity(intent);
                    }
                }

                else if (result.get(0).equals("fourth") || result.get(0).equals("IV") || result.get(0).equals("Fort") ) {

                    if (tcount >= 4) {

                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.impList(to1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 4    :  " + str[12]);
                        System.out.println("DATE  4   :  " + str[13]);
                        System.out.println("SUBJECT 4  :  " + str[14]);
                        System.out.println("CONTENT 4 :  " + str[15]);

                        to.setText(str[12]);
                        sdat.setText(str[13]);
                        subje.setText(str[14]);
                        conten.setText(str[15]);
                        speak("Fourth message from favourites list :\n To : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Imp1.this, Imp1.class);
                        startActivity(intent);
                    }

                }
                else if (result.get(0).equals("fifth") || result.get(0).equals("v") || result.get(0).equals("V")) {
                    if (tcount >= 5) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.impList(to1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 5   :  " + str[16]);
                        System.out.println("DATE  5   :  " + str[17]);
                        System.out.println("SUBJECT 5  :  " + str[18]);
                        System.out.println("CONTENT 5 :  " + str[19]);

                        to.setText(str[16]);
                        sdat.setText(str[17]);
                        subje.setText(str[18]);
                        conten.setText(str[19]);
                        speak("Fifth message from favourites list :\n To : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Imp1.this, Imp1.class);
                        startActivity(intent);
                    }

                }

                else if (result.get(0).equals("back") || result.get(0).equals("Back")) {

                    Intent intent = new Intent(Imp1.this, Options.class);
                    startActivity(intent);
                }

                else {
                    speak("Failed to recognize voice command");
                    Intent intent = new Intent(Imp1.this, Imp1.class);
                    startActivity(intent);
                }
            }
        }
    }
}
