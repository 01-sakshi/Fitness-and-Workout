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

public class Third5 extends AppCompatActivity implements AdapterView.OnItemClickListener{    //HIIT full body beginner
    ListView mylist5;
    TextView textView52,textView53;
    String[] activities={"Second1","Third3","Fifth1","First8"};
    String[] exernames ={"Arm Circles","Plank","Jumping Jack","Kneeling Push-Ups"};
    int[] image={R.drawable.b2,R.drawable.j1,R.drawable.e1,R.drawable.u2};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitythird5);
        setTitle("HIIT Full Body Beginner");
        mylist5=findViewById(R.id.mylist5);
        textView52=findViewById(R.id.textView52);
        textView53=findViewById(R.id.textView53);
        Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
        textView52.setTypeface(myfont);
        textView53.setTypeface(myfont);
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
