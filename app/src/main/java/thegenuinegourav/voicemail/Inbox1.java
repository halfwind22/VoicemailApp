
package thegenuinegourav.voicemail;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class Inbox1 extends AppCompatActivity {


    private TextView to, sdat, subje, conten;
    private TextToSpeech tts;
    private int numberOfClicks;
    public static String t_id = Config.EMAIL;
    String to1 = t_id;
   // String st1 = stat;
    public String ftrash;
    public String strash;
    public static String stat ="Inbox";
    public int tcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox1);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Say first to access first inbox message,second for second message and so on.Say delete to delete the message,important to mark as favourite,back to go back to menu");


                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });


        to = (TextView) findViewById(R.id.from);
        sdat = (TextView) findViewById(R.id.date);
        subje = (TextView) findViewById(R.id.sub);
        conten = (TextView) findViewById(R.id.body);


        String fr1= MyAsynk.fromm;
        String sd1= MyAsynk.sdate;
        String su1= MyAsynk.subj;
        String co1= MyAsynk.cont;

        String fr2= MyAsynk.fromm1;
        String sd2= MyAsynk.sdate1;
        String su2= MyAsynk.subj1;
        String co2= MyAsynk.cont1;

        String fr3= MyAsynk.fromm2;
        String sd3= MyAsynk.sdate2;
        String su3= MyAsynk.subj2;
        String co3= MyAsynk.cont2;

        String fr4= MyAsynk.fromm3;
        String sd4= MyAsynk.sdate3;
        String su4= MyAsynk.subj3;
        String co4= MyAsynk.cont3;

        String fr5= MyAsynk.fromm4;
        String sd5= MyAsynk.sdate4;
        String su5= MyAsynk.subj4;
        String co5= MyAsynk.cont4;


        String fr6= MyAsynk.fromm5;
        String sd6= MyAsynk.sdate5;
        String su6= MyAsynk.subj5;
        String co6= MyAsynk.cont5;

        String fr7= MyAsynk.fromm6;
        String sd7= MyAsynk.sdate6;
        String su7= MyAsynk.subj6;
        String co7= MyAsynk.cont6;

        String fr8= MyAsynk.fromm7;
        String sd8= MyAsynk.sdate7;
        String su8= MyAsynk.subj7;
        String co8= MyAsynk.cont7;

        String fr9= MyAsynk.fromm8;
        String sd9= MyAsynk.sdate8;
        String su9= MyAsynk.subj8;
        String co9= MyAsynk.cont8;

        String fr10= MyAsynk.fromm9;
        String sd10= MyAsynk.sdate9;
        String su10= MyAsynk.subj9;
        String co10= MyAsynk.cont9;


        /*String fr1 = to.getText().toString().trim();
        String sd1 = sdat.getText().toString().trim();
        String su1 = subje.getText().toString().trim();
        String co1 = conten.getText().toString().trim();
        String to1 = t_id;*/
        String st1 = stat;


        SharedPreferences sharedPref = getSharedPreferences("m_id", Context.MODE_PRIVATE);

        if(sharedPref.getInt("MAIL_ID",0)==0){

        }

        int mail_id=sharedPref.getInt("MAIL_ID",0);



        DatabaseHelper db = new DatabaseHelper(this);
        db.insertMail(mail_id,fr1,to1,sd1,su1,co1,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+1,fr2,to1,sd2,su2,co2,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+2,fr3,to1,sd3,su3,co3,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+3,fr4,to1,sd4,su4,co4,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+4,fr5,to1,sd5,su5,co5,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+5,fr6,to1,sd6,su6,co6,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+6,fr7,to1,sd7,su7,co7,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+7,fr8,to1,sd8,su8,co8,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+8,fr9,to1,sd9,su9,co9,st1);

        db = new DatabaseHelper(this);
        db.insertMail(mail_id+9,fr10,to1,sd10,su10,co10,st1);




        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("MAIL_ID",mail_id+10);
        editor.apply();

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
          //  Toast.makeText(Sent.this, "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
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
                // speak("Most Recent message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString()+ "\nContent : " + conten.getText().toString());

               /* String fr1 = to.getText().toString().trim();
                //String fr1 = fro.getText().toString();
                String sd1 = sdat.getText().toString().trim();
                String su1 = subje.getText().toString().trim();
                String co1 = conten.getText().toString().trim();
                String to1 = t_id;*/
                //String st1 = stat;
                // DatabaseHelper db = new DatabaseHelper(this);
                //db.insertMail(fr1,to1,sd1,su1,co1,st1);


                DatabaseHelper db = new DatabaseHelper(this);
                tcount = db.countInbox(to1);
                System.out.println("Inbox Count    :  " + tcount);

                if (result.get(0).equals("cancel")) {
                    speak("Cancelled!");
                    exitFromApp();
                }

                else if (result.get(0).equals("first")) {
                    if (tcount >= 1) {
                        to1 = t_id;
                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
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
                        speak("First message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }

                else if (result.get(0).equals("second")) {
                    if (tcount >= 2) {

                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 2    :  " + str[4]);
                        System.out.println("DATE  2   :  " + str[5]);
                        System.out.println("SUBJECT 2  :  " + str[6]);
                        System.out.println("CONTENT 2 :  " + str[7]);

                        to.setText(str[4]);
                        sdat.setText(str[5]);
                        subje.setText(str[6]);
                        conten.setText(str[7]);
                        speak("Second message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }

                else if (result.get(0).equals("third") || result.get(0).equals("III")) {
                    if (tcount >= 3) {
                   /* String fr1 = fro.getText().toString().trim();
                    //String fr1 = fro.getText().toString();
                    String sd1 = sdat.getText().toString().trim();
                    String su1 = subje.getText().toString().trim();
                    String co1 = conten.getText().toString().trim(); */
                        //String to1 = t_id;
                        //String st1 = stat;
                        int i = 1;
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        //String str="";
                        //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 3    :  " + str[8]);
                        System.out.println("DATE  3   :  " + str[9]);
                        System.out.println("SUBJECT 3  :  " + str[10]);
                        System.out.println("CONTENT 3 :  " + str[11]);

                        to.setText(str[8]);
                        sdat.setText(str[9]);
                        subje.setText(str[10]);
                        conten.setText(str[11]);
                        speak("Third message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }

                else if (result.get(0).equals("fourth") || result.get(0).equals("IV") || result.get(0).equals("Fort") ) {

                    if (tcount >= 4) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 4    :  " + str[12]);
                        System.out.println("DATE  4   :  " + str[13]);
                        System.out.println("SUBJECT 4  :  " + str[14]);
                        System.out.println("CONTENT 4 :  " + str[15]);

                        to.setText(str[12]);
                        sdat.setText(str[13]);
                        subje.setText(str[14]);
                        conten.setText(str[15]);
                        speak("Fourth message from inboxt :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }
                else if (result.get(0).equals("fifth") || result.get(0).equals("v") || result.get(0).equals("V")) {
                    if (tcount >= 5) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 5   :  " + str[16]);
                        System.out.println("DATE  5   :  " + str[17]);
                        System.out.println("SUBJECT 5  :  " + str[18]);
                        System.out.println("CONTENT 5 :  " + str[19]);

                        to.setText(str[16]);
                        sdat.setText(str[17]);
                        subje.setText(str[18]);
                        conten.setText(str[19]);
                        speak("Fifth message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }

                else if (result.get(0).equals("sixth") || result.get(0).equals("vi") || result.get(0).equals("VI")) {
                    if (tcount >= 6) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 6   :  " + str[20]);
                        System.out.println("DATE  6   :  " + str[21]);
                        System.out.println("SUBJECT 6  :  " + str[22]);
                        System.out.println("CONTENT 6 :  " + str[23]);

                        to.setText(str[20]);
                        sdat.setText(str[21]);
                        subje.setText(str[22]);
                        conten.setText(str[23]);
                        speak("Sixth message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }
                else if (result.get(0).equals("7th") || result.get(0).equals("seventh") || result.get(0).equals("7TH")) {
                    if (tcount >= 7) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 7   :  " + str[24]);
                        System.out.println("DATE  7   :  " + str[25]);
                        System.out.println("SUBJECT 7  :  " + str[26]);
                        System.out.println("CONTENT 7 :  " + str[27]);

                        to.setText(str[24]);
                        sdat.setText(str[25]);
                        subje.setText(str[26]);
                        conten.setText(str[27]);
                        speak("Seventh message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }
                else if (result.get(0).equals("it") || result.get(0).equals("8") || result.get(0).equals("eid")) {
                    if (tcount >= 8) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 8   :  " + str[28]);
                        System.out.println("DATE  8   :  " + str[29]);
                        System.out.println("SUBJECT 8  :  " + str[30]);
                        System.out.println("CONTENT 8 :  " + str[31]);

                        to.setText(str[28]);
                        sdat.setText(str[29]);
                        subje.setText(str[30]);
                        conten.setText(str[31]);
                        speak("Eighth message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }
                else if (result.get(0).equals("9th") || result.get(0).equals("9") || result.get(0).equals("nine")) {
                    if (tcount >= 9) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 9   :  " + str[32]);
                        System.out.println("DATE  9   :  " + str[33]);
                        System.out.println("SUBJECT 9  :  " + str[34]);
                        System.out.println("CONTENT 9 :  " + str[35]);

                        to.setText(str[32]);
                        sdat.setText(str[33]);
                        subje.setText(str[34]);
                        conten.setText(str[35]);
                        speak("Ninth message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }
                else if (result.get(0).equals("10th") || result.get(0).equals("tenth") || result.get(0).equals("Tenth")) {
                    if (tcount >= 10) {
                        to1 = t_id;
                        //st1 = stat;

                        String msg;
                        String st1 = "Inbox";
                        DatabaseHelper db1 = new DatabaseHelper(this);
                        msg = db1.inboxList(to1, st1);
                        String str[] = msg.split("\\\n");

                        System.out.println("FROM 10   :  " + str[36]);
                        System.out.println("DATE  10   :  " + str[37]);
                        System.out.println("SUBJECT 10  :  " + str[38]);
                        System.out.println("CONTENT 10 :  " + str[39]);

                        to.setText(str[36]);
                        sdat.setText(str[37]);
                        subje.setText(str[38]);
                        conten.setText(str[39]);
                        speak("Tenth message from inbox :\n From : " + to.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                    }
                    else
                    {
                        speak("NO MESSAGE EXISTS");
                        Intent intent = new Intent(Inbox1.this, Inbox1.class);
                        startActivity(intent);
                    }
                }




                else if (result.get(0).equals("delete") || result.get(0).equals("Delete")) {

                    Intent intent = new Intent(Inbox1.this, Trash.class);
                    startActivity(intent);
                }

                else if (result.get(0).equals("important") || result.get(0).equals("Important")) {

                    Intent intent = new Intent(Inbox1.this, Imp.class);
                    startActivity(intent);
                }


                else if (result.get(0).equals("back") || result.get(0).equals("Back")) {

                    Intent intent = new Intent(Inbox1.this, Options.class);
                    startActivity(intent);
                }

                else {
                    speak("Failed to recognize voice command");
                    Intent intent = new Intent(Inbox1.this, Sent.class);
                    startActivity(intent);
                }
            }
        }
    }
}









































/*package thegenuinegourav.voicemail;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Inbox1 extends AppCompatActivity {


    private TextView fro, sdat, subje, conten;
    private TextToSpeech tts;
    private int numberOfClicks;
    public static String t_id = Config.EMAIL;
    public static String stat ="Inbox";
    String to1 = t_id;
    String st1 = stat;
    public String ftrash;
    public String strash;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox1);




            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "This Language is not supported");
                        }
                        speak("Say first to access first inbox message,second for second message and so on.Say delete to delete a message");


                    } else {
                        Log.e("TTS", "Initilization Failed!");
                    }
                }
            });



            fro = (TextView) findViewById(R.id.from);
            sdat = (TextView) findViewById(R.id.date);
            subje = (TextView) findViewById(R.id.sub);
            conten = (TextView) findViewById(R.id.body);

        //String fr= MyAsynk.fromm;
            String fr= MyAsynk.fromm;
            String sd= MyAsynk.sdate;
            String su= MyAsynk.subj;
            String co= MyAsynk.cont;

            String frx= MyAsynk.fromm1;
            String sdx= MyAsynk.sdate1;
            String sux= MyAsynk.subj1;
            String cox= MyAsynk.cont1;

            String fry= MyAsynk.fromm2;
            String sdy= MyAsynk.sdate2;
            String suy= MyAsynk.subj2;
            String coy= MyAsynk.cont2;

            String frz= MyAsynk.fromm3;
            String sdz= MyAsynk.sdate3;
            String suz= MyAsynk.subj3;
            String coz= MyAsynk.cont3;

            String frzz= MyAsynk.fromm4;
            String sdzz= MyAsynk.sdate4;
            String suzz= MyAsynk.subj4;
            String cozz= MyAsynk.cont4;


        fro.setText(fr);
        sdat.setText(sd);
        subje.setText(su);
        conten.setText(co);

            String fr1 = fro.getText().toString().trim();
            //String fr1 = fro.getText().toString();
            String sd1 = sdat.getText().toString().trim();
            String su1 = subje.getText().toString().trim();
            String co1 = conten.getText().toString().trim();
            String to1 = t_id;
            String st1 = stat;




            SharedPreferences sharedPref = getSharedPreferences("m_id", Context.MODE_PRIVATE);

            if(sharedPref.getInt("MAIL_ID",0)==0){

            }

            int mail_id=sharedPref.getInt("MAIL_ID",0);



            DatabaseHelper db = new DatabaseHelper(this);
            db.insertMail(mail_id,fr1,to1,sd1,su1,co1,st1);

             db = new DatabaseHelper(this);
             db.insertMail(mail_id+1,frx,to1,sdx,sux,cox,st1);

             db = new DatabaseHelper(this);
             db.insertMail(mail_id+2,fry,to1,sdy,suy,coy,st1);

            db = new DatabaseHelper(this);
            db.insertMail(mail_id+3,frz,to1,sdz,suz,coz,st1);

            db = new DatabaseHelper(this);
            db.insertMail(mail_id+4,frzz,to1,sdzz,suzz,cozz,st1);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("MAIL_ID",mail_id+5);
            editor.apply();

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
            Toast.makeText(Inbox1.this, "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
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
                // speak("Most Recent message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString()+ "\nContent : " + conten.getText().toString());



                if (result.get(0).equals("cancel")) {
                    speak("Cancelled!");
                    exitFromApp();
                } else if (result.get(0).equals("first"))
                {
                    speak("First message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                }

                else if (result.get(0).equals("second")) {

                    to1 = t_id;
                    st1 = stat;

                   /* String msg;
                    DatabaseHelper db1 = new DatabaseHelper(this);
                    //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                    msg = db1.inboxList(to1, st1);
                    String str[] = msg.split("\\\n");*/

                /*    System.out.println("FROM 2    :  " + MyAsynk.fromm1);
                    System.out.println("DATE  2   :  " + MyAsynk.sdate1);
                    System.out.println("SUBJECT 2  :  " + MyAsynk.subj1);
                    System.out.println("CONTENT 2 :  " + MyAsynk.cont1);

                    fro.setText(MyAsynk.fromm1);
                    sdat.setText(MyAsynk.sdate1);
                    subje.setText(MyAsynk.subj1);
                    conten.setText(MyAsynk.cont1);
                    speak("Second message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                }

                else if (result.get(0).equals("third") || result.get(0).equals("III")) {
                   /* String fr1 = fro.getText().toString().trim();
                    //String fr1 = fro.getText().toString();
                    String sd1 = sdat.getText().toString().trim();
                    String su1 = subje.getText().toString().trim();
                    String co1 = conten.getText().toString().trim(); */
                    //String to1 = t_id;
                    //String st1 = stat;
                 /*   int i = 1;
                    to1 = t_id;
                    st1 = stat;

                   /* String msg;
                    //String str="";
                    DatabaseHelper db1 = new DatabaseHelper(this);
                    //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                    msg = db1.inboxList(to1, st1);
                    String str[] = msg.split("\\\n");*/

                  /*  System.out.println("FROM 3    :  " + MyAsynk.fromm2);
                    System.out.println("DATE  3   :  " + MyAsynk.sdate2);
                    System.out.println("SUBJECT 3  :  " + MyAsynk.subj2);
                    System.out.println("CONTENT 3 :  " + MyAsynk.cont2);

                    fro.setText(MyAsynk.fromm2);
                    sdat.setText(MyAsynk.sdate2);
                    subje.setText(MyAsynk.subj2);
                    conten.setText(MyAsynk.cont2);
                    speak("Third message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                }

                else if (result.get(0).equals("fourth") || result.get(0).equals("IV") || result.get(0).equals("Fort") ) {

                    int i = 1;
                    to1 = t_id;
                    st1 = stat;

                   /* String msg;
                    //String str="";
                    DatabaseHelper db1 = new DatabaseHelper(this);
                    //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                    msg = db1.inboxList(to1, st1);
                    String str[] = msg.split("\\\n");*/

         /*           System.out.println("FROM 4    :  " + MyAsynk.fromm3);
                    System.out.println("DATE  4   :  " + MyAsynk.sdate3);
                    System.out.println("SUBJECT 4  :  " + MyAsynk.subj3);
                    System.out.println("CONTENT 4 :  " + MyAsynk.cont3);

                    fro.setText(MyAsynk.fromm3);
                    sdat.setText(MyAsynk.sdate3);
                    subje.setText(MyAsynk.subj3);
                    conten.setText(MyAsynk.cont3);
                    speak("Fourth message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                }

                else if (result.get(0).equals("fifth") || result.get(0).equals("v") || result.get(0).equals("V")) {

                    int i = 1;
                    to1 = t_id;
                    st1 = stat;

                   /* String msg;
                    //String str="";
                    DatabaseHelper db1 = new DatabaseHelper(this);
                    //db.inboxList(fr1,to1,sd1,su1,co1,st1);
                    msg = db1.inboxList(to1, st1);
                    String str[] = msg.split("\\\n");*/

           /*         System.out.println("FROM 5    :  " + MyAsynk.fromm4);
                    System.out.println("DATE  5   :  " + MyAsynk.sdate4);
                    System.out.println("SUBJECT 5  :  " + MyAsynk.subj4);
                    System.out.println("CONTENT 5 :  " + MyAsynk.cont4);

                    fro.setText(MyAsynk.fromm4);
                    sdat.setText(MyAsynk.sdate4);
                    subje.setText(MyAsynk.subj4);
                    conten.setText(MyAsynk.cont4);
                    speak("Fifth message from inbox :\n From : " + fro.getText().toString() + "\nDate : " + sdat.getText().toString() + "\nSubject : " + subje.getText().toString() + "\nContent : " + conten.getText().toString());
                }

                /*else {
                                speak("Please Restart the app to reset");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exitFromApp();
                        }
                    }, 4000);
                            }*/

          /*      else if (result.get(0).equals("delete") || result.get(0).equals("Delete")) {

                    /*speak("Say from address of the mailer whose message you want to delete");
                     ftrash = result.get(1);
                    speak("Say subject of the mail whose message you want to delete");
                    strash = result.get(2);

                    System.out.println("FTRASH    :  " +ftrash);
                    System.out.println("STRASH    :  " +strash);

                    DatabaseHelper db1 = new DatabaseHelper(this);
                    db1.trashMail(ftrash,strash);*/

             /*       Intent intent = new Intent(Inbox1.this, Trash.class);
                    startActivity(intent);
                }
                else if (result.get(0).equals("back") || result.get(0).equals("Back")) {

                    Intent intent = new Intent(Inbox1.this, Options.class);
                    startActivity(intent);
                }


                else {
                    speak("Failed to recognize voice command");

                    Intent intent = new Intent(Inbox1.this, Inbox1.class);
                    startActivity(intent);
                }
            }
            }
        }
}
*/