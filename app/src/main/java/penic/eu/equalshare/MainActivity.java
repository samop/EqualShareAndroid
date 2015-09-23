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


public class MainActivity extends AppCompatActivity {

    String people_names[]={"Ales", "Samo"};
    final ArrayList<String> list = new ArrayList<String>();
    ListView list_people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button new_person=(Button) findViewById(R.id.button);
        Button new_expence=(Button) findViewById(R.id.button2);
        list_people= (ListView) findViewById(R.id.listView);

    for (int i = 0; i < people_names.length; ++i) {
      list.add(people_names[i]);
    }
    final ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_list_item_1, list);
    list_people.setAdapter(adapter);

        new_person.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ListView list_people= (ListView) v.findViewById(R.id.listView);
                list.add("Nekdo nov");
                list_people.setAdapter(adapter);

            }
        });

        new_expence.setOnClickListener((OnClickListener) new AddNewExpenceActivity());

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
}
