package jalandhar.sakshiaggarwal.keepdoing;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class mylogout extends AppCompatActivity {
    ImageView mylogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogout);
        setTitle("Account");
        mylogout=findViewById(R.id.mylogout);
        mylogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySession obj=new MySession(mylogout.this);
                obj.removeuname();
                Intent myintent=new Intent(mylogout.this,MainActivity.class);
                startActivity(myintent);
                jalandhar.sakshiaggarwal.keepdoing.mylogout.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mydelete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.delete)
        {
            AlertDialog.Builder  mybuilder=new AlertDialog.Builder(this,R.style.myalertdialogstyle);
            mybuilder.setMessage("Are you sure you want to delete ?");
            mybuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MySession obj=new MySession(mylogout.this);
                    String uname=obj.getuname();

                    try {
                        SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
                        try {

                            mydb.execSQL("delete from loginsign where loginid=?", new String[]{uname});
                            mydb.close();
                            Toast.makeText(mylogout.this, "Deleted", Toast.LENGTH_SHORT).show();
                            mylogout.this.finish();
                            obj.removeuname();
                           Intent myintent=new Intent(mylogout.this,MainActivity.class);
                           startActivity(myintent);
                           mylogout.this.finish();
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(mylogout.this, "Error in Creating Table due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(mylogout.this, "Error in Creating Database due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
            mybuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog mydialog=mybuilder.create();
            mydialog.show();
        }
        return true;
    }
}
