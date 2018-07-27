package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

public class myprofile extends AppCompatActivity {
    ImageView mypic;
    EditText name1,age1,height1,weight1,gender1;
    String uname;
    Button changepic;
    File imageFolder = null;
    Uri myuri;
    String imagedata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityprofile);
        setTitle("Profile");
        mypic=findViewById(R.id.mypic);
        changepic=findViewById(R.id.changepic);
        name1=findViewById(R.id.name1);
        age1=findViewById(R.id.age1);
        height1=findViewById(R.id.height1);
        weight1=findViewById(R.id.weight1);
        gender1=findViewById(R.id.gender1);
        MySession obj=new MySession(myprofile.this);
        uname=obj.getuname();

        changepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 2);
            }
        });

        try {
            SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
            try {
                Cursor myresult = mydb.rawQuery("select * from myinfo where loginid=?", new String[]{uname});
                if(myresult.moveToNext())
                {
                 imagedata = myresult.getString(myresult.getColumnIndex("image"));

                    mypic.setImageURI(Uri.parse(imagedata));
                    name1.setText(myresult.getString(myresult.getColumnIndex("name")));
                    age1.setText(myresult.getString(myresult.getColumnIndex("age")));
                    height1.setText(myresult.getString(myresult.getColumnIndex("height")));
                    weight1.setText(myresult.getString(myresult.getColumnIndex("weight")));
                    gender1.setText(myresult.getString(myresult.getColumnIndex("gender")));
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2) //for gallery image
        {
            myuri=data.getData();
            mypic.setImageURI(myuri);

            imageFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "KeepDoing");
            if(!imageFolder.exists())
            {
                imageFolder.mkdirs();
            }
            if(imageFolder.exists())
            {
                String filename="img" + new java.util.Date().getTime() + ".jpg";
                File myimage =new File(imageFolder,filename);
                myuri = Uri.fromFile(new File(myimage.getPath()));
                try {
                    myimage.createNewFile();
                    copyFile(new File(getRealPathFromURI(data.getData())), myimage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String getRealPathFromURI(Uri contentUri) { //code for image from gallery

        String[] proj = { MediaStore.Video.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private void copyFile(File sourceFile, File destFile) throws IOException {      //code for image from gallery
        if (!sourceFile.exists()) {
            return;
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.updatemyinfo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.updateinfo)
        {
            try {
                SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
                try {
                    if(myuri!=null) {

                        mydb.execSQL("update myinfo set image=?, name=?, weight=? ,height=? , age=? , gender=? where loginid=?", new String[]{myuri.toString(), name1.getText().toString(), weight1.getText().toString(), height1.getText().toString(), age1.getText().toString(), gender1.getText().toString(), uname});
                        File myfile = new File(Uri.parse(imagedata).getPath());
                        if (myfile.exists()) {
                            myfile.delete();
                        }
                    }
                    else
                    {
                        mydb.execSQL("update myinfo set name=?, weight=? ,height=? , age=? , gender=? where loginid=?", new String[]{name1.getText().toString(), weight1.getText().toString(), height1.getText().toString(), age1.getText().toString(), gender1.getText().toString(), uname});
                    }
                    mydb.close();
                    Toast.makeText(myprofile.this, "Changes Done", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(myprofile.this, "Error in Updating Table due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e)
            {
                Toast.makeText(myprofile.this, "Error in Creating Database due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
