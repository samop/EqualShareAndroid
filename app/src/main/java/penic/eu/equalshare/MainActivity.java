package penic.eu.equalshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    //String people_names[]={"Ales", "Samo"};
    ListView list_people;

    DataObj berkiList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button new_person=(Button) findViewById(R.id.button);
        Button new_expence=(Button) findViewById(R.id.button2);
        list_people= (ListView) findViewById(R.id.listView);

        berkiList=new DataObj();

   /* for (int i = 0; i < people_names.length; ++i) {
      berkiList.name.add(people_names[i]);
        berkiList.balance.add(0.0f);
        berkiList.totalPaid.add(0.0f);
        berkiList.weight.add(1.0f);

    } */
        berkiList.updateLabel();
    adapter = new ArrayAdapter(this,
        android.R.layout.simple_list_item_1, berkiList.tempLabel);
    list_people.setAdapter(adapter);

        new_person.setOnClickListener(this);
        new_expence.setOnClickListener(this);

    }

    public void test_function(){

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


/** In the following function we handle the button clicks. **/
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button2) {
            ListView people = (ListView) v.findViewById(R.id.listView);
            Intent intent = new Intent(v.getContext(), AddNewExpenceActivity.class);
            intent.putExtra("people_list", berkiList.name);
            startActivityForResult(intent, 1);
        } else if(v.getId()==R.id.button){
            Intent intent = new Intent(v.getContext(), AddNewPersonActivity.class);
            startActivityForResult(intent, 2);
        }
    }


     protected void onActivityResult(int requestCode, int resultCode, Intent data) {

             if(resultCode == RESULT_OK) {
                 if (requestCode == 1) {

                     //Calculate new expences...
                     int index = data.getIntExtra("payedBy", 0);
                     float value = Float.parseFloat(data.getStringExtra("value"));
                     //Toast.makeText(MainActivity.this, "payedBy="+index+", value="+value, Toast.LENGTH_SHORT).show();
                     berkiList.totalPaid.set(index, berkiList.totalPaid.get(index)
                             + value);
                     //recalculate balance
                     berkiList.calc();
                     berkiList.updateLabel();
                     adapter = new ArrayAdapter(this,
                             android.R.layout.simple_list_item_1, berkiList.tempLabel);
                     list_people.setAdapter(adapter);
                 } else if (requestCode == 2) {
                     String name=data.getStringExtra("name");
                     float weight=Float.parseFloat(data.getStringExtra("weight"));
                     berkiList.name.add(name);
                     berkiList.weight.add(weight);
                     berkiList.balance.add(0.0f);
                     berkiList.totalPaid.add(0.0f);
                     berkiList.calc();
                     berkiList.updateLabel();
                     adapter = new ArrayAdapter(this,
                             android.R.layout.simple_list_item_1, berkiList.tempLabel);
                     list_people.setAdapter(adapter);

                 }
             }
    }
}
