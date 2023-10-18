package thegenuinegourav.voicemail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


//new MyAsynk().execute();

public class Inbox extends AppCompatActivity {

    private TextView fro, sdat, subje, conten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        fro = (TextView) findViewById(R.id.from);
        sdat = (TextView) findViewById(R.id.date);
        subje = (TextView) findViewById(R.id.sub);
        conten = (TextView) findViewById(R.id.body);
        //Intent intent = new Intent(Inbox.this,MyAsynk.class);
        //startActivity(intent);

        // private void Mailinbox() {
        //String fromm,sdate,subj,cont;
        MyAsynk inbox = new MyAsynk(this);
        inbox.execute();


        //String fr= MyAsynk.fromm;
       /* String fr= inbox.fromm;
        String sd= inbox.sdate;
        String su= inbox.subj;
        String co= inbox.cont;
        fro.setText(fr);
        sdat.setText(sd);
        subje.setText(su);
        conten.setText(co);*/
    }
}