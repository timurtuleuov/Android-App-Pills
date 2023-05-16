//package space.tuleuov.pills;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//public class DrugList {
//    DataBase dataBase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pills_list);
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
//                Toast.makeText(DrugSet.this, "Запись сохранена", Toast.LENGTH_SHORT).show();
//            }
//        });
//        dataBase = new DataBase(this);
//    }
//
//    protected void onDestroy(){
//        dataBase.close();
//        super.onDestroy();
//    }
//}
