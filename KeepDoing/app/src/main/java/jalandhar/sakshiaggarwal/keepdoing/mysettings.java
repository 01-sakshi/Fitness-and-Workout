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
import android.widget.ListView;
import android.widget.TextView;

public class mysettings extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    ListView mylist5;
    String[] activities={"myprofile","mylogout","aboutus","preferenceactivity"};
    String[] names={"My Profile","Account","About Us","Other Settings"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysettings);
        mylist5=findViewById(R.id.mylist5);
        myhelper obj=new myhelper(this,android.R.layout.simple_list_item_1,names);
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
            View myrow=getLayoutInflater().inflate(R.layout.activitylistdesign2,parent,false);
            TextView name;
            name=myrow.findViewById(R.id.name);
            Typeface myfont = Typeface.createFromAsset(getAssets(), "Magnificent.ttf");
            name.setTypeface(myfont);
            name.setText(names[position]);
            return myrow;
        }
    }
}
