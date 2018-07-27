package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Intent myintent;
    Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(MainActivity.this,mysignin.class);
                startActivity(myintent);
                MainActivity.this.finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent=new Intent(MainActivity.this,mylogin.class);
                startActivity(myintent);
                MainActivity.this.finish();
            }
        });
    }
}
