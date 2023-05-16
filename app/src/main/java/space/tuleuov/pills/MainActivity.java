package space.tuleuov.pills;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<Drug> drugs = new ArrayList<Drug>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //БАЗА ДАННЫХ



        ListView drugsList = findViewById(R.id.drugsList);
        setInitialData();
        DrugsAdapter drugsAdapter = new DrugsAdapter(this, R.layout.pills_list_one_object, drugs);
        drugsList.setAdapter(drugsAdapter);





        //all queries
//        Cursor allQueries = db.rawQuery("SELECT * FROM drugs;", null);
//        allQueries.close();
//        db.close();
        //

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.pills_list:
//                private List<Drug> drugs;
                setContentView(R.layout.pills_list);
//                ListView drugsList = findViewById(R.id.drugsList);
//                String[] names = {
//                        "Финлепсин","Аспирин","ГАБА","Снюс"
//                };
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        this,
//                        android.R.layout.simple_list_item_1,
//                        names
//                );
//                drugsList.setAdapter(adapter);

                ListView drugsList = findViewById(R.id.drugsList);
                setInitialData();
                DrugsAdapter drugsAdapter = new DrugsAdapter(this, R.layout.pills_list_one_object, drugs);
                drugsList.setAdapter(drugsAdapter);

                return true;
            case R.id.pills_set:
                Intent intent = new Intent(MainActivity.this, DrugSet.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
    private void setInitialData(){
        dataBase = new DataBase(this);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        String query = "SELECT * FROM drugs";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            long id = cursor.getColumnIndex("id");
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String dose = cursor.getString(cursor.getColumnIndexOrThrow("dose"));
            String hour = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
            String minute = cursor.getString(cursor.getColumnIndexOrThrow("minute"));
            Drug model = new Drug(id, name, dose, hour, minute);
            drugs.add(model);
        }


//        drugs.add(new Drug(1, "Финлепсин", "Пол таблетки", "11", "30"));
//        drugs.add(new Drug(2, "Финлепсин", "Целая таблетка", "23", "30"));
//        drugs.add(new Drug(3, "ГАБА", "Две капсулы", "11", "30"));
//        drugs.add(new Drug(4, "Снюс", "Одна снюсенка", "10", "30"));

    }
    @Override
    protected void onDestroy(){
        dataBase.close();
        super.onDestroy();
    }


}