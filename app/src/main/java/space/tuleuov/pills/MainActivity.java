package space.tuleuov.pills;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<Drug> drugs = new ArrayList<Drug>();

    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "Pills channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pills_list);

        //БАЗА ДАННЫХ



        ListView drugsList = findViewById(R.id.drugsList);
        setInitialData();
        DrugsAdapter drugsAdapter = new DrugsAdapter(this, R.layout.pills_list_one_object, drugs, dataBase);
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

                setContentView(R.layout.pills_list);
                ListView drugsList = findViewById(R.id.drugsList);
                if (drugsList != null && !drugs.isEmpty()) {
                    drugs.clear(); //
                }

                setInitialData();
                DrugsAdapter drugsAdapter = new DrugsAdapter(this, R.layout.pills_list_one_object, drugs, dataBase);
                drugsList.setAdapter(drugsAdapter);
                return true;
            case R.id.pills_set:
                setContentView(R.layout.pills_set);
                EditText nameEditText = findViewById(R.id.medication_name);
                EditText doseEditText = findViewById(R.id.dose);
                TimePicker drugTime = (TimePicker) findViewById(R.id.time);

                drugTime.setIs24HourView(true);

                Button saveButton = findViewById(R.id.save_button);
                saveButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String name = nameEditText.getText().toString();
                        String dose = doseEditText.getText().toString();
                        int hour = drugTime.getHour();
                        int minute = drugTime.getMinute();

                        SQLiteDatabase db = dataBase.getWritableDatabase();

                        ContentValues row  = new ContentValues();
                        row.put("name", name);
                        row.put("dose", dose);
                        row.put("hour", hour);
                        row.put("minute", minute);
                        long newRow = db.insert("drugs", null, row);
                        db.close();
                        Toast.makeText(MainActivity.this, "Запись сохранена", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class); // Создаем явное намерение для перехода на активность PillsListActivity
                        startActivity(intent); // Запускаем новую активность
                    }
                });
                return true;
        }
        return false;
    }
    private void setInitialData(){
        dataBase = new DataBase(this);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        String query = "SELECT * FROM drugs ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query, null);
        try{
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String dose = cursor.getString(cursor.getColumnIndexOrThrow("dose"));
                String hour = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
                String minute = cursor.getString(cursor.getColumnIndexOrThrow("minute"));
                Drug model = new Drug(id, name, dose, hour, minute);
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                String dateOfPill = hour + ":" + minute;
//                if (dateOfPill.equals(currentTime)) {
//                    System.out.println("YEEES");
//                    NotificationCompat.Builder builder =
//                            new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
//                                    .setContentTitle("Пора пить таблетки")
//                                    .setContentText("Пора пить "+ name + " дозировкой " + dose)
//                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//                    NotificationManagerCompat notificationManager =
//                            NotificationManagerCompat.from(MainActivity.this);
//                    notificationManager.notify(NOTIFY_ID, builder.build());
//                }
                drugs.add(model);
            }
        }
        finally {
            cursor.close();
        }


    }
    @Override
    protected void onDestroy(){
        dataBase.close();
        super.onDestroy();
    }


}