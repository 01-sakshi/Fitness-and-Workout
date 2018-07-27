package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class mygoals extends AppCompatActivity {
    ImageView getstart;
    TextView textView81;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitygoals);
        getstart=findViewById(R.id.getstart);
        textView81=findViewById(R.id.textView81);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView81.setTypeface(myfont);
        getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(mygoals.this,myplan.class);
                startActivity(myintent);
                mygoals.this.finish();
            }
        });
    }
}
