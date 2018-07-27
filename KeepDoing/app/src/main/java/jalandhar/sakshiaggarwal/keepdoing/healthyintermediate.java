package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Context;
import android.content.Intent;
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

public class healthyintermediate extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView mylist6;
    String[] activities={"Second6","Third","First6","","First10","Sixth2",""};
    String[] days={"Day 1","Day 2","Day 3","Day 4","Day 5","Day 6","Day 7"};
    String[] tasks={"Intermediate Abs Workout","Lower Back Relaxation","Intermediate Fat Loss Workout","Rest Day","Body Weight Tonight Workout-Beginner","Full Body Stretch","Rest Day"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhealthyintermediate);
        mylist6=findViewById(R.id.mylist6);
        myhelper obj=new myhelper(this,android.R.layout.simple_list_item_1,tasks);
        mylist6.setAdapter(obj);
        mylist6.setOnItemClickListener(this);
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
            View myrow=getLayoutInflater().inflate(R.layout.activitylistdesign,parent,false);
            TextView day,task;
            day=myrow.findViewById(R.id.day);
            task=myrow.findViewById(R.id.task);
            day.setText(days[position]);
            task.setText(tasks[position]);
            return myrow;
        }
    }
}
