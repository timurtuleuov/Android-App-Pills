package space.tuleuov.pills;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //БАЗА ДАННЫХ
        dataBase = new DataBase(this);




        //all queries
//        Cursor allQueries = db.rawQuery("SELECT * FROM drugs;", null);
//        allQueries.close();
//        db.close();
        //
        EditText nameEditText = findViewById(R.id.medication_name);
        EditText doseEditText = findViewById(R.id.dose);
        TimePicker drugTime = (TimePicker)findViewById(R.id.time);

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
            }
        });
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
                return true;
            case R.id.pills_set:
                setContentView(R.layout.pills_set);
                return true;
        }
        return false;
    }
    @Override
    protected void onDestroy(){
        dataBase.close();
        super.onDestroy();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView drugNameView;
        public TextView drugDoseView;
        public TextView drugTimeView;

        public ViewHolder(View itemView){
            super(itemView);

            drugNameView = itemView.findViewById(R.id.drug_name);
            drugDoseView = itemView.findViewById(R.id.drug_dose);
            drugTimeView = itemView.findViewById(R.id.drug_time);

        }

        private List<Drug> mDrug;
        private Context mContext;

        public DrugListAdapter(Context context, List<Drug> drugs) {
            mDrug = drugs;
            mContext = context;
        }
    }
}