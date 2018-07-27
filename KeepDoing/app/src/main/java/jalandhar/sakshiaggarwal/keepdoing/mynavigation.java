package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class mynavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Intent myintent;
    ImageView mypic,myplan,myworkout,aboutme,aboutus;
    TextView name1,uname1;
    String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynavigation);
        myplan=findViewById(R.id.myplan);
        myworkout=findViewById(R.id.myworkout);
        aboutme=findViewById(R.id.aboutme);
        aboutus=findViewById(R.id.aboutus);

        myplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(mynavigation.this,myplan.class);
                startActivity(myintent);
            }
        });
        myworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(mynavigation.this,myworkoutonsearch.class);
                startActivity(myintent);
            }
        });
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(mynavigation.this,AboutMe.class);
                startActivity(myintent);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(mynavigation.this,aboutus.class);
                startActivity(myintent);
            }
        });
        setTitle("keep Doing");

        MySession obj=new MySession(mynavigation.this);
        uname=obj.getuname();

        try {
            SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
            try {
                Cursor myresult = mydb.rawQuery("select image,name,loginid from myinfo where loginid=?", new String[]{uname});
                if(myresult.moveToNext())
                {
                    View header=((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
                    mypic=header.findViewById(R.id.mypic);
                    name1=header.findViewById(R.id.name1);
                    uname1=header.findViewById(R.id.uname1);
                    String imagedata = myresult.getString(myresult.getColumnIndex("image"));
                    mypic.setImageURI(Uri.parse(imagedata));
                    name1.setText(myresult.getString(myresult.getColumnIndex("name")));
                    uname1.setText(myresult.getString(myresult.getColumnIndex("loginid")));
                }

                mydb.close();
            }
            catch(Exception e)
            {
                Toast.makeText(getBaseContext(), "Error in Fetching due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "Error in Creating Database due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        try {
            SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
            try {
                Cursor myresult = mydb.rawQuery("select image,name,loginid from myinfo where loginid=?", new String[]{uname});
                if(myresult.moveToNext())
                {
                    View header=((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
                    mypic=header.findViewById(R.id.mypic);
                    name1=header.findViewById(R.id.name1);
                    uname1=header.findViewById(R.id.uname1);
                    String imagedata = myresult.getString(myresult.getColumnIndex("image"));
                    mypic.setImageURI(Uri.parse(imagedata));
                    name1.setText(myresult.getString(myresult.getColumnIndex("name")));
                    uname1.setText(myresult.getString(myresult.getColumnIndex("loginid")));
                }

                mydb.close();
            }
            catch(Exception e)
            {
                Toast.makeText(getBaseContext(), "Error in Fetching due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "Error in Creating Database due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.myplan) {
            myintent=new Intent(mynavigation.this,myplan.class);
            startActivity(myintent);

        }
        else if (id == R.id.myworkout) {
            myintent=new Intent(mynavigation.this,myworkoutonsearch.class);
            startActivity(myintent);

        }
        else if (id == R.id.myabout) {
            myintent=new Intent(mynavigation.this,AboutMe.class);
            startActivity(myintent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
