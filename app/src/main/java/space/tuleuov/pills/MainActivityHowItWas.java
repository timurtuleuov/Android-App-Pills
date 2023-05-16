//package space.tuleuov.pills;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//    DataBase dataBase;
//    ArrayList<Drug> drugs = new ArrayList<Drug>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        EditText nameEditText = findViewById(R.id.medication_name);
//        EditText doseEditText = findViewById(R.id.dose);
//        TimePicker drugTime = (TimePicker) findViewById(R.id.time);
//
//        drugTime.setIs24HourView(true);
//
//        Button saveButton = findViewById(R.id.save_button);
//        saveButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                String name = nameEditText.getText().toString();
//                String dose = doseEditText.getText().toString();
//                int hour = drugTime.getHour();
//                int minute = drugTime.getMinute();
//
//                SQLiteDatabase db = dataBase.getWritableDatabase();
//
//                ContentValues row  = new ContentValues();
//                row.put("name", name);
//                row.put("dose", dose);
//                row.put("hour", hour);
//                row.put("minute", minute);
//                long newRow = db.insert("drugs", null, row);
//                db.close();
//                Toast.makeText(MainActivity.this, "Запись сохранена", Toast.LENGTH_SHORT).show();
//            }
//        });
//        //БАЗА ДАННЫХ
//        dataBase = new DataBase(this);
//
//
//
//
//        //all queries
////        Cursor allQueries = db.rawQuery("SELECT * FROM drugs;", null);
////        allQueries.close();
////        db.close();
//        //
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.pills_list:
////                private List<Drug> drugs;
//                setContentView(R.layout.pills_list);
////                ListView drugsList = findViewById(R.id.drugsList);
////                String[] names = {
////                        "Финлепсин","Аспирин","ГАБА","Снюс"
////                };
////                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
////                        this,
////                        android.R.layout.simple_list_item_1,
////                        names
////                );
////                drugsList.setAdapter(adapter);
//
//                ListView drugsList = findViewById(R.id.drugsList);
//                setInitialData();
//                DrugsAdapter drugsAdapter = new DrugsAdapter(this, R.layout.pills_list_one_object, drugs);
//                drugsList.setAdapter(drugsAdapter);
//
//                return true;
//            case R.id.pills_set:
//                EditText nameEditText = findViewById(R.id.medication_name);
//                EditText doseEditText = findViewById(R.id.dose);
//                TimePicker drugTime = (TimePicker) findViewById(R.id.time);
//
//                drugTime.setIs24HourView(true);
//
//                Button saveButton = findViewById(R.id.save_button);
//                saveButton.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//                        String name = nameEditText.getText().toString();
//                        String dose = doseEditText.getText().toString();
//                        int hour = drugTime.getHour();
//                        int minute = drugTime.getMinute();
//
//                        SQLiteDatabase db = dataBase.getWritableDatabase();
//
//                        ContentValues row  = new ContentValues();
//                        row.put("name", name);
//                        row.put("dose", dose);
//                        row.put("hour", hour);
//                        row.put("minute", minute);
//                        long newRow = db.insert("drugs", null, row);
//                        db.close();
//                        Toast.makeText(MainActivity.this, "Запись сохранена", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                setContentView(R.layout.main);
//                return true;
//        }
//        return false;
//    }
//    private void setInitialData(){
//        drugs.add(new Drug("Финлепсин", "Пол таблетки", "11", "30"));
//        drugs.add(new Drug("Финлепсин", "Целая таблетка", "23", "30"));
//        drugs.add(new Drug("ГАБА", "Две капсулы", "11", "30"));
//        drugs.add(new Drug("Снюс", "Одна снюсенка", "10", "30"));
//        drugs.add(new Drug("СНЮС", "Две снюсенки", "22", "30"));
//    }
//    @Override
//    protected void onDestroy(){
//        dataBase.close();
//        super.onDestroy();
//    }
//
//
//}