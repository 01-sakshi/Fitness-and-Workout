package jalandhar.sakshiaggarwal.keepdoing;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AboutMe extends AppCompatActivity {
    Intent myintent;
    ImageView pic,birth;
    Button savebtn, take,goals,settings;
    EditText weight, name, height, age;
    RadioButton male, female;
    Uri imageUri = null;
    File imageFolder = null;
    Uri myuri;
    String gender;
    String uname;
    Calendar mycalendar;
    MySession obj1;
    String imagedata;

    @Override
    protected void onResume() {

        try {
            SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
            try {

                Cursor myresult = mydb.rawQuery("select * from myinfo where loginid=?", new String[]{uname});
                if(myresult.moveToNext()) {
                    imagedata = myresult.getString(myresult.getColumnIndex("image"));
                    pic.setImageURI(Uri.parse(imagedata));
                    name.setText(myresult.getString(myresult.getColumnIndex("name")));
                    age.setText(myresult.getString(myresult.getColumnIndex("age")));
                    height.setText(myresult.getString(myresult.getColumnIndex("height")));
                    weight.setText(myresult.getString(myresult.getColumnIndex("weight")));
                    if (myresult.getString(myresult.getColumnIndex("gender")).equals("male")) {
                        male.toggle();
                    } else if (myresult.getString(myresult.getColumnIndex("gender")).equals("female")) {
                        female.toggle();
                    }

                    if (!name.equals("") && !age.equals("") && !weight.equals("") && !height.equals("")) {
                        take.setVisibility(View.GONE);
                        savebtn.setVisibility(View.GONE);
                        birth.setVisibility(View.GONE);
                    }
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityaboutme);
        pic = findViewById(R.id.pic);
        savebtn = findViewById(R.id.savebtn);
        take = findViewById(R.id.take);
        name=findViewById(R.id.name);
        birth=findViewById(R.id.birth);
        goals=findViewById(R.id.goals);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        age = findViewById(R.id.age);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        settings=findViewById(R.id.settings);

        obj1=new MySession(AboutMe.this);
        uname=obj1.getuname();


        mycalendar = Calendar.getInstance();  //to get the object of class Calendar

        final int mYear = mycalendar.get(Calendar.YEAR);        //a shortcut way to declare variables upward and use it downwards
        final int mMonth = mycalendar.get(Calendar.MONTH);
        final int mDay = mycalendar.get(Calendar.DAY_OF_MONTH);

        obj1=new MySession(AboutMe.this);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(AboutMe.this,mysettings.class);
                startActivity(myintent);
            }
        });
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(AboutMe.this,mygoals.class);
                startActivity(myintent);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {  //to save user detail
            @Override
            public void onClick(View v) {
                String uname = obj1.getuname();
                String name1 = name.getText().toString();
                String weight1 = weight.getText().toString();
                String height1=height.getText().toString();
                String age1=age.getText().toString();

                if (!isValidName(name1))
                {
                    name.setError("Enter your Name");
                }
                else if (!isValidweight(weight1)) {

                    weight.setError("Enter your weight");
                }
                else if (!isValidheight(height1)) {

                    height.setError("Enter your height");
                }
                else if (!isValidage(age1)) {

                    age.setError("Enter your age");
                }
                else if(!isValidgender())
                {
                    Toast.makeText(AboutMe.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidimage())
                {
                    Toast.makeText(AboutMe.this, "Choose Photo", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    mydatahelper obj = new mydatahelper(AboutMe.this, "keepdoingdbfinal", null, 1);
                    obj.myOpen();
                    if (male.isSelected()) {
                        gender = "Male";
                    } else {
                        gender = "female";
                    }
                    long id = obj.insertValues1(myuri.toString(), name.getText().toString(), weight.getText().toString(), height.getText().toString(), age.getText().toString(), gender, uname);       //call to insertvalues1() method
                    if (id != -1)   //returns some row id
                    {
                        Toast.makeText(AboutMe.this, "Added!", Toast.LENGTH_SHORT).show();
                    }

                    obj.myClose();
                }
            }
        });
         birth.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 age.setShowSoftInputOnFocus(false);
                 DatePickerDialog mydialog = new DatePickerDialog(AboutMe.this, new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                         String month = "";
                         String date="";
                         if (selectedMonth < 10) {
                             month = "0" + selectedMonth;
                         }
                         if(selectedDay<10)
                         {
                             date="0"+selectedDay;
                         }
                         age.setText(date + "-" + month + "-" + selectedYear);
                     }
                 }, mYear, mMonth, mDay);
                 mydialog.getDatePicker().setMaxDate(new java.util.Date().getTime());  //to disable the future dates
                 mydialog.show();
             }
         });

        take.setOnClickListener(new View.OnClickListener() {   //Take Photo Button
            @Override
            public void onClick(View v)
            {
                if (checkAndRequestPermissions()) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 2);

                } else {
                    Toast.makeText(AboutMe.this, "First Give  permission", Toast.LENGTH_SHORT).show();
                    checkAndRequestPermissions();
                }

            }
        });
    }
    private boolean checkAndRequestPermissions ()
    {
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 2);
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2) //for gallery image
        {
            myuri=data.getData();
            pic.setImageURI(myuri);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 2);

                }
            } else {
                Toast.makeText(this, "Please give permission", Toast.LENGTH_SHORT).show();
                checkAndRequestPermissions();
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


    private boolean isValidName(String name1)
    {
        if (!name1.equals("") ) {
            return true;
        }
        return false;
    }
    private boolean isValidweight(String weight1)
    {
        if (!weight1.equals("")) {
            return true;
        }
        return false;
    }
    private boolean isValidheight(String height1)
    {
        if (!height1.equals("")) {
            return true;
        }
        return false;
    }
    private boolean isValidage(String age1)
    {
        if (!age1.equals("")) {
            return true;
        }
        return false;
    }
    private boolean isValidgender()
    {
        if (male.isChecked() || female.isChecked() ) {
            return true;
        }
        return false;
    }
    private boolean isValidimage()
    {
        if (pic.getDrawable()!=null) {
            return true;
        }
        return false;
    }
  }
