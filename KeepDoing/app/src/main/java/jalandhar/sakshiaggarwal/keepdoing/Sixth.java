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

public class Sixth extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView mylist5;
    TextView textView30,textView31;
    String[] activities={"First5","Fifth3","Fifth1","Fourth3"};
    String[] exernames ={"Russian Twist","Abs Stretch","Jumping Jacks","Reverse Crunch"};
    int[] image={R.drawable.o2,R.drawable.l2,R.drawable.e1,R.drawable.k2};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysixth);
        setTitle("HIIT Awakening");
        mylist5=findViewById(R.id.mylist5);
        textView30=findViewById(R.id.textView30);
        textView31=findViewById(R.id.textView31);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView30.setTypeface(myfont);
        textView31.setTypeface(myfont);
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
