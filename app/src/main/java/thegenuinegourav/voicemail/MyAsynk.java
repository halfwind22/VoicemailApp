package thegenuinegourav.voicemail;

/**
 * Created by Balu Pradeep on 26-Mar-18.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;


//new MyAsynk().execute();

//public class MyAsynk extends AsyncTask<Void, Void, Void> {

public class MyAsynk extends AsyncTask<String, String, String > {

    public static String fromm,fromm1,fromm2,fromm3,fromm4,fromm5,fromm6,fromm7,fromm8,fromm9;
    public static String sdate,sdate1,sdate2,sdate3,sdate4,sdate5,sdate6,sdate7,sdate8,sdate9;
    public static String subj,subj1,subj2,subj3,subj4,subj5,subj6,subj7,subj8,subj9;
    public static String cont,cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9;
    public static String mail = Config.EMAIL;
    public static String pass = Config.PASSWORD;
    String str="";
    String str1="";
    String str2="";
    String str3="";
    String str4="";
    String str5="";
    String str6="";
    String str7="";
    String str8="";
    String str9="";

    public static int flag = 0;
    private Context context;
    private ProgressDialog progressDialog;

    public MyAsynk(Context context){
        this.context=context;
    }



    @Override
    protected String doInBackground(String... params) {

        // public AsyncTask(context, String fromm, String sdate, String subj){
        if (flag == 0) {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            try {
                Session session = Session.getInstance(props, null);
                Store store = session.getStore();
                //store.connect("imap.gmail.com", "balupradeep41@gmail.com", "balu4434");
                store.connect("imap.gmail.com",mail,pass);
                Folder inbox = store.getFolder("INBOX");
                //Folder inbox = store.getFolder("SENT");
                inbox.open(Folder.READ_ONLY);

                javax.mail.Message msg9 = inbox.getMessage(inbox.getMessageCount()-9);
                javax.mail.Message msg8 = inbox.getMessage(inbox.getMessageCount()-8);
                javax.mail.Message msg7 = inbox.getMessage(inbox.getMessageCount()-7);
                javax.mail.Message msg6 = inbox.getMessage(inbox.getMessageCount()-6);
                javax.mail.Message msg5 = inbox.getMessage(inbox.getMessageCount()-5);

                javax.mail.Message msg4 = inbox.getMessage(inbox.getMessageCount()-4);
                javax.mail.Message msg3 = inbox.getMessage(inbox.getMessageCount()-3);
                javax.mail.Message msg2= inbox.getMessage(inbox.getMessageCount()-2);
                javax.mail.Message msg1= inbox.getMessage(inbox.getMessageCount()-1);
                javax.mail.Message msg= inbox.getMessage(inbox.getMessageCount());

                javax.mail.Address[] in = msg9.getFrom();
                for (javax.mail.Address address : in) {
                    //System.out.println("FROM:" + address.toString());

                    fromm = address.toString();
                    fromm=fromm.trim();
                    String []f=fromm.split("<");
                    fromm=f[1];
                    fromm=fromm.replace(">","").trim();

                }
                Multipart mp = (Multipart) msg9.getContent();
                BodyPart bp = mp.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate = msg9.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj = msg9.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont = bp.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str = str + fromm + "\n" + sdate + "\n" + subj + "\n" + cont;


                javax.mail.Address[] in1 = msg8.getFrom();
                for (javax.mail.Address address : in1) {
                    //System.out.println("FROM:" + address.toString());
                    fromm1 = address.toString();
                    fromm1=fromm1.trim();
                    String []f=fromm1.split("<");
                    fromm1=f[1];
                    fromm1=fromm1.replace(">","").trim();
                }
                Multipart mp1 = (Multipart) msg8.getContent();
                BodyPart bp1 = mp1.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate1 = msg8.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj1 = msg8.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont1 = bp1.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str1 = str1 + fromm1 + "\n" + sdate1 + "\n" + subj1 + "\n" + cont1;

                javax.mail.Address[] in2 = msg7.getFrom();
                for (javax.mail.Address address : in2) {
                    //System.out.println("FROM:" + address.toString());
                    fromm2 = address.toString();
                    fromm2=fromm2.trim();
                    String []f=fromm2.split("<");
                    fromm2=f[1];
                    fromm2=fromm2.replace(">","").trim();
                }
                Multipart mp2 = (Multipart) msg7.getContent();
                BodyPart bp2 = mp2.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate2 = msg7.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj2 = msg7.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont2 = bp2.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str2 = str2 + fromm2 + "\n" + sdate2 + "\n" + subj2 + "\n" + cont2;

                javax.mail.Address[] in3 = msg6.getFrom();
                for (javax.mail.Address address : in3) {
                    //System.out.println("FROM:" + address.toString());
                    fromm3 = address.toString();
                    fromm3=fromm3.trim();
                    String []f=fromm3.split("<");
                    fromm3=f[1];
                    fromm3=fromm3.replace(">","").trim();
                }
                Multipart mp3 = (Multipart) msg6.getContent();
                BodyPart bp3 = mp3.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate3 = msg6.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj3 = msg6.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont3 = bp3.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str3 = str3 + fromm3 + "\n" + sdate3 + "\n" + subj3 + "\n" + cont3;

                javax.mail.Address[] in4 = msg5.getFrom();
                for (javax.mail.Address address : in4) {
                    //System.out.println("FROM:" + address.toString());
                    fromm4 = address.toString();
                    fromm4=fromm4.trim();
                    String []f=fromm4.split("<");
                    fromm4=f[1];
                    fromm4=fromm4.replace(">","").trim();
                }
                Multipart mp4 = (Multipart) msg5.getContent();
                BodyPart bp4 = mp4.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate4 = msg5.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj4 = msg5.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont4 = bp4.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str4 = str4 + fromm4 + "\n" + sdate4 + "\n" + subj4 + "\n" + cont4;


                javax.mail.Address[] in5 = msg4.getFrom();
                for (javax.mail.Address address : in5) {
                    //System.out.println("FROM:" + address.toString());
                    fromm5= address.toString();
                    fromm5=fromm5.trim();
                    String []f=fromm5.split("<");
                    fromm5=f[1];
                    fromm5=fromm5.replace(">","").trim();
                }
                Multipart mp5 = (Multipart) msg4.getContent();
                BodyPart bp5 = mp5.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate5 = msg4.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj5 = msg4.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont5 = bp5.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str5 = str5 + fromm5 + "\n" + sdate5 + "\n" + subj5 + "\n" + cont5;


                javax.mail.Address[] in6 = msg3.getFrom();
                for (javax.mail.Address address : in6) {
                    //System.out.println("FROM:" + address.toString());
                    fromm6 = address.toString();
                    fromm6=fromm6.trim();
                    String []f=fromm6.split("<");
                    fromm6=f[1];
                    fromm6=fromm6.replace(">","").trim();
                }
                Multipart mp6 = (Multipart) msg3.getContent();
                BodyPart bp6 = mp6.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate6 = msg3.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj6 = msg3.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont6 = bp6.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str6 = str6 + fromm6 + "\n" + sdate6 + "\n" + subj6 + "\n" + cont6;



                javax.mail.Address[] in7 = msg2.getFrom();
                for (javax.mail.Address address : in7) {
                    //System.out.println("FROM:" + address.toString());
                    fromm7 = address.toString();
                    fromm7=fromm7.trim();
                    String []f=fromm7.split("<");
                    fromm7=f[1];
                    fromm7=fromm7.replace(">","").trim();
                }
                Multipart mp7 = (Multipart) msg2.getContent();
                BodyPart bp7 = mp7.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate7 = msg2.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj7 = msg2.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont7 = bp7.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str7 = str7 + fromm7 + "\n" + sdate7 + "\n" + subj7 + "\n" + cont7;




                javax.mail.Address[] in8 = msg1.getFrom();
                for (javax.mail.Address address : in8) {
                    //System.out.println("FROM:" + address.toString());
                    fromm8 = address.toString();
                    fromm8=fromm8.trim();
                    String []f=fromm8.split("<");
                    fromm8=f[1];
                    fromm8=fromm8.replace(">","").trim();
                }
                Multipart mp8 = (Multipart) msg1.getContent();
                BodyPart bp8 = mp8.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate8 = msg1.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj8 = msg1.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont8 = bp8.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str8 = str8 + fromm8 + "\n" + sdate8 + "\n" + subj8 + "\n" + cont8;




                javax.mail.Address[] in9 = msg.getFrom();
                for (javax.mail.Address address : in9) {
                    //System.out.println("FROM:" + address.toString());
                    fromm9 = address.toString();
                    fromm9=fromm9.trim();
                    String []f=fromm9.split("<");
                    fromm9=f[1];
                    fromm9=fromm9.replace(">","").trim();
                }
                Multipart mp9 = (Multipart) msg.getContent();
                BodyPart bp9 = mp9.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                sdate9 = msg.getSentDate().toString().trim();
                //System.out.println("SUBJECT:" + msg.getSubject());
                subj9 = msg.getSubject();
                //System.out.println("CONTENT:" + bp.getContent());
                cont9 = bp9.getContent().toString().trim();

                // Inbox inb = new Inbox();
                //inb.showInbox(fromm, sdate, subj, cont);
                //inb.showInbox();

                //Intent intent = new Intent(MyAsynk.this,Inbox.class);
                //intent.putExtra()
                str9 = str9 + fromm9 + "\n" + sdate9 + "\n" + subj9 + "\n" + cont9;






                str = str  + "\n" + str1 + "\n" + str2 + "\n" + str3 + "\n"+ str4 + "\n"+ str5 + "\n" + str6 + "\n" + str7 + "\n"+ str8 + "\n" + str9 + "\n";


            } catch (Exception mex) {
                mex.printStackTrace();
            }

            //return str;
            //return cont;

        }
        return str;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Retrieving Inbox messages","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();

        String str[]=aVoid.split("\\\n");
        //System.out.println("FROM :" +aVoid);
       /* System.out.println("FROM     :  " +str[0]);
        System.out.println("DATE     :  " +str[1]);
        System.out.println("SUBJECT  :  " +str[2]);
        System.out.println("CONTENT  :  " +str[3]);

        System.out.println("FROM2    :  " +str[4]);
        System.out.println("DATE2     :  " +str[5]);
        System.out.println("SUBJECT2  :  " +str[6]);
        System.out.println("CONTENT2  :  " +str[7]);

        System.out.println("FROM3     :  " +str[8]);
        System.out.println("DATE3     :  " +str[9]);
        System.out.println("SUBJECT3  :  " +str[10]);
        System.out.println("CONTENT3  :  " +str[11]);

        System.out.println("FROM4     :  " +str[12]);
        System.out.println("DATE4     :  " +str[13]);
        System.out.println("SUBJECT4  :  " +str[14]);
        System.out.println("CONTENT4  :  " +str[15]);

        System.out.println("FROM5     :  " +str[16]);
        System.out.println("DATE5     :  " +str[17]);
        System.out.println("SUBJECT5  :  " +str[18]);
        System.out.println("CONTENT5  :  " +str[19]);*/

        System.out.println("User id:  :  "+Config.EMAIL);
        System.out.println("User password:  :  "+Config.PASSWORD);
        Intent intent = new Intent(context,Inbox1.class);
        context.startActivity(intent);
        ((Activity)context).finish();


        //Inbox inb = new Inbox();
        //inb.showInbox(fromm, sdate, subj, cont);
        //inb.showInbox(cont);


    }

}