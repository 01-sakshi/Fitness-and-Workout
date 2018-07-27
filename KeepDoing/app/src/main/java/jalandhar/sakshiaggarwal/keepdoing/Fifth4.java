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

public class Fifth4 extends AppCompatActivity  implements AdapterView.OnItemClickListener{  //advanced fat loss workout
    ListView mylist1;
    TextView textView78,textView79;
    String[] activities={"Second1","Fifth1","Third6","Sixth4","Second7"};
    String[] exernames ={"Forward Shoulder Circle","Jumping Jacks","Jumping Jack Squat","Frogger","High Knee"};
    int[] image={R.drawable.b2,R.drawable.e1,R.drawable.q4,R.drawable.x4,R.drawable.d2};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityfifth4);

        setTitle("Advanced Fat Loose Workout");
        mylist1=findViewById(R.id.mylist1);
        textView78=findViewById(R.id.textView78);
        textView79=findViewById(R.id.textView79);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView78.setTypeface(myfont);
        textView79.setTypeface(myfont);
        myhelper obj=new myhelper(this,android.R.layout.simple_list_item_1,exernames);
        mylist1.setAdapter(obj);
        mylist1.setOnItemClickListener(this);
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
