package jalandhar.sakshiaggarwal.keepdoing;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {
    TextView about;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityaboutus);
        setTitle("About Us");
        about=findViewById(R.id.about);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        about.setTypeface(myfont);
    }
}
