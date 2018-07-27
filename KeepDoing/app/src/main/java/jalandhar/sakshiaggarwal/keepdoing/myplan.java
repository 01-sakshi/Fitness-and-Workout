package jalandhar.sakshiaggarwal.keepdoing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static java.security.AccessController.getContext;

public class myplan extends AppCompatActivity {
    ImageView lose,healthy;
    //ImageView nextac;
    Intent myintent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityplan);
        //nextac=findViewById(R.id.nextac);
        lose=findViewById(R.id.lose);
        healthy=findViewById(R.id.healthy);
        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        myintent=new Intent(myplan.this,loseoptions.class);
                        startActivity(myintent);
            }
        });
        healthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        myintent=new Intent(myplan.this,healthyoptions.class);
                        startActivity(myintent);
                    }
        });
    }
}
