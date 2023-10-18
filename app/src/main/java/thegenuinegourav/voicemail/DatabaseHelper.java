package thegenuinegourav.voicemail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "bmail.db";
    public static final String TABLE_NAME = "register";
    public static final String TABLE_NAME2 = "mail";
    public static final String COL_1 = "u_id";
    public static final String COL_2 = "u_name";
    public static final String COL_3 = "u_dob";
    public static final String COL_4 = "u_phone";
    public static final String COL_5 = "u_pass";
    public int no = 100;
    public int no1 = 2000;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table  if not exists " + TABLE_NAME + "(u_id integer auto increment,u_name varchar(80) primary key,u_dob varchar(80),u_phone varchar(80),u_pass varchar(80),u_sc varchar(40));");
        sqLiteDatabase.execSQL("create table if not exists mail(id integer AUTO INCREMENT,m_from varchar(80),m_to varchar(80),m_date varchar(50),status varchar(20),subject varchar(50),content varchar(300));");
        //sqLiteDatabase.execSQL("create table if not exists supplier(supplier_id integer primary key,name text,email text);");
        //sqLiteDatabase.execSQL("create table if not exists sales(dos date,item_id integer,squantity integer);");
        //sqLiteDatabase.execSQL("create table if not exists expired(item_id integer references item(itemm_id),stat text);");
        //sqLiteDatabase.execSQL("create table if not exists cstock(item_id integer references item(item_id),stat text);");
        //sqLiteDatabase.execSQL("create table if not exists settings(quantity integer);");
        //sqLiteDatabase.execSQL("insert into settings values(10);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertUser(String b_name, String b_dob, String b_phone, String b_pass, String b_sc) {
        no++;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table register;");
        //sqLiteDatabase.execSQL("create table  if not exists "+TABLE_NAME+"(u_id integer autoincrement,u_name varchar(80) primary key,u_dob varchar(80),u_phone varchar(80),u_pass varchar(80));");
        sqLiteDatabase.execSQL("create table  if not exists " + TABLE_NAME + "(u_id integer auto increment,u_name varchar(80) primary key,u_dob varchar(80),u_phone varchar(80),u_pass varchar(80),u_sc varchar(40));");
        sqLiteDatabase.execSQL("insert into register values('" + no + "','" + b_name + "','" + b_dob + "','" + b_phone + "','" + b_pass + "','"+b_sc+"');");
        //sqLiteDatabase.execSQL("insert into register values('"+b_name+"','"+b_dob+"','"+b_phone+"','"+b_pass+"');");
    }

    public Cursor showUsers() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from register;", null);
        return cursor;

      /* if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dob = cursor.getString(2);
                String phone = cursor.getString(3);
                //Log.d(TAG, "onCreate: sql is" +cursor);
                Toast.makeText(this,"ID = "+id+ "Name = "+name+ "DOB = "+dob+" Phone = "+phone,Toast.LENGTH_LONG).show();

            } while(cursor.moveToNext());
        } */
        // cursor.close();
        //sqLiteDatabase.close();

    }


    public String validate(String b_name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String passv = "";
        Cursor passs = sqLiteDatabase.rawQuery("select u_pass from register where u_name= '" + b_name + "';", null);
        if (passs.moveToFirst()) {
            passv = passs.getString(0);
        }
        return passv;
    }

    public String validatesc(String b_name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sc1 = "";
        Cursor passs = sqLiteDatabase.rawQuery("select u_sc from register where u_name= '" + b_name + "';", null);
        if (passs.moveToFirst()) {
            sc1 = passs.getString(0);
        }
        return sc1;
    }


    public void insertMail(int mail_id, String fr1, String to1, String sd1, String su1, String co1, String st1) {
        no1 = no1 + 1;


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        //sqLiteDatabase.execSQL("create table if not exists mail(id INTEGER PRIMARY KEY AUTOINCREMENT,m_from varchar(80),m_to varchar(80),m_date varchar(50),status varchar(20),subject varchar(50),content varchar(300));");
        sqLiteDatabase.execSQL("create table if not exists mail(id INTEGER,m_from varchar(80),m_to varchar(80),m_date varchar(50),status varchar(20),subject varchar(50),content varchar(300));");
        Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '" + fr1 + "' and content= '" + co1 + "';", null);


        if (curesor.getCount() == 0) {


            ContentValues values = new ContentValues();
            values.put("id", mail_id);
            values.put("m_from", fr1);
            values.put("m_to", to1);
            values.put("m_date", sd1);
            values.put("status", st1);
            values.put("subject", su1);
            values.put("content", co1);

            String TABLE_NAME2 = "mail";
            sqLiteDatabase.insert(TABLE_NAME2, null, values);

            //     sqLiteDatabase.execSQL("insert into mail values('" + no1 + "','" + fr1 + "','" + to1 + "','" + sd1 + "','" + st1 + "','" + su1 + "','" + co1 + "');");
            //sqLiteDatabase.execSQL("insert into mail values('" + fr1 + "','" + to1 + "','" + sd1 + "','" + st1 + "','" + su1 + "','" + co1 + "');");
            System.out.println("Inbox successfully added to database");
        } else {
            System.out.println("Message already present in database");
        }
    }

    public int countInbox(String to1) {
        String st1 = "Inbox";
        String st2 = "Important";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c1 = sqLiteDatabase.rawQuery("select * from mail where m_to= '" + to1 + "' and status= '" + st1 + "' or status='" + st2 + "' order by id DESC;", null);
        int count = c1.getCount();
        c1.close();
        return count;
    }

    //public void inboxList(String fr1,String to1,String sd1,String su1,String co1,String st1){
    public String inboxList(String to1, String st1) {
        String st2 = "Important";
        String str1 = "";
        //String fr2,sd2,su2,co2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_to= '" + to1 + "' and status= '" + st1 + "' or status='" + st2 + "' order by id DESC;", null);
        if (curesor.moveToFirst()) {
            do {
                String fr2 = curesor.getString(1);
                String sd2 = curesor.getString(3);
                String su2 = curesor.getString(5);
                String co2 = curesor.getString(6);
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n" +">>>" +"\n";
                str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2 + "\n";
                //str1 = str1+"";

            } while (curesor.moveToNext());

        }
        curesor.close();
        sqLiteDatabase.close();
        return str1;


    }

    public int countSent(String to1) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c1 = sqLiteDatabase.rawQuery("select * from mail where m_from = '" + to1 + "' order by id desc;", null);
        int count = c1.getCount();
        c1.close();
        return count;
    }
    public String sentList(String to1) {
        String str1 = "";
        //String fr2,sd2,su2,co2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        //Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '"+to1+"' order by id;",null);
        Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from = '" + to1 + "' order by id desc;", null);
        //Cursor curesor = sqLiteDatabase.rawQuery("select id from mail;",null);
        // Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '"Balu Pradeep <balupradeep41@gmail.com>"' order by id;",null);
        if (curesor.moveToFirst()) {
            do {
                //Integer idd =  curesor.getInt(0);
                String fr2 = curesor.getString(2);
                String sd2 = curesor.getString(3);
                String su2 = curesor.getString(5);
                String co2 = curesor.getString(6);
                System.out.println("fr2    :  " + fr2);
                // System.out.println("id    :  " + idd);
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n" +">>>" +"\n";
                str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2 + "\n";
                //str1 = str1+"";

            } while (curesor.moveToNext());

        }
        System.out.println("Str1    :  " + str1);
        curesor.close();
        return str1;
        //
        //sqLiteDatabase.close();

    }

    public int countTrash(String to1) {
        //Integer i;
        String st = "Trash";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c1 = sqLiteDatabase.rawQuery("select count(*) from mail where m_to = '"+to1+"' and status ='" + st + "' order by id desc;", null);
        int count = c1.getCount();
        c1.close();
        return count;
    }


    public String trashList(String to1) throws ArrayIndexOutOfBoundsException {
        String str1 = "";
        String st = "Trash";

        //String st = "Inbox";
        //String fr2,sd2,su2,co2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        //Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '"+to1+"' order by id;",null);
        Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_to = '"+to1+"' and status ='" + st + "' order by id desc;", null);
        //Cursor curesor = sqLiteDatabase.rawQuery("select id from mail;",null);
        if (curesor.moveToFirst()) {
            do {
                //Integer idd =  curesor.getInt(0);
                String fr2 = curesor.getString(1);
                String sd2 = curesor.getString(3);
                String su2 = curesor.getString(5);
                String co2 = curesor.getString(6);
                System.out.println("fr2    :  " + fr2);
                // System.out.println("id    :  " + idd);
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n" +">>>" +"\n";
                str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2 + "\n";
                //str1 = str1+"";

            } while (curesor.moveToNext());

        }
        System.out.println("Str1    :  " + str1);
        curesor.close();
        return str1;
        //
        //sqLiteDatabase.close();

    }

    public int countSearch(String fr1,String to1) {
        //Integer i;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c1 = sqLiteDatabase.rawQuery("select * from mail where m_from ='" + fr1 + "' and m_to = '" + to1 + "'order by id desc;", null);
        int count = c1.getCount();
        c1.close();
        return count;
    }


    public String searchList(String fr1, String to1) throws ArrayIndexOutOfBoundsException {
        String str1 = "";
        //String st = "Inbox";
        //String fr2,sd2,su2,co2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        //Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '"+to1+"' order by id;",null);
        Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from ='" + fr1 + "' and m_to = '" + to1 + "'order by id desc;", null);
        //Cursor curesor = sqLiteDatabase.rawQuery("select id from mail;",null);
        if (curesor.moveToFirst()) {
            do {
                //Integer idd =  curesor.getInt(0);
                String fr2 = curesor.getString(1);
                String sd2 = curesor.getString(3);
                String su2 = curesor.getString(5);
                String co2 = curesor.getString(6);
                System.out.println("fr2    :  " + fr2);
                // System.out.println("id    :  " + idd);
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n" +">>>" +"\n";
                str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2 + "\n";
                //str1 = str1+"";

            } while (curesor.moveToNext());

        }
        System.out.println("Str1    :  " + str1);
        return str1;
        // curesor.close();
        //sqLiteDatabase.close();

    }


  /*  public String sentList(String to1) {
        String str1="";
        //String fr2,sd2,su2,co2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        //Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '"+to1+"' order by id;",null);
        //Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where LOWER(m_from) like '%'+'"+to1+"'+'_' order by id;",null);
        Cursor curesor = sqLiteDatabase.rawQuery("select id from mail;",null);
        if(curesor.moveToFirst()) {
            do {
                Integer idd =  curesor.getInt(0);
                //String fr2 = curesor.getString(1);
                //String sd2 = curesor.getString(3);
                //String su2  = curesor.getString(5);
                //String co2  = curesor.getString(6);
                //System.out.println("fr2    :  " + fr2);
                 System.out.println("id    :  " + idd);
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n" +">>>" +"\n";
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n";
                //str1 = str1+"";

            } while(curesor.moveToNext());

        }
        //System.out.println("Str1    :  " + str1);
        return str1;
        // curesor.close();
        //sqLiteDatabase.close();

    }*/


    public void trashMail(String ftrash, String strash) {
        String t = "Trash";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("update mail set status = '"+t+"' where m_from ='"+ftrash+"' and subject = '"+strash+"';");
        sqLiteDatabase.execSQL("update mail set status = '" + t + "' where m_from = '" + ftrash + "'  and subject = '" + strash + "';");

    }

    public void restoreMail(String ftrash, String strash) {
        String t = "Inbox";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("update mail set status = '"+t+"' where m_from ='"+ftrash+"' and subject = '"+strash+"';");
        sqLiteDatabase.execSQL("update mail set status = '" + t + "' where m_from = '" + ftrash + "'  and subject = '" + strash + "';");

    }

    public void deleteMail(String ftrash, String strash) {
        String t = "Trash";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("update mail set status = '"+t+"' where m_from ='"+ftrash+"' and subject = '"+strash+"';");
        sqLiteDatabase.execSQL("delete from mail where status = '" + t + "' and m_from = '" + ftrash + "'  and subject = '" + strash + "';");
    }


    public void impMail(String ftrash, String strash) {
        String t = "Important";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("update mail set status = '"+t+"' where m_from ='"+ftrash+"' and subject = '"+strash+"';");
        sqLiteDatabase.execSQL("update mail set status = '" + t + "' where m_from = '" + ftrash + "'  and subject = '" + strash + "';");

    }

    public int countImp(String to1) {
        //Integer i;
        String st = "Important";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c1 = sqLiteDatabase.rawQuery("select count(*) from mail where m_to = '"+to1+"' and status ='" + st + "' order by id desc;", null);
        int count = c1.getCount();
        c1.close();
        return count;
    }

    public String impList(String to1) throws ArrayIndexOutOfBoundsException {
        String str1 = "";
        String st = "Important";
        //String st = "Inbox";
        //String fr2,sd2,su2,co2;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.execSQL("drop table mail;");
        //System.out.println("Table Dropped");
        //Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_from= '"+to1+"' order by id;",null);
        Cursor curesor = sqLiteDatabase.rawQuery("select * from mail where m_to = '"+to1+"' and status ='" + st + "' order by id desc;", null);
        //Cursor curesor = sqLiteDatabase.rawQuery("select id from mail;",null);
        if (curesor.moveToFirst()) {
            do {
                //Integer idd =  curesor.getInt(0);
                String fr2 = curesor.getString(1);
                String sd2 = curesor.getString(3);
                String su2 = curesor.getString(5);
                String co2 = curesor.getString(6);
                System.out.println("fr2    :  " + fr2);
                // System.out.println("id    :  " + idd);
                //str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2+ "\n" +">>>" +"\n";
                str1 = str1 + fr2 + "\n" + sd2 + "\n" + su2 + "\n" + co2 + "\n";
                //str1 = str1+"";

            } while (curesor.moveToNext());

        }
        System.out.println("Str1    :  " + str1);
        return str1;
        // curesor.close();
        //sqLiteDatabase.close();

    }




      /*  public String stat(){
            String t1="Trash";
            String str1="";
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor c =  sqLiteDatabase.execSQL("select m_from from mail where status="+t1+";",null);
            if(c.moveToFirst()) {
                do {
                    String idd = c.getString(0);
                    str1 = str1 + idd;
                } while (c.moveToNext());
            }
            //statv = c.getString(0);
            return str1;

    }*/



}
