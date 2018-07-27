package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mysignin extends AppCompatActivity {
    EditText email,password;
    CheckBox showpass;
    ImageView signnow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysignin);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        showpass=findViewById(R.id.showpass);
        signnow=findViewById(R.id.signnow);
        signnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                if (!isValidPassword(pass)) {
                    password.setError("Invalid Password");
                } else if( !isValidEmail(email1)) {

                    email.setError("Invalid Email Address");
                } else {
                    mydatahelper obj = new mydatahelper(mysignin.this, "keepdoingdb2", null, 1);
                    obj.myOpen();
                    long id = obj.insertValues(email.getText().toString(), password.getText().toString());       //call to insertvalues() method

                    if (id != -1)   //returns some row id
                    {
                        MySession obj1=new MySession(mysignin.this);
                        obj1.setuname(email.getText().toString());
                        Intent myintent=new Intent(mysignin.this,mynavigation.class);
                        startActivity(myintent);
                        mysignin.this.finish();
                    } else {
                        Toast.makeText(mysignin.this, "Email already in use", Toast.LENGTH_SHORT).show();
                    }
                    obj.myClose();
                }
            }

        });
        showpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

      private boolean isValidEmail(String emaill) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emaill);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}
