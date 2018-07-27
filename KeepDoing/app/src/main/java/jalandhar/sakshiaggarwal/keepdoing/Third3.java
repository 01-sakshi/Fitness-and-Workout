package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Third3 extends AppCompatActivity {
    ImageView ex13;
    TextView textView21;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitythird3);
        setTitle("Plank");
        ex13=findViewById(R.id.ex13);
        textView21=findViewById(R.id.textView21);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView21.setTypeface(myfont);
        AnimationDrawable myanimation=(AnimationDrawable)ex13.getDrawable();
        myanimation.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myshare,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.myshare)
        {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = textView21.getText().toString();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        return true;
    }
}
