package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class loseoptions extends AppCompatActivity {
    Intent myintent;
    ImageView begin,inter,advan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityloseoptions);
        begin=findViewById(R.id.begin);
        inter=findViewById(R.id.inter);
        advan=findViewById(R.id.advan);
       begin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                myintent=new Intent(loseoptions.this,loosebeginner.class);
                startActivity(myintent);
           }
       });
       inter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myintent=new Intent(loseoptions.this,looseintermediate.class);
               startActivity(myintent);
           }
       });
       advan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myintent=new Intent(loseoptions.this,looseadvance.class);
               startActivity(myintent);
           }
       });
    }
}
