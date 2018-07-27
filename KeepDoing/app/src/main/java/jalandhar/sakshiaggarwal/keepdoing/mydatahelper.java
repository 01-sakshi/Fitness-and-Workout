package jalandhar.sakshiaggarwal.keepdoing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class mydatahelper extends SQLiteOpenHelper
{
    Context mycontext;
    SQLiteDatabase mydatabase;
    public mydatahelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "keepdoingdb2", null, 1);
        mycontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        try {
            mydb.execSQL("create table if not exists loginsign(loginid varchar(400) PRIMARY KEY, password varchar(300))");
            mydb.execSQL("create table if not exists myinfo(image varchar(1000),name varchar(200),weight varchar(200),height varchar(200),age varchar(200),gender varchar(200),loginid varchar(400), FOREIGN KEY (loginid) REFERENCES loginsign(loginid))");
        }
        catch(Exception e)
        {
            Toast.makeText(mycontext, "Error in creating table due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void myOpen()
    {
        mydatabase = this.getReadableDatabase();     //it calls onCreate() and onUpgrade() methods itself
    }
    public void myClose()
    {
        mydatabase.close();
    }
    public long insertValues(String inputid, String inputpass)  //user defined
    {
        ContentValues myvalues=new ContentValues();
        myvalues.put("loginid", inputid);                     // " " contains column name
        myvalues.put("password", inputpass);
        return mydatabase.insert("loginsign", null, myvalues);
    }
    public long insertValues1(String inputimage,String inputname, String inputweight, String inputheight,String inputage,String inputgender,String inputuname)  //user defined
    {
        ContentValues myvalues=new ContentValues();

        myvalues.put("image",inputimage);
        myvalues.put("name", inputname);
        myvalues.put("weight", inputweight);
        myvalues.put("height", inputheight);
        myvalues.put("age", inputage);
        myvalues.put("gender",inputgender);
        myvalues.put("loginid",inputuname);

        return mydatabase.insert("myinfo", null, myvalues);
    }
}
