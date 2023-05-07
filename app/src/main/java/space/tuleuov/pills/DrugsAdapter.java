package space.tuleuov.pills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DrugsAdapter extends ArrayAdapter<Drug> {
    private LayoutInflater inflater;
    private int layout;
    private List<Drug> drugs;

    public DrugsAdapter(Context context, int resource, List<Drug> drugs){

        super(context, resource, drugs);
        this.drugs = drugs;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(this.layout, parent, false);

        TextView name = view.findViewById(R.id.drug_name);
        TextView dose = view.findViewById(R.id.drug_dose);
        TextView time = view.findViewById(R.id.drug_time);

        Drug drug = drugs.get(position);

        name.setText(drug.getName());
        dose.setText(drug.getDose());
        time.setText(drug.getTime());


        return view;
    }
}
