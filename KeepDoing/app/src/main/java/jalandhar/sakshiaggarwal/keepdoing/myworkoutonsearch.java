package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class myworkoutonsearch extends AppCompatActivity implements AdapterView.OnItemClickListener{  //listview with search
    ArrayAdapter adapter;
    ListView mylist;
    EditText search;
    String[] activities={"Fifth3","First3","First4","Second4","Fourth5","Second1","Second3","Sixth4","Second7","Third1","Third7","Fifth1","Third6","First7","First8","Fourth6","Fourth4","Third4","Sixth8","Third3","First5","Fourth3","Second5","First1","Fourth1","Sixth6"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivtyworkouts);
        mylist=findViewById(R.id.mylist5);
        search=findViewById(R.id.search);
        mylist.setOnItemClickListener(this);
        ArrayList<HashMap<String,String>> names;
        String[] shows={"Abs Stretch","Assisted Crunch","Back Stretch","Bent Over Lateral Raise","Cossack Squat","Forward Shoulder Circle","Fifer Scissors","Frogger","High Knee","Hop","Heel Toucher","Jumping Jacks","Jumping Jack Squat","Knee Toucher","Kneeling Push-Ups","Kneeling Back Kick","Left Power Knee","Left Abs Stretch","Push-Ups","Plank","Russian Twist","Reverse Crunch","Right Abs Stretch","Standing Hip Rotation","Sprinting in Place","V-Sit Hold"};
        adapter=new ArrayAdapter(this,R.layout.activitysearchdesign,R.id.name,shows);
        mylist.setAdapter(adapter);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myworkoutonsearch.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            Log.e("keep Doing","Error in Item Click Listener");
        }
    }
}
