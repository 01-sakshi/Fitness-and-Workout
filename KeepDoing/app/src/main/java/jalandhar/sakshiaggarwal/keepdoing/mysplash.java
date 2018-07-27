package jalandhar.sakshiaggarwal.keepdoing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class mysplash extends AppCompatActivity {
    ProgressDialog mydialog;
    Intent myintent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysplash);
        SharedPreferences myprefer= PreferenceManager.getDefaultSharedPreferences(this);
        boolean splash=myprefer.getBoolean("splashbox",true);
        mydialog =new ProgressDialog(mysplash.this);


        if(splash==false)
        {
          myintent=new Intent(mysplash.this,MainActivity.class);
          startActivity(myintent);
          mysplash.this.finish();
        }
        else
        {
            mydialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mydialog.setMessage("Loading...");
            mydialog.setCancelable(true);
            mydialog.setMax(100);
            mydialog.show();

            Thread t=new Thread() {
                public void run()
                {
                    try
                    {
                        for(int i=0;i<100;i++)
                        {

                           Thread.sleep(60);
                            mydialog.incrementProgressBy(i);
                        }

                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        myintent=new Intent(mysplash.this,MainActivity.class);
                        startActivity(myintent);
                        mysplash.this.finish();
                    }
                }
            };    t.start();
        }
    }
}
