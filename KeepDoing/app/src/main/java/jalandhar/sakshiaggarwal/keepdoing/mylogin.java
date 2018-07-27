package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class mylogin extends AppCompatActivity {
    EditText email2, password2;
    CheckBox showpass2;
    ImageView loginnow2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogin);
        email2 = findViewById(R.id.email2);
        password2 = findViewById(R.id.password2);
        showpass2 = findViewById(R.id.showpass2);
        loginnow2 = findViewById(R.id.loginnow2);
        loginnow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    SQLiteDatabase mydb = openOrCreateDatabase("keepdoingdb2", MODE_PRIVATE, null);
                    try {
                        Cursor myresult = mydb.rawQuery("select * from loginsign where loginid=? and password=?", new String[]{email2.getText().toString(),password2.getText().toString()});
                        if (myresult.moveToNext())
                        {
                            MySession obj=new MySession(mylogin.this);
                            obj.setuname(email2.getText().toString());
                            Intent myintent=new Intent(mylogin.this,mynavigation.class);
                            startActivity(myintent);
                            Toast.makeText(mylogin.this, "Loged In!", Toast.LENGTH_SHORT).show();
                            mylogin.this.finish();
                        }
                        else
                        {
                            Toast.makeText(mylogin.this, "Incorrect Username and Password", Toast.LENGTH_SHORT).show();
                        }
                        mydb.close();
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), "Error in Loging in due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Error in Creating Database due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        showpass2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}
