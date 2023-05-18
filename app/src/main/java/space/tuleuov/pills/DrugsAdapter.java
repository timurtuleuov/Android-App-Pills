package space.tuleuov.pills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DrugsAdapter extends ArrayAdapter<Drug> {
    private LayoutInflater inflater;
    private int layout;
    private List<Drug> drugs;
    private DataBase dataBase; // Добавлено поле для объекта DataBase
    private OnDeleteClickListener onDeleteClickListener;

    // Интерфейс слушателя удаления
    public interface OnDeleteClickListener {
        void onDeleteClick(Drug drug);
    }

    // Метод для установки слушателя удаления
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    public DrugsAdapter(Context context, int resource, List<Drug> drugs, DataBase dataBase) {
        super(context, resource, drugs);
        this.drugs = drugs;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.dataBase = dataBase; // Сохраняем переданный объект DataBase в поле класса
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(this.layout, parent, false);
        }

        Button delButton = view.findViewById(R.id.btn_delete);
        Drug drug = getItem(position);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataBase.getWritableDatabase();
                int deleteRow = dataBase.deleteDrug(drug.getId());
                if (deleteRow > 0) {
                    remove(drug);
                    notifyDataSetChanged();
                } else {
                    System.out.println("ОШИБКА");
                }
            }
        });


        TextView name = view.findViewById(R.id.drug_name);
        TextView dose = view.findViewById(R.id.drug_dose);
        TextView time = view.findViewById(R.id.drug_time);

        Drug drugGet = drugs.get(position);

        name.setText(drugGet.getName());
        dose.setText(drugGet.getDose());
        time.setText(drugGet.getTime());

        return view;
    }
}
