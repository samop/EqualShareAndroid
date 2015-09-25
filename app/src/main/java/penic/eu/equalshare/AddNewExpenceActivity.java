package penic.eu.equalshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import java.util.ArrayList;
import android.view.View.OnClickListener;

public class AddNewExpenceActivity extends AppCompatActivity implements OnClickListener {

    Button backButton;
    EditText expenceValue;
    Spinner people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expence);
         // 1. get passed intent
        Intent intent = getIntent();
        // 2. get list from intent
        ArrayList<String> people_list= intent.getStringArrayListExtra("people_list");

        //fill data into the Spinner.
        people= (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, people_list);
        people.setAdapter(adapter);

        backButton=(Button) findViewById(R.id.button3);
        backButton.setOnClickListener(this);

        expenceValue=(EditText) findViewById(R.id.editText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_expence, menu);
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

    @Override
    public void onClick(View view) {
         Intent returnIntent = new Intent();
         returnIntent.putExtra("value",expenceValue.getText().toString());
         returnIntent.putExtra("payedBy", people.getSelectedItemPosition());
         setResult(RESULT_OK,returnIntent);
         finish();
    }
}
