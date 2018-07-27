package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class healthyoptions extends AppCompatActivity {
    Intent myintent1;
  ImageView beginl,inter,advan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhealthyoptions);
        beginl=findViewById(R.id.begin);
        inter=findViewById(R.id.inter);
        advan=findViewById(R.id.advan);
        beginl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               myintent1=new Intent(healthyoptions.this,healthybeginner.class);
               startActivity(myintent1);
            }
        });
        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent1=new Intent(healthyoptions.this,healthyintermediate.class);
                startActivity(myintent1);
            }
        });
        advan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myintent1=new Intent(healthyoptions.this,healthyadvance.class);
                startActivity(myintent1);
            }
        });
    }
}
