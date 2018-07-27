package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class First10 extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView mylist5;
    TextView textView58,textView59;
    String[] activities={"Fifth1","Fourth5","Fourth6"};
    String[] exernames ={"Jumping Jack","Cossack Squat","Kneeling Back Kick"};
    int[] image={R.drawable.e1,R.drawable.v1,R.drawable.w2};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) { //body weight tonight workout beginner
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityfirst10);
        setTitle("Body-Weight Tonight Workout Beginner");
        mylist5=findViewById(R.id.mylist5);
        textView58=findViewById(R.id.textView58);
        textView59=findViewById(R.id.textView59);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView58.setTypeface(myfont);
        textView59.setTypeface(myfont);
        myhelper obj=new myhelper(this,android.R.layout.simple_list_item_1,exernames);
        mylist5.setAdapter(obj);
        mylist5.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            Class myclass = Class.forName(getPackageName() + "." + activities[position]);
            Intent myintent=new Intent(this,myclass);
            startActivity(myintent);
        }
        catch(Exception e)
        {
            Log.e("keep Doing","Error in Item Click Listener");
        }
    }
    class myhelper extends ArrayAdapter<String>
    {
        public myhelper(@NonNull Context context, int resource, @NonNull String[] objects) {
            super(context, resource, objects);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View myrow=getLayoutInflater().inflate(R.layout.activitylistdesign1,parent,false);
            TextView exername;
            ImageView img1;
            img1=myrow.findViewById(R.id.img1);
            img1.setImageResource(image[position]);
            exername=myrow.findViewById(R.id.exername);
            Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
            exername.setTypeface(myfont);
            exername.setText(exernames[position]);
            return myrow;
        }
    }
}
