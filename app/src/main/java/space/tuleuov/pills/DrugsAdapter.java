package space.tuleuov.pills;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

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
        Button editButton = view.findViewById(R.id.btn_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Редактирование")
                        .setMessage("Введите новые данные:")
                        .setView(R.layout.pills_update) // Используйте свой макет для диалога редактирования
                        .setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Получение значений из полей ввода
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                EditText nameEditText = alertDialog.findViewById(R.id.medication_name);
                                EditText doseEditText = alertDialog.findViewById(R.id.dose);
                                TimePicker drugEditTime = alertDialog.findViewById(R.id.time);
                                drugEditTime.setIs24HourView(true);

                                nameEditText.setText(drug.getName());
                                doseEditText.setText(drug.getDose());

                                String newName = nameEditText.getText().toString();
                                String newDose = doseEditText.getText().toString();
                                int newHour = drugEditTime.getHour();
                                int newMinute = drugEditTime.getMinute();


                                // Логика для сохранения изменений
                                updateDrug(drug, newName, newDose, newHour, newMinute);
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Логика для отмены редактирования
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Удаление")
                        .setMessage("Уверены, что хотите удалить?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Логика для удаления
                                SQLiteDatabase db = dataBase.getWritableDatabase();
                                int deleteRow = dataBase.deleteDrug(drug.getId());
                                if (deleteRow > 0) {
                                    remove(drug);
                                    notifyDataSetChanged();
                                } else {
                                    System.out.println("ОШИБКА");
                                }

                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Логика для отмены удаления
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

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
    private void updateDrug(Drug drug, String newName, String newDose, int newHour, int newMinute) {
        drug.setName(newName);
        drug.setDose(newDose);
        drug.setHour(String.valueOf(newHour));
        drug.setMinute(String.valueOf(newMinute));

        int rowsAffected = dataBase.updateDrug(drug, newName, newDose, newHour, newMinute);
        // Handle the result accordingly
    }

}
