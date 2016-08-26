package com.example.le.custommanagerdemo_theme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.le.custommanagerdemo_theme.R;
import com.example.le.custommanagerdemo_theme.model.CustomListview;

import java.util.ArrayList;

public class CustomListviewAdapter extends ArrayAdapter<CustomListview> {
    private ArrayList<CustomListview> arrCustomListview;
    private LayoutInflater layoutInflater;
    private Context context;
    public CustomListviewAdapter(Context context, int resource, ArrayList<CustomListview> objects) {
        super(context, resource, objects);
        this.context=context;
        this.arrCustomListview = objects;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_custom_list_item, parent, false);
            viewHolder.imvIcon = (ImageView) convertView.findViewById(R.id.imvIcon);
            viewHolder.txtLine1 = (TextView) convertView.findViewById(R.id.txtLine1);
            viewHolder.txtLine2 = (TextView) convertView.findViewById(R.id.txtLine2);
            viewHolder.cbMark = (CheckBox) convertView.findViewById(R.id.cbMark);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final CustomListview customListview = arrCustomListview.get(position);
        if (customListview != null) {
            viewHolder.txtLine1.setText(customListview.getLine1());
            viewHolder.txtLine2.setText(customListview.getLine2());
            viewHolder.cbMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    customListview.setSelected(b);
                }
            });
            viewHolder.cbMark.setChecked(customListview.isSelected());
//            if (customListview.getType() == 1) {
//                viewHolder.imvIcon.setImageResource(R.drawable.file);
//                viewHolder.cbMark.setVisibility(View.VISIBLE);
//            } else if (customListview.getType() == 2) {
//                viewHolder.cbMark.setVisibility(View.INVISIBLE);
//                viewHolder.imvIcon.setImageResource(R.drawable.folder);
//
//            }
        }

        return convertView;
    }

    public void mark(int i) {
        for (int k = 0; k < arrCustomListview.size(); k++) {
            if (k != i) arrCustomListview.get(k).setSelected(false);
        }
        arrCustomListview.get(i).setSelected(arrCustomListview.get(i).isSelected() ? false : true);
        notifyDataSetChanged();


    }

    public int checkSelected() {

        for (int i = 0; i < arrCustomListview.size(); i++) {
            if (arrCustomListview.get(i).isSelected()) return i;
        }
        return -1;

    }

    public class ViewHolder  {
        public ImageView imvIcon;
        public TextView txtLine1;
        public TextView txtLine2;
        public CheckBox cbMark;

       /* public ViewHolder(View v) {
            v.setOnCreateContextMenuListener(this);
        }*/

    }

}