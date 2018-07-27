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

public class Sixth7 extends AppCompatActivity implements AdapterView.OnItemClickListener {  //HIIT full body
    ListView mylist5;
    TextView textView70,textView71;
    String[] activities={"Sixth6","Fifth1","Second7","Sixth8","First8"};
    String[] exernames ={"V-Sit Hold","Jumping Jacks","High Knee","Push-Ups","Kneeling Push Ups"};
    int[] image={R.drawable.vsit,R.drawable.e1,R.drawable.d2,R.drawable.z2,R.drawable.u2};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysixth7);
        setTitle("HIIT Full Body");
        mylist5=findViewById(R.id.mylist5);
        textView70=findViewById(R.id.textView70);
        textView71=findViewById(R.id.textView71);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView70.setTypeface(myfont);
        textView71.setTypeface(myfont);
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
